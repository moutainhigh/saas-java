package com.hq.orderMS.common.datasyn.info;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class DataSynVer {

	private DataSynType synType;
	private String id;
	private long ver;
	
	public static DataSynVer newInstance(DataSynType synTypeP,String idP,long verP){
		
		DataSynVer synVer = new DataSynVer();
		synVer.synType = synTypeP;
		synVer.id = idP;
		synVer.ver=verP;
		
		return synVer;
		
	}
	
	public DataSynType getSynType() {
		return synType;
	}
	public void setSynType(DataSynType synType) {
		this.synType = synType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getVer() {
		return ver;
	}
	public void setVer(long ver) {
		this.ver = ver;
	}
	
	
	
	
	
	
}
