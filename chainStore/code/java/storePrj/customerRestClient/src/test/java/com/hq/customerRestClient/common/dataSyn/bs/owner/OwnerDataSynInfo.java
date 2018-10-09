package com.hq.customerRestClient.common.dataSyn.bs.owner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hq.customerRestClient.common.dataSyn.info.DataSynVer;
import com.hq.customerRestClient.common.dataSyn.info.DataSynVerInfo;

public class OwnerDataSynInfo {
	
	private String ownerId;

	private Map<String,OwnerDataSynItem> itemMap = new ConcurrentHashMap<String,OwnerDataSynItem>();
	
	private DataSynVerInfo synVerInfo;
	
	public static OwnerDataSynInfo newInstance(String ownerIdP){
		OwnerDataSynInfo info = new OwnerDataSynInfo();
		info.ownerId = ownerIdP;
		return info;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(String key){
		OwnerDataSynItem item = getItem(key);
		return item==null?null:(T)item.getData();
	}
	
	public OwnerDataSynItem getItem(String key){
		return itemMap.get(key);
	}
	
	public void put(String key, OwnerDataSynItem item){
		itemMap.put(key, item);
		//有更新就清空列表;
		synVerInfo = null;
	}

	public String getOwnerId() {
		return ownerId;
	}
	
	
	public DataSynVerInfo getSynVerInfo(){
		if(synVerInfo == null){
			List<DataSynVer> synVerListTmp = new ArrayList<DataSynVer>();
			Collection<OwnerDataSynItem> values = itemMap.values();
			for (OwnerDataSynItem ownerDataSynItem : values) {
				synVerListTmp.add(ownerDataSynItem.getSynVer());
			}
			
			DataSynVerInfo synVerInfoTmp = DataSynVerInfo.newInstance(ownerId, synVerListTmp);
			synVerInfo = synVerInfoTmp;
		}
		return synVerInfo;
		
	}
	
	
	
}
