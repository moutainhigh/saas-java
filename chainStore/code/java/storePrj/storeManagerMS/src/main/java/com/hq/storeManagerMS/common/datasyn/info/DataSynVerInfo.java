package com.hq.storeManagerMS.common.datasyn.info;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;


@SynClass
public class DataSynVerInfo {
	
	private String ownerId;
	
	private List<DataSynVer> synVerList = new ArrayList<DataSynVer>();

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
