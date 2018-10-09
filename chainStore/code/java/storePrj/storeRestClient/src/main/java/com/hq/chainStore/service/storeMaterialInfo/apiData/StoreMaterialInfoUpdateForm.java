package com.hq.chainStore.service.storeMaterialInfo.apiData;

public class StoreMaterialInfoUpdateForm {

	private int updateType;
	
	private long storeId;

	private AddMaterialInfoForm addMaterialInfoForm;
	
	private UpdateMaterialInfoForm updateMaterialInfoForm;
	
	private RemoveMaterialInfoForm removeMaterialInfoForm;
	
	private RemoveMaterialInventoryForm removeMaterialInventoryForm;
	
	public static StoreMaterialInfoUpdateForm newInstance(){
		return new StoreMaterialInfoUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}
	
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	
	public StoreMaterialInfoUpdateType getUpdateTypeEnum() {
		return StoreMaterialInfoUpdateType.valueOf(this.updateType);
	}
	
	public void setUpdateTypeEnum(StoreMaterialInfoUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public AddMaterialInfoForm getAddMaterialInfoForm() {
		return addMaterialInfoForm;
	}

	public void setAddMaterialInfoForm(AddMaterialInfoForm addMaterialInfoForm) {
		this.addMaterialInfoForm = addMaterialInfoForm;
	}

	public UpdateMaterialInfoForm getUpdateMaterialInfoForm() {
		return updateMaterialInfoForm;
	}

	public void setUpdateMaterialInfoForm(UpdateMaterialInfoForm updateMaterialInfoForm) {
		this.updateMaterialInfoForm = updateMaterialInfoForm;
	}

	public RemoveMaterialInfoForm getRemoveMaterialInfoForm() {
		return removeMaterialInfoForm;
	}

	public void setRemoveMaterialInfoForm(RemoveMaterialInfoForm removeMaterialInfoForm) {
		this.removeMaterialInfoForm = removeMaterialInfoForm;
	}

	public RemoveMaterialInventoryForm getRemoveMaterialInventoryForm() {
		return removeMaterialInventoryForm;
	}

	public void setRemoveMaterialInventoryForm(RemoveMaterialInventoryForm removeMaterialInventoryForm) {
		this.removeMaterialInventoryForm = removeMaterialInventoryForm;
	}

}
