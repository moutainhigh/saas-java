package com.hq.storeMS.service.buserDevice.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class IotKeyValue {
	
	private String key;
	
	private String value;
	
	@JsonInclude(Include.NON_NULL) 
	private String level; //警报信息特有的level
	
	public static IotKeyValue getInstance(){
		return new IotKeyValue();
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	 
}
