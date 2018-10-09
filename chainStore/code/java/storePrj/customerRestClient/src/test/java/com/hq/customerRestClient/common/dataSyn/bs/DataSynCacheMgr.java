package com.hq.customerRestClient.common.dataSyn.bs;

import com.hq.customerRestClient.common.cache.RestCacheMgr;
import com.hq.customerRestClient.common.dataSyn.bs.owner.OwnerDataSynInfo;
import com.hq.customerRestClient.common.dataSyn.bs.owner.OwnerDataSynItem;
import com.hq.customerRestClient.common.dataSyn.info.DataSynItem;
import com.hq.customerRestClient.common.dataSyn.info.DataSynType;
import com.hq.customerRestClient.common.dataSyn.info.DataSynVer;
import com.hq.customerRestClient.common.dataSyn.info.DataSynVerInfo;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class DataSynCacheMgr {
	
	public static DataSynCacheMgr getInstance(){
		return HotSwap.getInstance().getSingleton(DataSynCacheMgr.class);
	}
	
	private final String cacheName = "dataSynCache";

	public void synData(String ownerId, DataSynItem dataSynItem, Class<?> clazz) {
		
		String data = dataSynItem.getData();
		Object dataObj = JsonUtil.getInstance().fromJson(data, clazz);
		DataSynVer synVer = dataSynItem.getSynVer();
		putData(ownerId, dataObj, synVer);
	}

	public void putData(String ownerId, Object dataObj, DataSynVer synVer) {
		OwnerDataSynItem ownerSynItem = OwnerDataSynItem.newInstance(synVer, dataObj);
		
		OwnerDataSynInfo synInfo = getOwnerSynInfo(ownerId);
		String key = getKey(synVer.getSynTypeEnum(),synVer.getId());
		synInfo.put(key, ownerSynItem);
	}
	private String getKey(DataSynType synType,String id){
		final String format = "{}_{}";
		String key = StringFormatUtil.format(format, synType.name(),id);
		return key;
	}

	private OwnerDataSynInfo getOwnerSynInfo(String ownerId) {
		OwnerDataSynInfo synInfo = RestCacheMgr.getInstance().get(cacheName, ownerId);
		if(synInfo == null){
			synInfo = OwnerDataSynInfo.newInstance(ownerId);
			RestCacheMgr.getInstance().put(cacheName, ownerId, synInfo);
		}
		return synInfo;
	}
	
	public <T> T getData(String ownerId, DataSynType synType,String id, Class<T> clazz){
		String key = getKey(synType,id);
		OwnerDataSynInfo synInfo = getOwnerSynInfo(ownerId);
		return synInfo.getData(key);
	}
	
	public DataSynVerInfo getSynVerInfo(String ownerId){
		OwnerDataSynInfo synInfo = getOwnerSynInfo(ownerId);
		return synInfo.getSynVerInfo();
	}
	
	
}
