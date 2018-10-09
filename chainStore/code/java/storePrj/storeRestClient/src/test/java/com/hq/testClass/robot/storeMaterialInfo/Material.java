package com.hq.testClass.robot.storeMaterialInfo;

import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.testClass.robot.storeMaterialInfo.robot.MaterialRobot;

public class Material {

	private MaterialRobot robot; 
	
	public static Material newInstance(MaterialRobot robot){
		Material material = new Material();
		material.robot = robot;
		return material;
	}
	
	public StoreMaterialInfo addMaterial(long storeId) {
		StoreMaterialInfo storeMaterialInfo = robot.addMaterial(storeId);
		return storeMaterialInfo;
	}
	
	public StoreMaterialInfo getById(){
		return robot.getById();
	}
	
	public void updateMaterialInfo(String materialId){
		robot.updateMaterialInfo(materialId);
	}
	
	public void updateMaterialInventory(String materialId){
		robot.testUpdateMaterialInventory(materialId);
	}
	
	public MaterialRobot getRobot() {
		return robot;
	}

	public void setRobot(MaterialRobot robot) {
		this.robot = robot;
	}

	public long getId(){
		return this.robot.getId();
	}
	
}
