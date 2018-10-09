package com.hq.common.dataSyn.bs.owner;

import com.hq.common.dataSyn.info.DataSynVer;

public class OwnerDataSynItem {

	private DataSynVer synVer;
	
	private Object data;
	
	public static OwnerDataSynItem newInstance(DataSynVer synVerP, Object dataP){
		OwnerDataSynItem item = new OwnerDataSynItem();
		item.synVer = synVerP;
		item.data = dataP;
		return item;
	}
	
	public DataSynVer getSynVer(){
		return synVer;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(){
		return (T) data;
	}
	
}
