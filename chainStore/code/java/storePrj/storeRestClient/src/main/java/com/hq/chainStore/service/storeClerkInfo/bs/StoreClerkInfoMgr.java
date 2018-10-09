package com.hq.chainStore.service.storeClerkInfo.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.chainStore.service.common.ClientConstants;
import com.hq.chainStore.service.storeClerkInfo.apiData.AddClerkInfoData;
import com.hq.chainStore.service.storeClerkInfo.apiData.AddRoleSet2ClerkData;
import com.hq.chainStore.service.storeClerkInfo.apiData.AddStoreAdminRoleData;
import com.hq.chainStore.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.chainStore.service.storeClerkInfo.apiData.HandleApplyClerkInfoData;
import com.hq.chainStore.service.storeClerkInfo.apiData.HandleGroupApplyClerkData;
import com.hq.chainStore.service.storeClerkInfo.apiData.RemoveStoreAdminRoleData;
import com.hq.chainStore.service.storeClerkInfo.apiData.ReomveClerkData;
import com.hq.chainStore.service.storeClerkInfo.apiData.ReomveRoleOfClerkData;
import com.hq.chainStore.service.storeClerkInfo.apiData.SetMonthPayDaysData;
import com.hq.chainStore.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;
import com.hq.chainStore.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.chainStore.service.storeClerkInfo.apiData.UpdateStoreAdminRoleData;
import com.hq.chainStore.service.storeClerkInfo.data.ApplyClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfoDAO;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoMgr {

	public static StoreClerkInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoMgr.class);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * 
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	private void update(String storeClerkInfoId, StoreClerkInfoUpdateForm updateForm) {
		StoreClerkInfoDAO.getInstance().update(storeClerkInfoId, updateForm);
	}

	public StoreClerkInfo get(String id) {
		return StoreClerkInfoDAO.getInstance().get(id);
	}
	
	public StoreClerkInfo getByStoreId(long storeId) {
		return get(ClientConstants.STORE_CLERKINFO_ID_SUFFFIX+storeId);
	}
	
	public boolean addClerk(long storeId, long bUserId) {
		AddClerkInfoData inputData = AddClerkInfoData.newInstance();
		inputData.setStoreId(storeId);
		inputData.setbUserId(bUserId);
		return StoreClerkInfoDAO.getInstance().addClerk(storeId, inputData);
	}

	public void addStoreAdminRole(StoreAdminRoleInfo4Add addRoleInfo) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		AddStoreAdminRoleData inputData = AddStoreAdminRoleData.newInstance();
		updateForm.setAddStoreAdminRoleData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.AddStoreAdminRole);

		inputData.setStoreId(addRoleInfo.getStoreId());
		inputData.setRole(addRoleInfo.toRole());
		update(addRoleInfo.getStoreClerkInfoId(), updateForm);
	}
	
	//删除岗位信息   用于物理删除  请慎重使用   一般用于删除测试数据 by kevin
	public void removeStoreAdminRole(String storeClerkInfoId, RemoveStoreAdminRoleData removeStoreAdminRoleData) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		updateForm.setRemoveStoreAdminRoleData(removeStoreAdminRoleData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.RemoveStoreAdminRole);
		update(storeClerkInfoId, updateForm);
	}

	public void updateStoreAdminRole(StoreAdminRoleInfo4Update updateRoleInfo) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		UpdateStoreAdminRoleData inputData = UpdateStoreAdminRoleData.newInstance();
		updateForm.setUpdateStoreAdminRoleData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.UpdateStoreAdminRole);

		inputData.setStoreId(updateRoleInfo.getStoreId());
		inputData.setRole(updateRoleInfo.toRole());
		update(updateRoleInfo.getStoreClerkInfoId(), updateForm);
	}
	
	public void applyClerkInfo(long storeId, String storeClerkInfoId, long bUserId) {

		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		ApplyClerkInfoData inputData = ApplyClerkInfoData.newInstance();
		updateForm.setApplyClerkInfoData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.ApplyClerk);

		inputData.setStoreId(storeId);
		inputData.setbUserId(bUserId);
		update(storeClerkInfoId, updateForm);
	}
	
	public void handleApplyClerkInfo(long storeId, String storeClerkInfoId, long bUserId, boolean approved) {
		
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		HandleApplyClerkInfoData inputData = HandleApplyClerkInfoData.newInstance();
		updateForm.setHandleApplyClerkInfoData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.HandleApplyClerk);
		
		inputData.setStoreId(storeId);
		inputData.setbUserId(bUserId);
		inputData.setApproved(approved);
		update(storeClerkInfoId, updateForm);
	}
	

	public void addClerkInfo(long storeId, String storeClerkInfoId, long bUserId, String verifyCode) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		AddClerkInfoData inputData = AddClerkInfoData.newInstance();
		updateForm.setAddClerkInfoData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.AddClerk);
		inputData.setStoreId(storeId);
		inputData.setbUserId(bUserId);
		inputData.setVerifyCode(verifyCode);
		update(storeClerkInfoId, updateForm);
	}

