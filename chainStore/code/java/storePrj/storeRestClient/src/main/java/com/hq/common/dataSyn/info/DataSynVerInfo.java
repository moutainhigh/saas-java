package com.hq.common.dataSyn.info;

import java.util.ArrayList;
import java.util.List;


public class DataSynVerInfo {
	
	private String ownerId;
	
	private List<DataSynVer> synVerList = new ArrayList<DataSynVer>();


	public static DataSynVerInfo newInstance(String ownerIdP, List<DataSynVer> synVerListP) {
		DataSynVerInfo target = new DataSynVerInfo();
		target.ownerId = ownerIdP;
		target.synVerList = synVerListP;
		return target;
	}
	
	public List<DataSynVer> getSynVerList() {
		return synVerList;
	}

	public void setSynVerList(List<DataSynVer> synVerList) {
		this.synVerList = synVerList;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	
	
	

}
