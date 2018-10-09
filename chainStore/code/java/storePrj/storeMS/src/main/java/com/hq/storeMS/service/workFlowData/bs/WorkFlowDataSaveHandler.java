package com.hq.storeMS.service.workFlowData.bs;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.order.bs.OrderHandler;
import com.hq.storeMS.service.workFlowData.apiData.save.WorkFlowDataSaveForm;
import com.hq.storeMS.service.workFlowData.apiData.save.WorkFlowDataSaveTypeEnum;
import com.hq.storeMS.service.workFlowData.data.MemCardInfo;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowType.bs.WorkFlowTypeMgr;
import com.hq.storeMS.service.workFlowType.data.SysFixCompEnum;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataSaveHandler {

	public static WorkFlowDataSaveHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataSaveHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> saveOrUpdate(WorkFlowDataSaveForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData target = null;
			long id = inputForm.getId();
			if(id == 0) {
				target = addWorkFlowData(inputForm);
			}else {
				target = WorkFlowDataMgr.getInstance().get(id);
				if(target == null) {
					target = addWorkFlowData(inputForm);
				}else {
					updateWorkFlowData(target, inputForm);
				}
			}
			//添加订单
			OperateTips operateTips = OperateTips.newInstance(true);
			if((inputForm.getSaveType()==WorkFlowDataSaveTypeEnum.ADDORDER.ordinal()) && (target !=null)){
				Order order = OrderHandler.getInstance().addPreOrder(target.getId());
				if(order == null) {
					operateTips.setSuccess(false);
					operateTips.setTips("生成订单失败");
				}
			}
			if(operateTips.isSuccess()){
				target = WorkFlowDataMgr.getInstance().get(target.getId());
				result.setTarget(target);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setTips(operateTips.getTips());
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataSaveHandler[saveOrUpdate]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private WorkFlowData addWorkFlowData(WorkFlowDataSaveForm inputForm) {
		MemCardInfo memCardInfo = inputForm.getMemCardInfo();
		WorkFlowType workFlowType = null;
		if(memCardInfo == null) {
			workFlowType = WorkFlowTypeMgr.getInstance().findOne(SysFixCompEnum.BuyComp.getMark());
		}else {
			workFlowType = WorkFlowTypeMgr.getInstance().findOne(SysFixCompEnum.MemCardComp.getMark());
		}
		BUser sessionBUser = BUserAuthUtils.getInstance().getSessionBUser();
		WorkFlowData workFlowData = inputForm.toWorkFlowData(sessionBUser.getId(), workFlowType.getId());
		WorkFlowDataMgr.getInstance().addAndReturnId(workFlowData);
		return workFlowData;
	}
	
	private void updateWorkFlowData(WorkFlowData target, WorkFlowDataSaveForm inputForm) {
		inputForm.updateWorkFlowData(target);
		WorkFlowDataMgr.getInstance().updateWorkFlowData(target);
	}
}
