package com.hq.storeMS.service.storeMaterialInfo.bs;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateForm;
import com.hq.storeMS.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateType;
import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMaterialInfoHandler {

	public static StoreMaterialInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoHandler.class);
	}
	
	public ReqResult<StoreMaterialInfo> getByStoreId(long storeId) {
		ReqResult<StoreMaterialInfo> result = ReqResult.newInstance(false, StoreMaterialInfo.class);
		try {
			StoreMaterialInfo storeMaterialInfo = StoreMaterialInfoMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeMaterialInfo);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.StoreMaterialInfo, "StoreMaterialInfoHandler[get]", reason, e);
		}
		return result;
	}
	
	public ReqResult<StoreMaterialInfo> update(StoreMaterialInfoUpdateForm formInfo) {
		ReqResult<StoreMaterialInfo> result = ReqResult.newInstance(false, StoreMaterialInfo.class);
		try {
			boolean success = false;
			StoreMaterialInfoUpdateType updateType = StoreMaterialInfoUpdateType.valueOf(formInfo.getUpdateType());
			switch (updateType) {
			case AddMaterialInfo:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.MATERIAL_ADMIN);
				success = StoreMaterialInfoMgr.getInstance().addMaterialInfo(formInfo.getStoreId(),formInfo.getAddMaterialInfoForm());
				break;
			case UpdateMaterialInfo:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.MATERIAL_ADMIN);
				success = StoreMaterialInfoMgr.getInstance().updateMaterialInfo(formInfo.getStoreId(),formInfo.getUpdateMaterialInfoForm());
				break;
			case RemoveMaterial:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.MATERIAL_ADMIN);
				success = StoreMaterialInfoMgr.getInstance().removeMaterialInfo(formInfo.getStoreId(),formInfo.getRemoveMaterialInfoForm());
				break;
			case RemoveMaterialInventory:
				BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.MATERIAL_ADMIN);
				success = StoreMaterialInfoMgr.getInstance().removeMaterialInventory(formInfo.getStoreId(),formInfo.getRemoveMaterialInventoryForm());
				break;
			default:
				break;
			}
			if (success) {
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} else {
				result.setTips(updateType.getDescript()+"失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.StoreMaterialInfo;
			final String logId = "StoreMaterialInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
