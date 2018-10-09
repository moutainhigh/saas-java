package com.hq.storeMS.service.order.bs.pay;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderOriginEnum;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordForm;
import com.hq.storeMS.service.bonusRecord.bs.BonusRecordMgr;
import com.hq.storeMS.service.bonusRecord.data.BonusRecordStatusEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 订单提成的回调处理
 *
 */
public class OrderBonusMgr {

	public static OrderBonusMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderBonusMgr.class);
	}
	
	/**
	 *  设置订单的提成信息
	 * @param order
	 * @param bonusInfoAddForms
	 * @return
	 */
	public OperateTips saveBonusRecord(Order order, List<BonusInfoAddForm> bonusInfoAddForms) {
		OperateTips operateTips = OperateTips.newInstance(false, "设置提成信息失败");
		if(CollectionUtils.isEmpty(bonusInfoAddForms)){//该订单没有提成信息 
			operateTips.setSuccess(true);
			operateTips.setTips("订单提成信息为空");
			return operateTips;
		}
		
		try {
			for (BonusInfoAddForm bonusInfoAddForm : bonusInfoAddForms) {
				BonusRecordForm bonusRecordForm = bonusInfoAddForm.toBonusRecordForm(order.getStoreId(), order.getId());
				BonusRecordMgr.getInstance().saveBonusRecord(bonusRecordForm, order);
			}
			operateTips.setSuccess(true);
			operateTips.setTips("设置订单提成信息成功");
		} catch (Exception e) {
			final String logId = "OrderBonusMgr[saveBonusRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(order.getId(), bonusInfoAddForms);
			MainLog.error(LogModule.Order, logId, reason, e);
		}
		return operateTips;
	}
	
	/**
	 *  更改提成信息， 状态为 有效
	 * @param order
	 * @return
	 */
	public OperateTips updateBonusInfo(Order order) {
		OperateTips operateTips = OperateTips.newInstance(false, "更改提成信息失败");
		try {
			//C端订单
			if(order.getOrigin() == OrderOriginEnum.CUSTOMER.ordinal()) {
				operateTips.setSuccess(true);
				return operateTips;
			}
			BonusRecordMgr.getInstance().updateBonusRecordStatusByOrderId(order.getId(), BonusRecordStatusEnum.VALID.ordinal());
			operateTips.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderBonusMgr[updateBonusInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(order.getId());
			MainLog.error(LogModule.Order, logId, reason, e);
		}
		return operateTips;
	}

}
