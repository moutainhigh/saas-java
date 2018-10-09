package com.hq.common.dataDetial.info;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataDetailVerItem {
	//{版本:{id:数据}}  {version:{id:data}}
	private Map<Long, DataItem> dataDetailVerMap = new ConcurrentHashMap<Long, DataItem>();
	
	public static DataDetailVerItem newInstance(){
		return new DataDetailVerItem();
	}
	
	public void putData(Long version, String id, Object data) {
		DataItem dataItem = dataDetailVerMap.get(version);
		if(dataItem == null) {
			dataItem = DataItem.newInstance();
			dataDetailVerMap.put(version, dataItem);
		}
		dataItem.putData(id, data);
	}
	
	public Object getData(Long version, String id) {
		DataItem dataItem = dataDetailVerMap.get(version);
		if(dataItem == null) {
			return null;
		}
		return dataItem.getData(id);
	}
	
	public DataItem getDataItem(Long version) {
		return dataDetailVerMap.get(version);
	}
	
}
