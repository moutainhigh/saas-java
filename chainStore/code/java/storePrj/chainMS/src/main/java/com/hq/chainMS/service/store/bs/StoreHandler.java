package com.hq.chainMS.service.store.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreHandler {

	public static StoreHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreHandler.class);
	}

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findStoreByCond(StoreQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Store> pageInfo = StoreMgr.getInstance().findStoreByCond(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Store, "StoreHandler[findStoreByCond]", reason, e);
		}
		return result;
	}

	public ReqResult<Store> get(long storeId) {
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			Store store = StoreMgr.getInstance().get(storeId);
			result.setTarget(store);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Store, "StoreHandler[get]", reason, e);
		}
		return result;
	}
}
