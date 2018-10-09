package com.hq.testClass.robot.storeMaterialInfo.robot;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeMaterialInfo.apiData.AddMaterialInfoForm;
import com.hq.chainStore.service.storeMaterialInfo.apiData.RemoveMaterialInventoryForm;
import com.hq.chainStore.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateForm;
import com.hq.chainStore.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateType;
import com.hq.chainStore.service.storeMaterialInfo.apiData.UpdateMaterialInfoForm;
import com.hq.chainStore.service.storeMaterialInfo.bs.StoreMaterialInfoMgr;
import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfo;

public class MaterialRobot {
	
	private MaterialRobotData data;
	
	public static MaterialRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 200);
		return newInstance(mark);
	}

	public static MaterialRobot newInstance(int mark){
		MaterialRobot robot = new MaterialRobot();
		robot.data = MaterialRobotData.newInstance(mark);
		return robot;
	}
	
	public StoreMaterialInfo addMaterial(long storeId){
		
		
		StoreMaterialInfoUpdateForm storeMaterialInfoUpdateForm = StoreMaterialInfoUpdateForm.newInstance();
		storeMaterialInfoUpdateForm.setUpdateTypeEnum(StoreMaterialInfoUpdateType.AddMaterialInfo);
		storeMaterialInfoUpdateForm.setStoreId(storeId);
		
		StoreMaterialInfo storeMaterialInfo = getById();
		
		AddMaterialInfoForm addMaterialInfoForm = AddMaterialInfoForm.newInstance();
		addMaterialInfoForm.setId(storeId+"_"+storeMaterialInfo.getMaterialIdIndex());
		addMaterialInfoForm.setName(data.getName());
		addMaterialInfoForm.setReferencePrice(data.getReferencePrice());
		addMaterialInfoForm.setThreshold(data.getThreshold());
		addMaterialInfoForm.setImgUrl(data.getImgUrl());
		storeMaterialInfoUpdateForm.setAddMaterialInfoForm(addMaterialInfoForm);
		
		StoreMaterialInfoMgr.getInstance().update(storeId, storeMaterialInfoUpdateForm);
		
		data.setStoreId(storeId);
		
		storeMaterialInfo = getById();
		return storeMaterialInfo;
	}
	
	public StoreMaterialInfo getById(){
		return StoreMaterialInfoMgr.getInstance().get(data.getStoreId());
	}
	
	public void updateMaterialInfo(String materialId){
		StoreMaterialInfoUpdateForm storeMaterialInfoUpdateForm = StoreMaterialInfoUpdateForm.newInstance();
		storeMaterialInfoUpdateForm.setUpdateTypeEnum(StoreMaterialInfoUpdateType.UpdateMaterialInfo);
		storeMaterialInfoUpdateForm.setStoreId(data.getStoreId());
		
		UpdateMaterialInfoForm updateMaterialInfoForm = UpdateMaterialInfoForm.newInstance();
		updateMaterialInfoForm.setId(materialId);
		updateMaterialInfoForm.setName("修改后的耗材");
		updateMaterialInfoForm.setImgUrl(data.getImgUrl());
		updateMaterialInfoForm.setReferencePrice(data.getReferencePrice());
		
		storeMaterialInfoUpdateForm.setUpdateMaterialInfoForm(updateMaterialInfoForm);
		
		StoreMaterialInfoMgr.getInstance().update(data.getStoreId(), storeMaterialInfoUpdateForm);
	}
	
	public void testUpdateMaterialInventory(String materialId) {
		StoreMaterialInfoUpdateForm storeMaterialInfoUpdateForm = StoreMaterialInfoUpdateForm.newInstance();
		storeMaterialInfoUpdateForm.setUpdateTypeEnum(StoreMaterialInfoUpdateType.RemoveMaterialInventory);
		storeMaterialInfoUpdateForm.setStoreId(data.getStoreId());
		
		RemoveMaterialInventoryForm inventoryForm = RemoveMaterialInventoryForm.newInstance();
		inventoryForm.setId(materialId);
		inventoryForm.setCount(3000);
		storeMaterialInfoUpdateForm.setRemoveMaterialInventoryForm(inventoryForm);
		
		StoreMaterialInfoMgr.getInstance().update(data.getStoreId(), storeMaterialInfoUpdateForm);
	}

	public MaterialRobotData getData() {
		return data;
	}

	public void setData(MaterialRobotData data) {
		this.data = data;
	}

	public long getId(){
		return this.data.getStoreId();
	}
	
	
	
}
