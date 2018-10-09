package com.hq.storeMS.service.storeClerkInfo.apiData;

public class StoreClerkInfoUpdateForm {

	private int updateType;

	private AddClerkInfoData addClerkInfoData;
	private ApplyClerkInfoData applyClerkInfoData;
	private HandleApplyClerkInfoData handleApplyClerkInfoData;

	private AddRole2ClerkData addRole2ClerkData;
	private AddStoreAdminRoleData addStoreAdminRoleData;
	private ReomveRoleOfClerkData reomveRoleOfClerkData;
	private UpdateStoreAdminRoleData updateStoreAdminRoleData;
	private RemoveStoreAdminRoleData removeStoreAdminRoleData;
	
	private AddRoleSet2ClerkData addRoleSet2ClerkData;
	private ReomveClerkData reomveClerkData;
	private SetMonthPayDaysData setMonthPayDaysData;
	private HandleGroupApplyClerkData handleGroupApplyClerkData;
	
	public static StoreClerkInfoUpdateForm newInstance(){
		return new StoreClerkInfoUpdateForm();
	}
	
	public StoreClerkInfoUpdateType getStoreClerkUpdateType() {
		return StoreClerkInfoUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}
	
	public void setUpdateTypeEnum(StoreClerkInfoUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}
	public StoreClerkInfoUpdateType getUpdateTypeEnum() {
		return StoreClerkInfoUpdateType.valueOf(this.updateType);
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public AddClerkInfoData getAddClerkInfoData() {
		return addClerkInfoData;
	}

	public void setAddClerkInfoData(AddClerkInfoData addClerkInfoData) {
		this.addClerkInfoData = addClerkInfoData;
	}

	public AddRole2ClerkData getAddRole2ClerkData() {
		return addRole2ClerkData;
	}

	public void setAddRole2ClerkData(AddRole2ClerkData addRole2ClerkData) {
		this.addRole2ClerkData = addRole2ClerkData;
	}

	public AddStoreAdminRoleData getAddStoreAdminRoleData() {
		return addStoreAdminRoleData;
	}

	public void setAddStoreAdminRoleData(AddStoreAdminRoleData addStoreAdminRoleData) {
		this.addStoreAdminRoleData = addStoreAdminRoleData;
	}

	public ReomveRoleOfClerkData getReomveRoleOfClerkData() {
		return reomveRoleOfClerkData;
	}

	public void setReomveRoleOfClerkData(ReomveRoleOfClerkData reomveRoleOfClerkData) {
		this.reomveRoleOfClerkData = reomveRoleOfClerkData;
	}

	public UpdateStoreAdminRoleData getUpdateStoreAdminRoleData() {
		return updateStoreAdminRoleData;
	}

	public void setUpdateStoreAdminRoleData(UpdateStoreAdminRoleData updateStoreAdminRoleData) {
		this.updateStoreAdminRoleData = updateStoreAdminRoleData;
	}

	public ApplyClerkInfoData getApplyClerkInfoData() {
		return applyClerkInfoData;
	}

	public void setApplyClerkInfoData(ApplyClerkInfoData applyClerkInfoData) {
		this.applyClerkInfoData = applyClerkInfoData;
	}

	public HandleApplyClerkInfoData getHandleApplyClerkInfoData() {
		return handleApplyClerkInfoData;
	}

	public void setHandleApplyClerkInfoData(HandleApplyClerkInfoData handleApplyClerkInfoData) {
		this.handleApplyClerkInfoData = handleApplyClerkInfoData;
	}

	public AddRoleSet2ClerkData getAddRoleSet2ClerkData() {
		return addRoleSet2ClerkData;
	}

	public void setAddRoleSet2ClerkData(AddRoleSet2ClerkData addRoleSet2ClerkData) {
		this.addRoleSet2ClerkData = addRoleSet2ClerkData;
	}

	public ReomveClerkData getReomveClerkData() {
		return reomveClerkData;
	}

	public void setReomveClerkData(ReomveClerkData reomveClerkData) {
		this.reomveClerkData = reomveClerkData;
	}

	public SetMonthPayDaysData getSetMonthPayDaysData() {
		return setMonthPayDaysData;
	}

	public void setSetMonthPayDaysData(SetMonthPayDaysData setMonthPayDaysData) {
		this.setMonthPayDaysData = setMonthPayDaysData;
	}

	public HandleGroupApplyClerkData getHandleGroupApplyClerkData() {
		return handleGroupApplyClerkData;
	}

	public void setHandleGroupApplyClerkData(HandleGroupApplyClerkData handleGroupApplyClerkData) {
		this.handleGroupApplyClerkData = handleGroupApplyClerkData;
	}

	public RemoveStoreAdminRoleData getRemoveStoreAdminRoleData() {
		return removeStoreAdminRoleData;
	}

	public void setRemoveStoreAdminRoleData(
			RemoveStoreAdminRoleData removeStoreAdminRoleData) {
		this.removeStoreAdminRoleData = removeStoreAdminRoleData;
	}
}
