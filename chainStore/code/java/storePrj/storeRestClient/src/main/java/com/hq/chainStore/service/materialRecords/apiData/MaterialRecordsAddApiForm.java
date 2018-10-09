package com.hq.chainStore.service.materialRecords.apiData;

import java.util.ArrayList;
import java.util.List;

public class MaterialRecordsAddApiForm {
	
	private List<MaterialRecordsAddApiData> materialRecordsAddList = new ArrayList<MaterialRecordsAddApiData>();
	
	public static MaterialRecordsAddApiForm newInstance(){
		return new MaterialRecordsAddApiForm();
	}

	public List<MaterialRecordsAddApiData> getMaterialRecordsAddList() {
		return materialRecordsAddList;
	}

	public void setMaterialRecordsAddList(List<MaterialRecordsAddApiData> materialRecordsAddList) {
		this.materialRecordsAddList = materialRecordsAddList;
	}
	
	public void addMaterialRecords(MaterialRecordsAddApiData materialRecordsAddApiData){
		this.materialRecordsAddList.add(materialRecordsAddApiData);
	}

}
