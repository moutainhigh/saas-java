package com.hq.storeMS.service.storeMaterialInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.storeMaterialInfo.apiData.UpdateMaterialInfoForm;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeMaterialInfo")
public class StoreMaterialInfo{

	@Id	
	private long id;
	
	private long storeId;
	
	private int materialIdIndex = 0;
	
	private Map<String,MaterialInfo> materialInfoMap = new HashMap<String,MaterialInfo>();
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	public static StoreMaterialInfo newInstance(long storeId){
		StoreMaterialInfo storeMaterialInfo = new StoreMaterialInfo();
		storeMaterialInfo.id = storeId;
		storeMaterialInfo.storeId = storeId;
		long curTime = System.currentTimeMillis();
		
		storeMaterialInfo.createdTime = curTime;
		storeMaterialInfo.lastUpdateTime = curTime;
		
		return storeMaterialInfo;
	}
	
	public boolean addMaterialInfo(MaterialInfo materialInfo){
		if(!materialInfoMap.containsKey(materialInfo.getId()) && StringUtils.equals(materialInfo.getId(), storeId+"_"+(materialIdIndex+1))){
			materialInfoMap.put(materialInfo.getId(), materialInfo);
			materialIdIndex = materialIdIndex + 1;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean updateMaterialInfo(UpdateMaterialInfoForm formInfo){
		return StoreMaterialInfoBeanHelper.getInstance().updateMaterialInfo(materialInfoMap, formInfo);
	}
	
	public boolean removeMaterialInfo(String id){
		return StoreMaterialInfoBeanHelper.getInstance().removeMaterialInfo(materialInfoMap, id);
	}
	
	public boolean addMaterialInventory(String materialId,int count){
		return StoreMaterialInfoBeanHelper.getInstance().addMaterialInventory(materialInfoMap,materialId,count);
	}
	
	public boolean removeMaterialInventory(String materialId,int count){
		return StoreMaterialInfoBeanHelper.getInstance().removeMaterialInventory(materialInfoMap,materialId,count);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getMaterialIdIndex() {
		return materialIdIndex;
	}

	public void setMaterialIdIndex(int materialIdIndex) {
		this.materialIdIndex = materialIdIndex;
	}

	public Map<String, MaterialInfo> getMaterialInfoMap() {
		return materialInfoMap;
	}

	public void setMaterialInfoMap(Map<String, MaterialInfo> materialInfoMap) {
		this.materialInfoMap = materialInfoMap;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}
	
	public void incrVer() {
		this.ver = ver+1;
	}
	
}
