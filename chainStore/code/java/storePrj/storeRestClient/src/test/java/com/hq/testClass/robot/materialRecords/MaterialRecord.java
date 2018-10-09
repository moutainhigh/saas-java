package com.hq.testClass.robot.materialRecords;

import java.util.List;

import com.hq.chainStore.service.materialRecords.data.MaterialRecords;
import com.hq.testClass.robot.materialRecords.robot.MaterialRecordsRobot;

public class MaterialRecord {

	private MaterialRecordsRobot robot; 
	
	public static MaterialRecord newInstance(MaterialRecordsRobot robot){
		MaterialRecord materialRecord = new MaterialRecord();
		materialRecord.robot = robot;
		return materialRecord;
	}
	
	public MaterialRecords addMaterialRecords(long storeId,String materialId) {
		return robot.addMaterialRecords(storeId,materialId);
	}
	
	public List<MaterialRecords> findByStoreId(long storeId) {
		return robot.findByStoreId(storeId);
	}
	
	public List<MaterialRecords> findByMaterialId(String materialId) {
		return robot.findByMaterialId(materialId);
	}
	
	public MaterialRecords getById(){
		return robot.getById();
	}
	
	public long getId(){
		return this.robot.getId();
	}
	
}
