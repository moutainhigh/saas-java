package com.hq.storeMS.service.storeBeauticianInfo.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.AddBeauticianInfoData;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateForm;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateType;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoHandler {

	public static StoreBeauticianInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoHandler.class);
	}

	public ReqResult<StoreBeauticianInfo> get(long id) {
		ReqResult<StoreBeauticianInfo> result = ReqResult.newInstance(false, StoreBeauticianInfo.class);
		try {
			StoreBeauticianInfo storeBeauticianInfo = StoreBeauticianInfoMgr.getInstance().getByStoreId(id);
			result.setTarget(storeBeauticianInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreBeauticianInfo, "StoreBeauticianInfoHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<StoreBeauticianInfo> update(StoreBeauticianInfoUpdateForm formInfo) {
		ReqResult<StoreBeauticianInfo> result = ReqResult.newInstance(false, StoreBeauticianInfo.class);
		try {
			StoreBeauticianInfoUpdateType updateType = formInfo.getUpdateTypeEnum();
			boolean success = false;
			switch (updateType) {
				case AddBeauticianInfo:
					AddBeauticianInfoData addBeauticianInfoData = formInfo.getAddBeauticianInfoData();
//					BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.BEAUTICIAN_ADMIN);
					success = StoreBeauticianInfoMgr.getInstance().addBeauticianInfo(formInfo.getStoreId(),addBeauticianInfoData);
					addStoreId2Beautician(formInfo.getStoreId(),addBeauticianInfoData.getBuserId());
					break;
				case UpdateBeauticianInfo:
//					BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.BEAUTICIAN_ADMIN);
					success = StoreBeauticianInfoMgr.getInstance().updateBeauticianInfo(formInfo.getStoreId(),formInfo.getUpdateBeauticianInfoData());
					break;
				case RemoveBeauticianInfo:
//					BUserAuthUtils.getInstance().checkPermission(formInfo.getStoreId(), StoreAdminPermEnum.BEAUTICIAN_ADMIN);
					success = StoreBeauticianInfoMgr.getInstance().removeBeauticianInfo(formInfo.getStoreId(),formInfo.getRemoveBeauticianInfoData());
					break;
				default:
					break;
			}
			if (success) {
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} else {
				result.setTips(updateType.getDescript()+"失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}

		} catch (Exception e) {
			final LogModule logModule = LogModule.StoreBeauticianInfo;
			final String logId = "StoreBeauticianInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
			
		}

		return result;
	}

	private void addStoreId2Beautician(long storeId,long buserId) {
		BUser buser = BUserQueryMgr.getInstance().get(buserId);
		buser.addStoreId(storeId);
		BUserModifyMgr.getInstance().update(buser);
	}


}
