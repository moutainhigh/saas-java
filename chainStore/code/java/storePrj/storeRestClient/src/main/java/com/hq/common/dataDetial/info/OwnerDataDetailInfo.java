package com.hq.common.dataDetial.info;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OwnerDataDetailInfo {
	private String ownerId;
	//{域:{版本:{id:数据}}}  {DataVersionEnum:{version:{id:data}}}
	private Map<Integer, DataDetailVerItem> dataDetailVerItemMap = new ConcurrentHashMap<Integer, DataDetailVerItem>();
	
	public static OwnerDataDetailInfo newInstance(String ownerIdP){
		OwnerDataDetailInfo info = new OwnerDataDetailInfo();
		info.ownerId = ownerIdP;
		return info;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(DataVersionEnum dataVersionEnum, Long version, String id){
		DataDetailVerItem dataDetailVerItem = dataDetailVerItemMap.get(dataVersionEnum.ordinal());
		if(dataDetailVerItem == null) {
			return null;
		}
		DataItem dataItem = dataDetailVerItem.getDataItem(version);
		if(dataItem == null) {//域缓存不为空，但指定的版本信息为空，则判定为本地缓存的版本与服务器的版本不一致，应该清空本地缓存
			removeTableCache(dataVersionEnum);
			return null;
		}
		return (T)dataItem.getData(id);
	}
	
	public void putData(DataVersionEnum dataVersionEnum, Long version, String id, Object data){
		DataDetailVerItem dataDetailVerItem = dataDetailVerItemMap.get(dataVersionEnum.ordinal());
		if(dataDetailVerItem == null) {
			dataDetailVerItem = DataDetailVerItem.newInstance();
			dataDetailVerItemMap.put(dataVersionEnum.ordinal(), dataDetailVerItem);
		}
		dataDetailVerItem.putData(version, id, data);
	}
	
	//清空域的缓存信息
	public void removeTableCache(DataVersionEnum dataVersionEnum) {
		dataDetailVerItemMap.remove(dataVersionEnum.ordinal());
	}
	
	//清空所有缓存信息
	public void clear() {
		dataDetailVerItemMap.clear();
	}

	public String getOwnerId() {
		return ownerId;
	}	
}
