package com.hq.storeMS.service.saas.bs;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.auth.opUser.OPUserAuthUtils;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminPermEnum;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminRole;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminRoleState;
import com.hq.storeMS.service.saas.apiData.OPAdminRoleAddApiForm;
import com.hq.storeMS.service.saas.apiData.OPAdminRoleUpdateApiForm;
import com.hq.storeMS.service.saas.apiData.OPAdminRoleUpdateType;
import com.hq.storeMS.service.saas.apiData.OPStoreQueryApiForm;
import com.hq.storeMS.service.saas.apiData.OPStoreUpdateApiForm;
import com.hq.storeMS.service.saas.apiData.OPStoreUpdateStateApiData;
import com.hq.storeMS.service.saas.apiData.OPStoreUpdateType;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.store.data.StoreState;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class SaasHandler {

	public static SaasHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SaasHandler.class);
	}

	public ReqResult<OPAdminRole> addOPAdminRole(OPAdminRoleAddApiForm inputForm) {
		ReqResult<OPAdminRole> result = ReqResult.newInstance(false, OPAdminRole.class);
		try {
			if (StringUtils.isBlank(inputForm.getName())) {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("姓名不能为空");
			} else {
				OPAdminRole role = inputForm.toRole();
				role.setStateEnum(OPAdminRoleState.Available);
				SaasMgr.getInstance().addOPAdminRole(role);

				result.setTarget(role);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}

		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[addOPAdminRole]", reason, e);
		}

		return result;
	}

	public ReqResult<OPAdminRole> getOPAdminRole(int roleId) {
		ReqResult<OPAdminRole> result = ReqResult.newInstance(false, OPAdminRole.class);
		try {
			OPAdminRole role = SaasMgr.getInstance().getOPAdminRole(roleId);
			result.setTarget(role);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(roleId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[getOPAdminRole]", reason, e);
		}
		return result;
	}


	public ReqResult<OPAdminRole> listOPAdminRole() {
		ReqResult<OPAdminRole> result = ReqResult.newInstance(false, OPAdminRole.class);
		try {
			List<OPAdminRole> roleList = SaasMgr.getInstance().listOPAdminRole();
			result.setTargetList(roleList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[listOPAdminRole]", "", e);
		}

		return result;
	}

	public ReqResult<OPAdminRole> update(OPAdminRoleUpdateApiForm inputForm) {
		ReqResult<OPAdminRole> result = ReqResult.newInstance(false, OPAdminRole.class);
		try {
			OPAdminRoleUpdateType updateType = inputForm.getUpdateTypeEnum();
			boolean success = false;
			switch (updateType) {
			case updateInfo:
				success = SaasMgr.getInstance().updateOPAdminRole(inputForm.getUpdateData()); 
				break;
			default:
				break;
			}
			if (success) {
				result.setSuccess(true);
			} else {
				result.setTips("saas角色信息操作失败");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[update]", reason, e);
		}
		return result;
	}

	public ReqResult<Store> updateStore(long storeId,OPStoreUpdateApiForm inputForm) {
		ReqResult<Store> result = ReqResult.newInstance(false, Store.class);
		try {
			OPStoreUpdateType updateType = inputForm.getUpdateTypeEnum();
			boolean success = false;
			switch (updateType) {
			case updateState:
				success = updateStoreState(inputForm.getUpdateData(), result);
				break;
			default:
				break;
			}
			if (success) {
				result.setSuccess(true);
			} else {
				result.setTips("店铺信息操作失败");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, inputForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[updateStore]", reason, e);
		}
		return result;
	}
	
	
	public ReqResult<StoreRO> list(int pageItemCount,int pageNo) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			List<StoreRO> allList = StoreMgr.getInstance().getPage(pageItemCount, pageNo);
			result.setTargetList(allList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[list]", reason, e);
		}
		return result;
		
	}
	
	public ReqResult<StoreRO> findById(long id) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			StoreRO store = StoreMgr.getInstance().getReadOnly(id);
			if(store!=null){
				result.setTarget(store);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[findById]", reason, e);
		}
		return result;
	}
	
	//自动审核
	public void initCheckStore(){
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				checkStore();
			}
		};
		timer.schedule(timerTask, 10000, 1000*60);
		
	}

	private void checkStore() {
		final int state = 1;
		final int pageItemCount = 10;
		final int pageNo = 1;
		
		try {
			OPStoreQueryApiForm opStoreQueryApiForm = OPStoreQueryApiForm.newInstance();
			opStoreQueryApiForm.setState(state);
			opStoreQueryApiForm.setPageItemCount(pageItemCount);
			opStoreQueryApiForm.setPageNo(pageNo);
			List<StoreRO> storeList = StoreMgr.getInstance().findStoreList(opStoreQueryApiForm);
			
			for (StoreRO storeRO : storeList) {
				long storeId = storeRO.getId();
				boolean approved = true;
				boolean success = StoreMgr.getInstance().approveStore(storeId, approved);
				Store store = StoreMgr.getInstance().get(storeId);
				if(success && store.getState() == StoreState.Open.ordinal()){
					StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
					//不存在才添加，否则不处理。
					if(storeClerkInfo == null){
						storeClerkInfo = StoreClerkInfo.newInstance(storeId,store.getBossId());
						StoreClerkInfoMgr.getInstance().addWithId(storeClerkInfo);
					}
				
					store.setClerkInfoId(storeClerkInfo.getId());
					StoreMgr.getInstance().update(store);
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Saas, "SaasHandler[checkStore]", "", e);
		}
		
	}
	
	public ReqResult<StoreRO> findStoreList(OPStoreQueryApiForm queryForm) {
		ReqResult<StoreRO> result = ReqResult.newInstance(false, StoreRO.class);
		try {
			List<StoreRO> storeList = StoreMgr.getInstance().findStoreList(queryForm);
			result.setTargetList(storeList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[findStoreList]", reason, e);
		}
		return result;
	}

	private boolean updateStoreState(OPStoreUpdateStateApiData updateData,ReqResult<Store> result) {
		OPUserAuthUtils.getInstance().checkPermission(OPAdminPermEnum.OP_STORE_CHECKER);
		long storeId = updateData.getId();
		boolean success = StoreMgr.getInstance().approveStore(storeId, updateData.isApproved());
		StoreRO store = StoreMgr.getInstance().getReadOnly(storeId);
		if(success && store.getState() == StoreState.Open.ordinal()){
			openStore(storeId, result);
		}
		
		return true;
	}
	

	private void openStore(long storeId, ReqResult<Store> result) {
		try {
			Store store = StoreMgr.getInstance().get(storeId);
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
			//不存在才添加，否则不处理。
			if(storeClerkInfo == null){
				storeClerkInfo = StoreClerkInfo.newInstance(storeId,store.getBossId());
				StoreClerkInfoMgr.getInstance().addWithId(storeClerkInfo);
			}
			store.setClerkInfoId(storeClerkInfo.getId());
			StoreMgr.getInstance().update(store);
			result.setTarget(store);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Saas, "SaasHandler[openStore]", reason, e);
		}
	}
}
