package com.hq.storeMS.service.storeMaterialInfo.data;

import java.util.Map;

import com.hq.storeMS.service.storeMaterialInfo.apiData.UpdateMaterialInfoForm;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMaterialInfoBeanHelper {

	public static StoreMaterialInfoBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoBeanHelper.class);
	}
	
	public boolean updateMaterialInfo(Map<String,MaterialInfo> materialInfoMap, UpdateMaterialInfoForm formInfo){
		if(materialInfoMap.containsKey(formInfo.getId())){
			MaterialInfo materialInfo = materialInfoMap.get(formInfo.getId());
			materialInfo.setName(formInfo.getName());
			materialInfo.setReferencePrice(formInfo.getReferencePrice());
			materialInfo.setThreshold(formInfo.getThreshold());
			materialInfo.setImgUrl(formInfo.getImgUrl());
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeMaterialInfo(Map<String,MaterialInfo> materialInfoMap, String id){
		if(materialInfoMap.containsKey(id)){
			materialInfoMap.remove(id);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addMaterialInventory(Map<String,MaterialInfo> materialInfoMap,String materialId, int count){
		if(materialInfoMap.containsKey(materialId)){
			MaterialInfo materialInfo = materialInfoMap.get(materialId);
			materialInfo.addInventory(count);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeMaterialInventory(Map<String,MaterialInfo> materialInfoMap,String materialId, int count){
		if(materialInfoMap.containsKey(materialId)){
			MaterialInfo materialInfo = materialInfoMap.get(materialId);
			materialInfo.removeInventory(count);
			return true;
		}else{
			return false;
		}
	}
	
}
