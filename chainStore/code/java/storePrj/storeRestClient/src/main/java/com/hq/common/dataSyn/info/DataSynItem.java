package com.hq.common.dataSyn.info;

public class DataSynItem {

	private DataSynVer synVer;
	private String data;
	
	
	public static DataSynItem newInstance(DataSynVer oldSynVer,long newVerP, String dataP){
		
		DataSynType synType = oldSynVer.getSynTypeEnum();
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
