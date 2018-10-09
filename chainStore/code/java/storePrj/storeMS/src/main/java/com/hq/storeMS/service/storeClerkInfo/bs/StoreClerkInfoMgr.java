package com.hq.storeMS.service.storeClerkInfo.bs;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddRoleSet2ClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.HandleApplyClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveRoleOfClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.SetMonthPayDaysData;
import com.hq.storeMS.service.storeClerkInfo.apiData.UpdateStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.data.ApplyClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.ApplyState;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoMgr {

	public static StoreClerkInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoMgr.class);
	}
	
	public void addWithId(StoreClerkInfo target) {
		StoreClerkInfoDataHolder.getInstance().addWithId(target);
	}

	public void delete(StoreClerkInfo target) {
		StoreClerkInfoDataHolder.getInstance().delete(target);
	}

	public StoreClerkInfo getByStoreId(long storeId) {
		String id = StoreClerkInfo.getIdByStoreId(storeId);
		return get(id);
	}
	
	public StoreClerkInfo get(String id) {
		return StoreClerkInfoDataHolder.getInstance().get(id);
	}
	
	public void update(StoreClerkInfo storeClerkInfo){
		StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
	}

	public boolean addStoreAdminRole(AddStoreAdminRoleData inputData) {
		boolean success = false;
		long storeId = inputData.getStoreId(); 
		StoreAdminRole role = inputData.getRole();
		
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);

		if (storeClerkInfo != null) {
			success = storeClerkInfo.addStoreAdminRole(role);
			if (success) {
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}
		}

		return success;
	}

	public boolean updateStoreAdminRole(UpdateStoreAdminRoleData inputData) {
		long storeId = inputData.getStoreId(); 
		StoreAdminRole role = inputData.getRole();
		
		boolean success = false;
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			success = storeClerkInfo.updateStoreAdminRole(role);
			if (success) {
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}
		}

		return success;
	}
	
	public OperateTips applyClerkInfo(ApplyClerkInfoData inputData) {
		OperateTips operateTips = OperateTips.newInstance();
		long storeId = inputData.getStoreId(); 
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			ApplyClerkInfo applyClerkInfo = ApplyClerkInfo.newInstance(inputData.getbUserId(),storeId);
			boolean success = storeClerkInfo.applyClerkInfo(applyClerkInfo);
			if (success) {
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}else{
				operateTips.setSuccess(false);
				operateTips.setTips("已申请加入店铺,不能重复申请");
			}
		}
		return operateTips;
	}
	
	public ApplyClerkInfo handleApplyClerkInfo(HandleApplyClerkInfoData inputData) {
		long storeId = inputData.getStoreId(); 
		
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		ApplyClerkInfo applyClerkInfo = null;
		if (storeClerkInfo != null) {
			applyClerkInfo = storeClerkInfo.handleApplyClerkInfo(inputData.getbUserId(), inputData.isApproved());
			ClerkInfo clerkInfo = ClerkInfo.newInstance(inputData.getbUserId());
			if(applyClerkInfo.getStateEnum() == ApplyState.Approved){
				storeClerkInfo.addClerkInfo(clerkInfo);
			}
			StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
		}
		return applyClerkInfo;
	}
	
	public ApplyClerkInfo HandleGroupApplyClerk(long storeId,long buserId,boolean approved) {
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		ApplyClerkInfo applyClerkInfo = null;
		if (storeClerkInfo != null) {
			applyClerkInfo = storeClerkInfo.handleApplyClerkInfo(buserId, approved);
			ClerkInfo clerkInfo = ClerkInfo.newInstance(buserId);
			if(applyClerkInfo.getStateEnum() == ApplyState.Approved){
				storeClerkInfo.addClerkInfo(clerkInfo);
			}
			StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
		}
		return applyClerkInfo;
	}

	public OperateTips addClerkInfo(AddClerkInfoData inputData) {
		OperateTips operateTips = OperateTips.newInstance();
		long storeId = inputData.getStoreId(); 
		ClerkInfo clerkInfo = ClerkInfo.newInstance(inputData.getbUserId());
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			boolean success = storeClerkInfo.addClerkInfo(clerkInfo);
			if(success){
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}else{
				operateTips.setSuccess(false);
				operateTips.setTips("已加入店铺,不能重复加入");
			}
		}
		return operateTips;
	}

	public boolean addRoleSet2Clerk(AddRoleSet2ClerkData inputData) {
		long storeId = inputData.getStoreId(); 
		boolean success = false;
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			long clerkBUserId = inputData.getBuserId();
			success = storeClerkInfo.addRoleSet2Clerk(clerkBUserId, inputData.getRoleIdSet());
			if(success){
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}
		}
		return success;
	}

	public boolean reomveRoleOfClerk(ReomveRoleOfClerkData inputData) {
		long storeId = inputData.getStoreId(); 
		boolean success = false;
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			long clerkBUserId = inputData.getBuserId();
			StoreAdminRole role = storeClerkInfo.getRole(inputData.getRoleId());
			success = storeClerkInfo.removeRoleOfClerk(clerkBUserId, role);
			if(success){
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}
		}
		return success;
	}
	
	public boolean reomveClerk(ReomveClerkData inputData) {
		long storeId = inputData.getStoreId(); 
		boolean success = false;
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			long clerkBUserId = inputData.getBuserId();
			success = storeClerkInfo.removeClerk(clerkBUserId);
			if(success){
				//同时删除员工申请信息
				storeClerkInfo.removeApplyClerk(clerkBUserId);
				StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			}
		}
		return success;
	}
	
	public boolean setMonthPayDays(SetMonthPayDaysData inputData) {
		long storeId = inputData.getStoreId(); 
		boolean success = false;
		StoreClerkInfo storeClerkInfo = getByStoreId(storeId);
		if (storeClerkInfo != null) {
			storeClerkInfo.setMonthPayDays(inputData.getMonthPayDays());
			StoreClerkInfoDataHolder.getInstance().update(storeClerkInfo);
			success = true;
		}
		return success;
	}

}
