package com.hq.storeMS.service.order.bs.pay;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.apiData.PayOrderWithNoteApiForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderOriginEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PayTypeEnum;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.arrearage.bs.ArrearageMgr;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.order.bs.check.CheckLeaguerInfoFilterHelper;
import com.hq.storeMS.service.order.bs.updateLeaguerInfo.UpdateLeaguerInfoFilterHelper;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackUpdateStatusForm;
import com.hq.storeMS.service.orderTrack.bs.OrderTrackMgr;
import com.hq.storeMS.service.orderTrack.data.OrderTrackStatusEnum;
import com.hq.storeMS.service.spreadStatis.bs.SpreadStatisModifyMgr;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 订单支付单独逻辑处理
 *
 */
public class OrderPayMgr {

	public static OrderPayMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderPayMgr.class);
	}
	
	// 更新订单的付款信息
	public OperateTips updateOrderByPayItem(Order order, List<PayItem> payItems) {
		OperateTips operateTips = OperateTips.newInstance(false, "订单支付失败");
		try {
			OrderUpdatePayItemApiForm updatePayItemApiForm = OrderUpdatePayItemApiForm.newInstance();
			updatePayItemApiForm.setPayItems(payItems);
			OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
			updateApiForm.setStoreId(order.getStoreId());
			updateApiForm.setUpdatePayItemApiForm(updatePayItemApiForm);
			updateApiForm.setUpdateType(OrderUpdateType.UpdatePayItem.ordinal());
			OrderMgr.getInstance().update(order.getId(), updateApiForm);
			operateTips.setSuccess(true);
		} catch (Exception e) {
			MainLog.error(LogModule.Order, "OrderPayMgr[updateOrderByPayItem]", "", e);
		}
		return operateTips;
	}
	
	/**
	 * 订单支付流程 
	 * 1. 比对金额 会员卡余额 次卡次数 应结金额等信息 
	 * 2. 更改订单的状态 已支付 
	 * 3. 更改客户的信息：绑定次卡、次卡减数、余额减少等操作。 
	 * 4. 更改提成信息， 状态为 有效
	 * 5. 有欠款支付的，生成欠款信息
	 * 6. 修改订单物流信息
	 */
	public OperateTips payOrderWithNote(Order order, PayOrderWithNoteApiForm form){
		OperateTips operateTips = OperateTips.newInstance(false, "支付订单失败");
		if (CollectionUtils.isNotEmpty(form.getPayItems())) {
			// 1.检验会员卡余额 客户次卡次数 应结金额等信息
			operateTips = CheckLeaguerInfoFilterHelper.getInstance().check(order, form.getPayItems());
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
			// 2.更改订单的状态为已支付
			operateTips = updateOrderPayWithNote(order, form);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
			//付款成功后 重新获取订单信息
			long orderId = order.getId();
			Order tmp = OrderMgr.getInstance().get(order.getStoreId(), orderId);
			FastBeanCopyer.getInstance().copy(tmp, order);
			// 3.更改客户的信息：绑定次卡、次卡减数、余额减少等操作。
			operateTips = UpdateLeaguerInfoFilterHelper.getInstance().updateInfo(order);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
			// 4.更改提成信息， 状态为 有效
			OrderBonusMgr.getInstance().updateBonusInfo(order);
			// 5.生成欠款信息
			createArrearage(order, form.getPayItems());
			//6.修改订单物流信息
			updateOrderTrack(order);
			//7.增加用户推广记录信息
			SpreadStatisModifyMgr.getInstance().addByOrder(order);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
			operateTips.setSuccess(true);
			operateTips.setTips("支付订单成功");
		}
		return operateTips;
	}
	
	private void updateOrderTrack(Order order) {
		if(order.getOrigin() == OrderOriginEnum.CUSTOMER.ordinal()) {//小程序商城订单
			OrderTrackMgr.getInstance().updateStatus(order.getStoreId(), order.getId(), OrderTrackUpdateStatusForm.newInstance(OrderTrackStatusEnum.Pay));
		}
	}
	
	//最后完成订单的支付
	private OperateTips updateOrderPayWithNote(Order order, PayOrderWithNoteApiForm form) {
		OperateTips operateTips = OperateTips.newInstance(false, "订单支付失败");
		try {
			OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
			updateApiForm.setStoreId(order.getStoreId());
			updateApiForm.setPayOrderWithNoteApiForm(form);
			updateApiForm.setUpdateType(OrderUpdateType.PayOrderWithNote.ordinal());
			OrderMgr.getInstance().update(order.getId(), updateApiForm);
			operateTips.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderPayMgr[updateOrderPayWithNote]";
			final String reason = LogHelper.getInstance().exceptionReason(order.getId(), form);
			MainLog.error(LogModule.Order, logId, reason, e);
		}
		return operateTips;
	}
	
	// 生成欠款信息
	private OperateTips createArrearage(Order order, List<PayItem> payItems) {
		OperateTips operateTips = OperateTips.newInstance(false, "生成欠款信息失败");
		try {
			float balanceTotal = getBalanceTotal(payItems);
			if(balanceTotal > 0){
				saveArrearageByCond(order, balanceTotal);
			}
			operateTips.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderPayMgr[addArrearage]";
			final String reason = LogHelper.getInstance().exceptionReason(order.getId(), payItems);
			MainLog.error(LogModule.Order, logId, reason, e);
		}
		return operateTips;
	}
	
	private void saveArrearageByCond(Order order, float balanceTotal){
		Leaguer leaguer = StoreLeaguerInfoMgr.getInstance().get(order.getStoreId()).takeLeaguerById(order.getLeaguerId());
		BUser buserRO = BUserAuthUtils.getInstance().getSessionBUser();
		if(leaguer != null && buserRO != null){
			Arrearage arrearage = Arrearage.newInstance();
			arrearage.setStoreId(order.getStoreId());
			arrearage.setOrderId(order.getId());
			arrearage.setOrderNumber(order.getNumber());
			arrearage.setLeaguerId(leaguer.getId());
			arrearage.setLeaguerName(leaguer.getName());
			arrearage.setLeaguerPhone(leaguer.getPhone());
			arrearage.setBalanceDue(balanceTotal);
			arrearage.setBalanceTotal(balanceTotal);
			arrearage.setCreatorId(buserRO.getId());
			arrearage.setCreatorName(buserRO.getName());
			ArrearageMgr.getInstance().addAndReturnId(arrearage);
		}
	}
	
	private float getBalanceTotal(List<PayItem> payItems){
		float balanceTotal = 0;
		for (PayItem payItem : payItems) {
			if(payItem.getPayType() == PayTypeEnum.ARREARAGE.ordinal()){
				balanceTotal = payItem.getCost();
				break;
			}
		}
		return balanceTotal;
	}
}
