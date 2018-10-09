package com.hq.storeMS.service.store.bs.update;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.buser.BUserPwdHelper;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BUserBeanHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.store.apiData.StoreUpdateApiForm;
import com.hq.storeMS.service.store.apiData.StoreUpdateChainData;
import com.hq.storeMS.service.store.apiData.StoreUpdateStatusData;
import com.hq.storeMS.service.store.apiData.StoreUpdateType;
import com.hq.storeMS.service.store.apiData.UpdateStoreInfoApiData;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreOperateEnum;
import com.hq.storeMS.service.store.data.StoreState;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveClerkData;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.bs.update.ClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreUpdateMgr {

	public static StoreUpdateMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreUpdateMgr.class);
	}
	
	public OperateTips updateStoreChainData(StoreUpdateChainData inputForm){
		OperateTips tips = OperateTips.newInstance(false, StoreUpdateType.StoreUpdateChainData.getDescript()+"失败");
		long storeId = inputForm.getStoreId();
		Store store = StoreMgr.getInstance().get(storeId);
		if(store!=null){
			if(inputForm.getOperate() == StoreOperateEnum.JoinChainHandler.ordinal()) {
				store.getChainIds().add(inputForm.getChainId());
			}else if(inputForm.getOperate() == StoreOperateEnum.RelieveStore.ordinal()){
				store.getChainIds().remove(inputForm.getChainId());
			}
			StoreMgr.getInstance().update(store);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	public OperateTips updateStoreInfo(StoreUpdateApiForm formInfo){
		OperateTips tips = OperateTips.newInstance(false, StoreUpdateType.UpdateStoreInfo.getDescript()+"失败");
		UpdateStoreInfoApiData inputData = formInfo.getUpdateStoreInfoApiData();
		long storeId = inputData.getStoreId();
		Store store = StoreMgr.getInstance().get(storeId);
		if(store!=null){
			inputData.update(store);
			StoreMgr.getInstance().update(store);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	public OperateTips updateStatus(StoreUpdateApiForm formInfo){
		OperateTips tips = OperateTips.newInstance(false, StoreUpdateType.StoreUpdateStatusData.getDescript()+"失败");
		StoreUpdateStatusData updateStatusData = formInfo.getUpdateStatusData();
		
		StoreState storeState = StoreState.valueOf(updateStatusData.getState());
		switch (storeState) {
		case Close:
			Store store = StoreMgr.getInstance().get(updateStatusData.getStoreId());
			BUser buser = BUserQueryMgr.getInstance().getSimple(store.getBossId());
			String password = updateStatusData.getPassword();
			if(StringUtils.isNotBlank(password) && checkPassword(buser, password)){
				store.setState(updateStatusData.getState());
				StoreMgr.getInstance().update(store);
				tips.setSuccess(true);
				closeStoreCallback(store);//同时删除店铺员工信息
				//清除老板的storeId  
				BUserBeanHelper.getInstance().removeStoreId4BUser(buser, updateStatusData.getStoreId());
				BUserModifyMgr.getInstance().update(buser);
			}else{
				tips.setTips("密码错误");
			}
			break;

		default:
			break;
		}
		
		return tips;
	}

	private void closeStoreCallback(Store store) {
		try {
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(store.getId());
			Map<Long, ClerkInfo> clerkInfoMap = storeClerkInfo.getClerkInfoMap();
			for (long clerkId : clerkInfoMap.keySet()) {
				if(clerkId != store.getBossId()){//非老板员工
					ReomveClerkData removeClerkData = ReomveClerkData.newInstance();
					removeClerkData.setStoreId(store.getId());
					removeClerkData.setBuserId(clerkId);
					ClerkInfoMgr.getInstance().reomveClerk(removeClerkData);
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Store, "StoreUpdateHandle[closeStoreCallback]", "删除店铺员工信息失败", e);
		}
	}
	
	private boolean checkPassword(BUser buser,String password){
		String encryptPassword = BUserPwdHelper.getInstance().getEncryptPassword(buser, password);
		if(StringUtils.equals(encryptPassword, buser.getPassword())){
			return true;
		}else{
			return false;
		}
	}
	
}
