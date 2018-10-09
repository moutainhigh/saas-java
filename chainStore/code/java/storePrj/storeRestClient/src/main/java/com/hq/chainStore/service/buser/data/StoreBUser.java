package com.hq.chainStore.service.buser.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import com.hq.common.dataDetial.bs.IntfDetailData;

@Table(name = "storeBUser")
public class StoreBUser  implements IntfDetailData{

	private long storeId;

	private Map<Long, SimpleBUser> buserInfoMap = new HashMap<Long, SimpleBUser>();

	public static StoreBUser newInstance(long storeId) {
		StoreBUser storeClerkInfo = new StoreBUser();
		storeClerkInfo.storeId = storeId;
		return storeClerkInfo;
	}
	
	@Override
	public Object targetId() {
		return this.storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Map<Long, SimpleBUser> getBuserInfoMap() {
		return buserInfoMap;
	}

	public void setBuserInfoMap(Map<Long, SimpleBUser> buserInfoMap) {
		this.buserInfoMap = buserInfoMap;
	}
}
