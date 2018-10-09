package com.hq.common.dataSyn.info;

import com.alibaba.fastjson.annotation.JSONField;

public class DataSynVer {

	@JSONField(serialize=false) 
	private DataSynType synTypeEnum;
	private String id;
	private long ver;
	
	public static DataSynVer newInstance(DataSynType synTypeP,String idP,long verP){
		
		DataSynVer synVer = new DataSynVer();
		synVer.synTypeEnum = synTypeP;
		synVer.id = idP;
		synVer.ver=verP;
		
		return synVer;
		
	}
	
	@JSONField(name = "synType")
	public int getSynType() {
		return synTypeEnum.ordinal();
	}
	@JSONField(name = "synType")
	public void setSynType(int ordinal) {
		this.synTypeEnum = DataSynType.valueOf(ordinal);
	}
	
	
	public DataSynType getSynTypeEnum() {
		return synTypeEnum;
	}
	
	public void setSynTypeEnum(DataSynType synType) {
		this.synTypeEnum = synType;
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