//	public void addRole2Clerk(long storeId, String storeClerkInfoId, long buserId, int roleId) {
//		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
//		AddRole2ClerkData inputData = AddRole2ClerkData.newInstance();
//		updateForm.setAddRole2ClerkData(inputData);
//		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.AddRole2Clerk);
//
//		inputData.setStoreId(storeId);
//		inputData.setBuserId(buserId);
//		inputData.setRoleId(roleId);
//		update(storeClerkInfoId, updateForm);
//	}
	
	//给员工设置多个角色覆盖更新
	public void addRoleSet2Clerk(long storeId, String storeClerkInfoId, long buserId, Set<Integer> roleIdSet) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		AddRoleSet2ClerkData inputData = AddRoleSet2ClerkData.newInstance();
		updateForm.setAddRoleSet2ClerkData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.AddRoleSet2Clerk);

		inputData.setStoreId(storeId);
		inputData.setBuserId(buserId);
		inputData.setRoleIdSet(roleIdSet);
		update(storeClerkInfoId, updateForm);
	}

	public void reomveRoleOfClerk(long storeId, String storeClerkInfoId, long buserId, int roleId) {

		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		ReomveRoleOfClerkData inputData = ReomveRoleOfClerkData.newInstance();
		updateForm.setReomveRoleOfClerkData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.ReomveRoleOfClerk);

		inputData.setStoreId(storeId);
		inputData.setBuserId(buserId);
		inputData.setRoleId(roleId);
		update(storeClerkInfoId, updateForm);
	}
	
	public void reomveClerk(long storeId, String storeClerkInfoId, long buserId) {

		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		ReomveClerkData inputData = ReomveClerkData.newInstance();
		updateForm.setReomveClerkData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.ReomveClerk);

		inputData.setStoreId(storeId);
		inputData.setBuserId(buserId);
		update(storeClerkInfoId, updateForm);
	}
	
	
	/***********************clerkInfo*************************/

	public ClerkInfo getClerk(String storeClerkInfoId,long clerkId) {
		ClerkInfo clerk = null;
		StoreClerkInfo storeClerkInfo = StoreClerkInfoDAO.getInstance().get(storeClerkInfoId);
		if(storeClerkInfo != null){
			Map<Long, ClerkInfo> clerkInfoMap = storeClerkInfo.getClerkInfoMap();
			if(clerkInfoMap.containsKey(clerkId)){
				clerk = clerkInfoMap.get(clerkId);
			}
		}
		return clerk;
	}
	
	public List<ClerkInfo> getClerkList(String storeClerkInfoId) {
		List<ClerkInfo> clerkList = null;
		StoreClerkInfo storeClerkInfo = StoreClerkInfoDAO.getInstance().get(storeClerkInfoId);
		if(storeClerkInfo != null){
			Map<Long, ClerkInfo> clerkInfoMap = storeClerkInfo.getClerkInfoMap();
			Collection<ClerkInfo> collection = clerkInfoMap.values();
			clerkList = new ArrayList<ClerkInfo>(collection);
		}
		return clerkList;
	}
	
	/***********************applyClerkInfo*************************/

	public ApplyClerkInfo getApplyClerk(String storeClerkInfoId,long applyClerkId) {
		ApplyClerkInfo applyClerk = null;
		StoreClerkInfo storeClerkInfo = StoreClerkInfoDAO.getInstance().get(storeClerkInfoId);
		if(storeClerkInfo != null){
			Map<Long, ApplyClerkInfo> applyClerkInfoMap = storeClerkInfo.getApplyClerkInfoMap();
			if(applyClerkInfoMap.containsKey(applyClerkId)){
				applyClerk = applyClerkInfoMap.get(applyClerkId);
			}
		}
		return applyClerk;
	}
	
	public List<ApplyClerkInfo> getApplyClerkList(String storeClerkInfoId) {
		List<ApplyClerkInfo> applyClerkList = null;
		StoreClerkInfo storeClerkInfo = StoreClerkInfoDAO.getInstance().get(storeClerkInfoId);
		if(storeClerkInfo != null){
			Map<Long, ApplyClerkInfo> applyClerkInfoMap = storeClerkInfo.getApplyClerkInfoMap();
			Collection<ApplyClerkInfo> collection = applyClerkInfoMap.values();
			applyClerkList = new ArrayList<ApplyClerkInfo>(collection);
		}
		return applyClerkList;
	}
	
	/***********************storeAdminRole*************************/

	public StoreAdminRole getStoreAdminRole(String storeClerkInfoId, long roleId) {
		StoreAdminRole storeAdminRole = null;
		StoreClerkInfo storeClerkInfo = StoreClerkInfoDAO.getInstance().get(storeClerkInfoId);
		if(storeClerkInfo != null){
			Map<Integer, StoreAdminRole> roleMap = storeClerkInfo.getRoleMap();
			if(roleMap.containsKey((int)roleId)){
				storeAdminRole = roleMap.get((int)roleId);
			}
		}
		return storeAdminRole;
	}
	
	public List<StoreAdminRole> getStoreAdminRoleList(String storeClerkInfoId) {
		List<StoreAdminRole> storeAdminRoleList = null;
		StoreClerkInfo storeClerkInfo = StoreClerkInfoDAO.getInstance().get(storeClerkInfoId);
		if(storeClerkInfo != null){
			Map<Integer, StoreAdminRole> roleMap = storeClerkInfo.getRoleMap();
			Collection<StoreAdminRole> collection = roleMap.values();
			storeAdminRoleList = new ArrayList<StoreAdminRole>(collection);
		}
		return storeAdminRoleList;
	}
	
	/***********************设置月结天数*************************/
	public void setMonthPayDays(long storeId,String storeClerkInfoId, int monthPayDays) {
		SetMonthPayDaysData inputData = SetMonthPayDaysData.newInstance();
		inputData.setStoreId(storeId);
		inputData.setMonthPayDays(monthPayDays);
		
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		updateForm.setSetMonthPayDaysData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.SetMonthPayDays);
		update(storeClerkInfoId,updateForm);
	}
	
	public void HandleGroupApplyClerk(long storeId, String storeClerkInfoId, Set<Long> buserIdSet, boolean approved) {
		StoreClerkInfoUpdateForm updateForm = StoreClerkInfoUpdateForm.newInstance();
		HandleGroupApplyClerkData inputData = HandleGroupApplyClerkData.newInstance();
		updateForm.setHandleGroupApplyClerkData(inputData);
		updateForm.setUpdateTypeEnum(StoreClerkInfoUpdateType.HandleGroupApplyClerk);

		inputData.setStoreId(storeId);
		inputData.setBuserIdSet(buserIdSet);
		inputData.setApproved(approved);
		update(storeClerkInfoId, updateForm);
	}
	
	
}
