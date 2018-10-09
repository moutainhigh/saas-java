package com.hq.chainClient.service.chainClerk.apiData;

public class ChainClerkUpdateForm {
	private int updateType;

	private AdminRoleAddForm adminRoleAddForm;
	private AdminRoleUpdateForm adminRoleUpdateForm;
	private AdminRoleRemoveForm adminRoleRemoveForm;
	
	private ClerkRoleSaveForm clerkRoleSaveForm;
	private ChainClerkAddForm chainClerkAddForm;
	private ChainClerkReomveForm chainClerkReomveForm;
	private ChainClerkUpdateInfoForm chainClerkUpdateInfoForm;
	
	private ChainAllotStoreForm chainAllotStoreForm;
	private ChainBatchAllotStoreForm chainBatchAllotStoreForm;
	
	public static ChainClerkUpdateForm newInstance(){
		return new ChainClerkUpdateForm();
	}
	
	public ChainClerkUpdateType getChainClerkUpdateType() {
		return ChainClerkUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public AdminRoleAddForm getAdminRoleAddForm() {
		return adminRoleAddForm;
	}

	public void setAdminRoleAddForm(AdminRoleAddForm adminRoleAddForm) {
		this.adminRoleAddForm = adminRoleAddForm;
	}

	public AdminRoleUpdateForm getAdminRoleUpdateForm() {
		return adminRoleUpdateForm;
	}

	public void setAdminRoleUpdateForm(AdminRoleUpdateForm adminRoleUpdateForm) {
		this.adminRoleUpdateForm = adminRoleUpdateForm;
	}

	public AdminRoleRemoveForm getAdminRoleRemoveForm() {
		return adminRoleRemoveForm;
	}

	public void setAdminRoleRemoveForm(AdminRoleRemoveForm adminRoleRemoveForm) {
		this.adminRoleRemoveForm = adminRoleRemoveForm;
	}

	public ChainClerkReomveForm getChainClerkReomveForm() {
		return chainClerkReomveForm;
	}

	public void setChainClerkReomveForm(ChainClerkReomveForm chainClerkReomveForm) {
		this.chainClerkReomveForm = chainClerkReomveForm;
	}

	public ChainClerkAddForm getChainClerkAddForm() {
		return chainClerkAddForm;
	}

	public void setChainClerkAddForm(ChainClerkAddForm chainClerkAddForm) {
		this.chainClerkAddForm = chainClerkAddForm;
	}

	public ClerkRoleSaveForm getClerkRoleSaveForm() {
		return clerkRoleSaveForm;
	}

	public void setClerkRoleSaveForm(ClerkRoleSaveForm clerkRoleSaveForm) {
		this.clerkRoleSaveForm = clerkRoleSaveForm;
	}

	public ChainClerkUpdateInfoForm getChainClerkUpdateInfoForm() {
		return chainClerkUpdateInfoForm;
	}

	public void setChainClerkUpdateInfoForm(ChainClerkUpdateInfoForm chainClerkUpdateInfoForm) {
		this.chainClerkUpdateInfoForm = chainClerkUpdateInfoForm;
	}

	public ChainAllotStoreForm getChainAllotStoreForm() {
		return chainAllotStoreForm;
	}

	public void setChainAllotStoreForm(ChainAllotStoreForm chainAllotStoreForm) {
		this.chainAllotStoreForm = chainAllotStoreForm;
	}

	public ChainBatchAllotStoreForm getChainBatchAllotStoreForm() {
		return chainBatchAllotStoreForm;
	}

	public void setChainBatchAllotStoreForm(ChainBatchAllotStoreForm chainBatchAllotStoreForm) {
		this.chainBatchAllotStoreForm = chainBatchAllotStoreForm;
	}
	
}
