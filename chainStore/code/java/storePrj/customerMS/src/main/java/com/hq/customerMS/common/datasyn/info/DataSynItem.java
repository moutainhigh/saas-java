package com.hq.customerMS.common.datasyn.info;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class DataSynItem {

	private DataSynVer synVer;
	private String data;
	
	
	public static DataSynItem newInstance(DataSynVer oldSynVer,long newVerP, String dataP){
		
		DataSynType synType = oldSynVer.getSynType();
		String id = oldSynVer.getId();
		
		DataSynItem synItem = new DataSynItem();
		synItem.synVer = DataSynVer.newInstance(synType, id, newVerP);
		synItem.data = dataP;
		return synItem;
	}
	
	public DataSynVer getSynVer() {
		return synVer;
	}
	public void setSynVer(DataSynVer synVer) {
		this.synVer = synVer;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
