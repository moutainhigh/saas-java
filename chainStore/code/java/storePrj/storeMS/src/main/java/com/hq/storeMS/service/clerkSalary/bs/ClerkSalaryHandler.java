package com.hq.storeMS.service.clerkSalary.bs;

import java.util.List;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.clerkSalary.apiData.ClerkSalaryUpdateApiForm;
import com.hq.storeMS.service.clerkSalary.apiData.ClerkSalaryUpdateType;
import com.hq.storeMS.service.clerkSalary.data.ClerkSalary;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkSalaryHandler {

	public static ClerkSalaryHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkSalaryHandler.class);
	}
	
	public ReqResult<ClerkSalary> findById(long storeId,long clerkId) {
		ReqResult<ClerkSalary> result = ReqResult.newInstance(false, ClerkSalary.class);
		try {
			ClerkSalary clerkSalary = ClerkSalaryMgr.getInstance().get(storeId,clerkId);
			result.setTarget(clerkSalary);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, clerkId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ClerkSalary, "ClerkSalaryHandler[findById]", reason, e);
		}

		return result;
	}
	
	public ReqResult<ClerkSalary> findByStoreId(long storeId,int pageItemCount,int pageNo) {
		ReqResult<ClerkSalary> result = ReqResult.newInstance(false, ClerkSalary.class);
		try {
			List<ClerkSalary> clerkSalaryList = ClerkSalaryMgr.getInstance().findByStoreId(storeId,pageItemCount,pageNo);
			result.setTargetList(clerkSalaryList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ClerkSalary, "ClerkSalaryHandler[findByStoreId]", reason, e);
		}

		return result;
	}
	
	public ReqResult<ClerkSalary> update(ClerkSalaryUpdateApiForm formInfo) {
		ReqResult<ClerkSalary> result = ReqResult.newInstance(false, ClerkSalary.class);
		ClerkSalaryUpdateType updateType = ClerkSalaryUpdateType.valueOf(formInfo.getUpdateType());
		try {
			boolean success = false;
			
			switch (updateType) {
			case RemoveClerkSalaryRecord:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.SALARY_ADMIN);
				success = ClerkSalaryMgr.getInstance().removeClerkSalaryRecord(formInfo.getStoreId(),formInfo.getClerkId(),formInfo.getRemoveClerkSalaryRecordApiForm());
				break;
			case AddClerkSalaryRecord:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.SALARY_ADMIN);
				success = ClerkSalaryMgr.getInstance().addClerkSalaryRecord(formInfo.getStoreId(),formInfo.getClerkId(),formInfo.getAddClerkSalaryRecordApiForm());
				break;
			case AddClerkPayroll:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.SALARY_ADMIN);
				success = ClerkSalaryMgr.getInstance().addClerkPayroll(formInfo.getStoreId(), formInfo.getClerkId(), formInfo.getAddClerkPayrollApiForm());
				break;

			default:
				break;
			}
			if(success){
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips(updateType.getDescript()+"失败");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ClerkSalary, "ClerkSalaryHandler[update]", reason, e);
		}
		return result;
	}
	
}
