package com.hq.storeClient.service.buserDevice.data.vo;

import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name="buserDevice")
public class IotKeyValue {
	private String key;
	private String value;
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
