package com.hq.customerMS.service.store.bs;

import java.util.List;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.customerMS.service.cuser.bs.CUserModifyMgr;
import com.hq.customerMS.service.cuser.bs.CUserQueryMgr;
import com.hq.customerMS.service.cuser.data.CUser;
import com.hq.customerMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeClient.service.store.apiData.JoinStoreForm;
import com.hq.storeClient.service.store.data.Store;
import com.hq.storeClient.service.storeLeaguerInfo.data.Leaguer;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreHandler {

	public static StoreHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreHandler.class);
	}

	public ReqResult<Store> findMyStores(long cuserId){
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			List<Store> list = StoreMgr.getInstance().findMyStores(cuserId);
			result.setSuccess(true);
			result.setTargetList(list);
		} catch (Exception e) {
			final String logId = "StoreHandler[findMyStores]";
			final String reason = LogHelper.getInstance().exceptionReason(cuserId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(LogModule.Store)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Store> getStore(long storeId){
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			Store store = StoreMgr.getInstance().get(storeId);
			result.setSuccess(true);
			result.setTarget(store);
		} catch (Exception e) {
			final String logId = "StoreHandler[getStore]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(LogModule.Store)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<Store> joinStore(JoinStoreForm joinStoreForm) {
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			long cuserId = joinStoreForm.getCuserId();
			long storeId = joinStoreForm.getStoreId();
			CUser user = CUserQueryMgr.getInstance().get(cuserId);
			if(user.getStoreIdSet().contains(storeId)) {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("已加入该店铺，不需重复加入");
				return result;
			}
			String leaguerId = Leaguer.genIdByStoreId(storeId, cuserId);
			LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().get(storeId, leaguerId);
			if(leaguerDetail!=null) {
				user.getStoreIdSet().add(storeId);
				user.getLeaguerIdSet().add(leaguerId);
				CUserModifyMgr.getInstance().update(user);
			}else {
				StoreMgr.getInstance().joinStore(joinStoreForm);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "StoreHandler[joinStore]";
			final String reason = LogHelper.getInstance().exceptionReason(joinStoreForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(LogModule.Store)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
