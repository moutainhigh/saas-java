package com.hq.storeMS.service.store.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.scheduleTask.StoreTaskMgr;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.cuser.bs.CUserMgr;
import com.hq.storeMS.service.store.apiData.JoinStoreForm;
import com.hq.storeMS.service.store.apiData.StoreAddApiForm;
import com.hq.storeMS.service.store.apiData.StoreFindTypeEnum;
import com.hq.storeMS.service.store.apiData.StoreQueryForm;
import com.hq.storeMS.service.store.apiData.StoreUpdateApiForm;
import com.hq.storeMS.service.store.bs.update.StoreHandlerHelper;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.store.data.StoreState;
import com.hq.storeMS.service.storeLeaguerInfo.bs.update.LeaguerInfoMgr;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreHandler {

	public static StoreHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreHandler.class);
	}

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findStoreByCond(StoreQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<StoreRO> pageInfo = StoreMgr.getInstance().findStoreByCond(queryForm);
			result.setSuccess(true);
			result.setTarget(pageInfo);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Store, "CmStoreHandler[findStoreByCond]", "", e);
		}
		return result;
	}

	public ReqResult<StoreRO> list(int pageItemCount, int pageNo) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			List<StoreRO> allList = StoreMgr.getInstance().getPage(pageItemCount, pageNo);
			result.setTargetList(allList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Store, "StoreHandler[list]", reason, e);
		}
		return result;
	}

	public ReqResult<StoreRO> get(long id) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			StoreRO store = StoreMgr.getInstance().getReadOnly(id);
			if (store != null) {
				result.setTarget(store);
				result.setSuccess(true);
			} else {
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("店铺不存在");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Store, "StoreHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<StoreRO> findBUserStores(long buserId, int pageItemCount, int pageNo, int findType) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			BUser buser = BUserQueryMgr.getInstance().getSimple(buserId);
			Set<Long> storeIdSet = buser.getStoreIdSet();
			List<StoreRO> storeList = StoreMgr.getInstance().findByIdList(storeIdSet, pageItemCount, pageNo);
			// 根据type区分自己店铺和加入店铺
			List<StoreRO> owneredList = new ArrayList<StoreRO>();
			List<StoreRO> joinedList = new ArrayList<StoreRO>();
			for (StoreRO item : storeList) {
				if (buserId == item.getBossId()) {
					owneredList.add(item);
				} else {
					joinedList.add(item);
				}
			}

			StoreFindTypeEnum storeFindType = StoreFindTypeEnum.valueOf(findType);
			switch (storeFindType) {
			case All:
				result.setTargetList(storeList);
				break;
			case Ownered:
				result.setTargetList(owneredList);
				break;
			case Joined:
				result.setTargetList(joinedList);
				break;
			default:
				break;
			}
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(buserId, pageItemCount, pageNo, findType);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Store, "StoreHandler[findBUserStores]", reason, e);
		}

		return result;
	}

	public ReqResult<StoreRO> findByName(String name, int pageItemCount, int pageNo) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			List<StoreRO> storeList = StoreMgr.getInstance().findByName(name, pageItemCount, pageNo);
			result.setTargetList(storeList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(name, pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Store, "StoreHandler[findByName]", reason, e);
		}
		return result;
	}

	public ReqResult<Store> add(StoreAddApiForm formInfo) {
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			// 验证店铺数量
			if (StoreVipMgr.getInstance().isStoreLimited(formInfo.getBossId())) {
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("当前店铺数量已达上线");
				return result;
			}
			if (checkForm4Add(formInfo, result)) {
				Store store = formInfo.toStore();
				store.setState(StoreState.Open.ordinal());
				StoreMgr.getInstance().addAndReturnId(store);
				BUser boss = BUserQueryMgr.getInstance().get(store.getBossId());
				boss.addStoreId(store.getId());
				BUserModifyMgr.getInstance().update(boss);
				result.setTarget(store);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
				StoreMgr.getInstance().initStoreCallBack(store);
				StoreTaskMgr.getInstance().storeDataCallBack(store);
			} else {
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("添加失败");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Store, "StoreHandler[add]", reason, e);
		}
		return result;
	}

	private boolean checkForm4Add(StoreAddApiForm formInfo, ReqResult<Store> result) {
		boolean success = true;
		if (StringUtils.isBlank(formInfo.getName())) {
			result.setStatus(RespStatus.INVALID_REQUEST);
			result.setTips("name is blank.");
			success = false;
		}
		return success;

	}

	public ReqResult<Store> update(StoreUpdateApiForm formInfo) {
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			OperateTips operateTips = StoreHandlerHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.Store;
			final String logId = "StoreHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Store> joinStoreForCuser(JoinStoreForm joinStoreForm) {
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			OperateTips operateTips = LeaguerInfoMgr.getInstance().addLeaguer4CuserJoin(joinStoreForm);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(joinStoreForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Store, "CmStoreHandler[joinStoreForCuser]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Store> findByCuser(long cuserId){
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			CUser cuser = CUserMgr.getInstance().get(cuserId);
			Set<Long> storeIds = cuser.getStoreIdSet();
			int pageItemCount=storeIds.size();
			int pageNo=1;
			List<StoreRO> stores = StoreMgr.getInstance().findByIdList(storeIds, pageItemCount, pageNo);
			List<Store> list = new ArrayList<Store>();
			for (StoreRO storeRO : stores) {
				list.add((Store)storeRO);
			}
			result.setSuccess(true);
			result.setTargetList(list);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Store, "CmStoreHandler[findByCuser]", "", e);
		}
		return result;
	}
}
