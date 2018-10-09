package com.hq.testClass.robot.materialRecords.robot;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsAddApiData;
import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsAddApiForm;
import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsQueryParam;
import com.hq.chainStore.service.materialRecords.bs.MaterialRecordsMgr;
import com.hq.chainStore.service.materialRecords.data.MaterialRecords;

public class MaterialRecordsRobot {
	
	private MaterialRecordsRobotData data;
	
	public static MaterialRecordsRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static MaterialRecordsRobot newInstance(int mark){
		MaterialRecordsRobot robot = new MaterialRecordsRobot();
		robot.data = MaterialRecordsRobotData.newInstance(mark);
		return robot;
	}
	
	public MaterialRecords addMaterialRecords(long storeId,String materialId){
		MaterialRecordsAddApiData materialRecordsAddApiData = MaterialRecordsAddApiData.newInstance();
		materialRecordsAddApiData.setStoreId(storeId);
		materialRecordsAddApiData.setMaterialId(materialId);
		materialRecordsAddApiData.setCount(data.getCount());
		materialRecordsAddApiData.setPrice(data.getPrice());
		materialRecordsAddApiData.setUserName(data.getUserName());
		materialRecordsAddApiData.setImgUrl(data.getImgUrl());
		
		MaterialRecordsAddApiForm materialRecordsAddApiForm = MaterialRecordsAddApiForm.newInstance();
		materialRecordsAddApiForm.addMaterialRecords(materialRecordsAddApiData);
		MaterialRecordsMgr.getInstance().add(materialRecordsAddApiForm);
		
		List<MaterialRecords> findByMaterialId = findByMaterialId(materialId);
		MaterialRecords materialRecords = findByMaterialId.get(0);
		data.setId(materialRecords.getId());
		data.setStoreId(storeId);
		data.setMaterialId(materialId);
		
		MaterialRecords materialRecords1 = getById();
		return materialRecords1;
	}
	
	public MaterialRecords getById(){
		return MaterialRecordsMgr.getInstance().get(data.getId());
	}
	
	public List<MaterialRecords> findByStoreId(long storeId) {
		MaterialRecordsQueryParam queryParam = MaterialRecordsQueryParam.newInstance();
		queryParam.setStoreId(storeId);
		queryParam.setMaxTime(0l);
		queryParam.setMinTime(0l);
		queryParam.setPageItemCount(100);
		queryParam.setPageNo(1);
		List<MaterialRecords> findByStoreId = MaterialRecordsMgr.getInstance().findByStoreId(queryParam);;
		return findByStoreId;
	}
	
	public List<MaterialRecords> findByMaterialId(String materialId) {
		MaterialRecordsQueryParam queryParam = MaterialRecordsQueryParam.newInstance();
		queryParam.setMaterialId(materialId);
		queryParam.setPageItemCount(100);
		queryParam.setPageNo(1);
		List<MaterialRecords> findByMaterialId = MaterialRecordsMgr.getInstance().findByMaterialId(queryParam);
		return findByMaterialId;
	}
	
	public MaterialRecordsRobotData getData() {
		return data;
	}

	public void setData(MaterialRecordsRobotData data) {
		this.data = data;
	}

	public long getId(){
		return this.data.getStoreId();
	}
	
	
	
}
