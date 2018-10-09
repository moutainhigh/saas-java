package com.hq.storeMS.service.buser.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeBUser")
public class StoreBUser {

	private long storeId;

	private Map<Long, SimpleBUser> buserInfoMap = new HashMap<Long, SimpleBUser>();

	public static StoreBUser newInstance(long storeId) {
		StoreBUser storeClerkInfo = new StoreBUser();
		storeClerkInfo.storeId = storeId;
		return storeClerkInfo;
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

	public void addAllBUsers(List<BUser> busers) {
		if (CollectionUtils.isEmpty(busers)) {
			return;
		}

		for (BUser bUser : busers) {
			this.buserInfoMap.put(bUser.getId(), SimpleBUser.newInstance(bUser));
		}
	}
}
