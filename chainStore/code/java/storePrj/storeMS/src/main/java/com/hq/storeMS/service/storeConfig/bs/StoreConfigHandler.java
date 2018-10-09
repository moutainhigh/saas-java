package com.hq.storeMS.service.storeConfig.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateForm;
import com.hq.storeMS.service.storeConfig.bs.update.ConfigHandlerHelper;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.List;

public class StoreConfigHandler {

	public static StoreConfigHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigHandler.class);
	}
	
	private final LogModule logModule = LogModule.StoreConfig;
	
	public ReqResult<StoreConfig> getByStoreId(long storeId) {
		ReqResult<StoreConfig> result = ReqResult.newInstance(false, StoreConfig.class);
		try {
			StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
			result.setTarget(storeConfig);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreConfig, "StoreConfigHandler[getByStoreId]", reason, e);
		}
		return result;
	}
	
	public ReqResult<StoreConfig> update(long storeId, StoreConfigUpdateForm formInfo) {
		ReqResult<StoreConfig> result = ReqResult.newInstance(false, StoreConfig.class);
		try {
			OperateTips operateTips = ConfigHandlerHelper.getInstance().update(storeId, formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "StoreConfigHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}


	public ReqResult<StoreConfig> getListByStoreIds(String storeIds) {
		ReqResult<StoreConfig> result = ReqResult.newInstance(false, StoreConfig.class);
		try {
			List<StoreConfig> storeConfigs = StoreConfigMgr.getInstance().getListByStoreIds(storeIds);
			result.setTargetList(storeConfigs);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeIds);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.StoreConfig, "StoreConfigHandler[getListByStoreIds]", reason, e);
		}
		return result;
	}

}
