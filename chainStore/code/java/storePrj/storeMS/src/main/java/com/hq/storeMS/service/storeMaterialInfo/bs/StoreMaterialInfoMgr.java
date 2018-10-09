package com.hq.storeMS.service.storeMaterialInfo.bs;

import com.hq.storeMS.service.storeMaterialInfo.apiData.AddMaterialInfoForm;
import com.hq.storeMS.service.storeMaterialInfo.apiData.RemoveMaterialInfoForm;
import com.hq.storeMS.service.storeMaterialInfo.apiData.RemoveMaterialInventoryForm;
import com.hq.storeMS.service.storeMaterialInfo.apiData.UpdateMaterialInfoForm;
import com.hq.storeMS.service.storeMaterialInfo.data.MaterialInfo;
import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMaterialInfoMgr {

	public static StoreMaterialInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoMgr.class);
	}
	
	public void addWithId(StoreMaterialInfo target) {
		StoreMaterialInfoDataHolder.getInstance().addWithId(target);		
	}
	
	private StoreMaterialInfo get(long id){
		StoreMaterialInfo storeMaterialInfo = StoreMaterialInfoDataHolder.getInstance().get(id);
		if(storeMaterialInfo == null){
			storeMaterialInfo = StoreMaterialInfo.newInstance(id);
			addWithId(storeMaterialInfo);
		}
		return storeMaterialInfo;
	}
	
	public StoreMaterialInfo getByStoreId(long storeId){
		long id = storeId;//添加时用storeId作为id
		return get(id);
	}
	
	public boolean addMaterialInfo(long storeId,AddMaterialInfoForm formInfo) {
		boolean success = false;
		StoreMaterialInfo storeMaterialInfo = getByStoreId(storeId);
		if (storeMaterialInfo != null) {
			MaterialInfo materialInfo = MaterialInfo.newInstance(formInfo.getId());
			materialInfo.toMaterial(formInfo);
			success = storeMaterialInfo.addMaterialInfo(materialInfo);
			if (success) {
				StoreMaterialInfoDataHolder.getInstance().update(storeMaterialInfo);
			}
		}

		return success;
	}

	public boolean updateMaterialInfo(long storeId,UpdateMaterialInfoForm formInfo) {
		boolean success = false;
		StoreMaterialInfo storeMaterialInfo = getByStoreId(storeId);
		if (storeMaterialInfo != null) {
			success = storeMaterialInfo.updateMaterialInfo(formInfo);
			if (success) {
				StoreMaterialInfoDataHolder.getInstance().update(storeMaterialInfo);
			}
		}
		return success;
	}
	
	public boolean removeMaterialInfo(long storeId,RemoveMaterialInfoForm inputData) {
		boolean success = false;
		StoreMaterialInfo storeMaterialInfo = getByStoreId(storeId);
		if (storeMaterialInfo != null) {
			success = storeMaterialInfo.removeMaterialInfo(inputData.getId());
			if (success) {
				StoreMaterialInfoDataHolder.getInstance().update(storeMaterialInfo);
			}
		}
		return success;
	}
	
	public void addMaterialInventory(long storeId,String materialId, int count) {
		boolean success = false;
		StoreMaterialInfo storeMaterialInfo = getByStoreId(storeId);
		if (storeMaterialInfo != null) {
			success = storeMaterialInfo.addMaterialInventory(materialId,count);
			if (success) {
				StoreMaterialInfoDataHolder.getInstance().update(storeMaterialInfo);
			}
		}
	}
	
	public boolean removeMaterialInventory(long storeId,RemoveMaterialInventoryForm formInfo) {
		boolean success = false;
		StoreMaterialInfo storeMaterialInfo = getByStoreId(storeId);
		if (storeMaterialInfo != null) {
			success = storeMaterialInfo.removeMaterialInventory(formInfo.getId(),formInfo.getCount());
			if (success) {
				StoreMaterialInfoDataHolder.getInstance().update(storeMaterialInfo);
			}
		}
		return success;
	}
	
}
