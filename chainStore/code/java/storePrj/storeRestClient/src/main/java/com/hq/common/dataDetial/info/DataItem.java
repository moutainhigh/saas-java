package com.hq.common.dataDetial.info;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataItem {
	//{id:数据}  {id:data}
	private Map<String, Object> dataMap = new ConcurrentHashMap<String, Object>();
	
	public static DataItem newInstance(){
		return new DataItem();
	}

	public void putData(String key, Object value) {
		dataMap.put(key, value);
	}
	
	public Object getData(String key) {
		return dataMap.get(key);
	}
	
}
