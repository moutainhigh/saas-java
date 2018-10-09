package com.hq.storeMS.service.orderNotes.bs.revoke;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateChargeBackForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateStatusApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataMgr;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.zenmind.common.hotSwap.HotSwap;

public class RevokeUpdateHandle {

	public static RevokeUpdateHandle getInstance() {
		return HotSwap.getInstance().getSingleton(RevokeUpdateHandle.class);
	}

	public OperateTips update(Order order) {
		OperateTips operateTips = OperateTips.newInstance(true);
		updateMemberCardBalance(order);
		updateOrderChargeBackCost(order);
		updateOrderStatus(order);
		return operateTips;
	}
	
	private void updateMemberCardBalance(Order order) {
		String memberCardId = getMemberCardId(order);
		List<RechargeItem> rechargeItems = order.getRechargeItems();
		RechargeItem rechargeItem = rechargeItems.get(0);
		float amount = rechargeItem.getAmount();
		
		LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(order.getStoreId(), order.getLeaguerId());
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(order.getStoreId());
		if (!storeCard.memberCardIsFromChain(memberCardId)) {// 本店卡
			LeaguerMemberCard leaguerMemberCard = detail.getLeaguerMemberCard();
			LeaguerDetailBeanHelper.getInstance().reduceBalance(leaguerMemberCard, amount);
			LeaguerDetailMgr.getInstance().updateSimple(detail);
		}else {//连锁卡
			CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(detail.getCuserId());
			Store store = StoreMgr.getInstance().getSimple(detail.getStoreId());
			LeaguerMemberCard leaguerMemberCard = cuserChainData.takeLeaguerMemberCard(store.takeChainId());
			LeaguerDetailBeanHelper.getInstance().reduceBalance(leaguerMemberCard, amount);
			CuserChainDataMgr.getInstance().update(cuserChainData);
		}
	}
	
	private void updateOrderChargeBackCost(Order order) {
		OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
		updateApiForm.setUpdateType(OrderUpdateType.UpdateChargeBackCost.ordinal());
		updateApiForm.setStoreId(order.getStoreId());
		OrderUpdateChargeBackForm orderUpdateChargeBackForm = OrderUpdateChargeBackForm.newInstance();
		orderUpdateChargeBackForm.setChargeBackCost(order.getRealPay());
		updateApiForm.setOrderUpdateChargeBackForm(orderUpdateChargeBackForm);
		OrderMgr.getInstance().update(order.getId(), updateApiForm);
	}
	
	private void updateOrderStatus(Order order) {
		OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
		updateApiForm.setUpdateType(OrderUpdateType.UpdateState.ordinal());
		updateApiForm.setStoreId(order.getStoreId());
		OrderUpdateStatusApiForm updateStatusData = OrderUpdateStatusApiForm.newInstance();
		updateStatusData.setStatus(OrderStatusEnum.CHARGEBACK_ALL.ordinal());
		updateApiForm.setUpdateStatusData(updateStatusData);
		OrderMgr.getInstance().update(order.getId(), updateApiForm);
	}

	/**
	 * 1、检查订单的状态 2、检查充值项 3、比对余额与撤单的金额
	 * 
	 * @param order
	 * @return
	 */
	public OperateTips check(Order order) {
		OperateTips operateTips = OperateTips.newInstance(true);
		if (order.getStatus() != OrderStatusEnum.HAS_PAY.ordinal()) {
			operateTips.setTips("只能撤销已付款的订单");
			operateTips.setSuccess(false);
			return operateTips;
		}
		List<RechargeItem> rechargeItems = order.getRechargeItems();
		if (CollectionUtils.isEmpty(rechargeItems)) {
			operateTips.setTips("充值项为空，不可撤单");
			operateTips.setSuccess(false);
			return operateTips;
		}
		
		RechargeItem rechargeItem = rechargeItems.get(0);
		String memberCardId = getMemberCardId(order);
		float balance = getMemberCardCost(memberCardId, order);
		if (balance < rechargeItem.getAmount()) {
			operateTips.setTips("会员卡余额不足，撤单失败");
			operateTips.setSuccess(false);
			return operateTips;
		}
		operateTips.setSuccess(true);
		return operateTips;
	}
	
	private String getMemberCardId(Order order) {
		List<RechargeItem> rechargeItems = order.getRechargeItems();
		String memberCardId = order.getMemberCardId();
		RechargeItem rechargeItem = rechargeItems.get(0);
		if (StringUtils.isNotBlank(rechargeItem.getMembershipCardId())) {// 设置新的会员卡
			memberCardId = rechargeItem.getMembershipCardId();
		}
		return memberCardId;
	}

	// 获取客户当前会员卡的余额信息
	private float getMemberCardCost(String memberCardId, Order order) {
		float cost = 0f;
		LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(order.getStoreId(), order.getLeaguerId());
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(detail.getStoreId());
		LeaguerMemberCard memberCard = getLeaguerMemberCard(memberCardId, detail, storeCard);
		if (memberCard != null) {
			cost = memberCard.getBalance();
		}
		return cost;
	}

	private LeaguerMemberCard getLeaguerMemberCard(String memberCardId, LeaguerDetail detail, StoreCardInfo storeCard) {
		if (!storeCard.memberCardIsFromChain(memberCardId)) {// 本店卡
			return detail.getLeaguerMemberCard();
		}

		// 连锁卡
		CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(detail.getCuserId());
		Store store = StoreMgr.getInstance().getSimple(detail.getStoreId());
		if (store != null && store.takeChainId() != null) {
			return cuserChainData.takeLeaguerMemberCard(store.takeChainId());
		}
		return null;
	}
}
