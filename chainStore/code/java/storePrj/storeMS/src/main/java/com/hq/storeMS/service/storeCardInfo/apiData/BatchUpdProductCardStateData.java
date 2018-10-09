package com.hq.storeMS.service.storeCardInfo.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 批量修改次卡状态
 */
public class BatchUpdProductCardStateData {

	private Set<String> prdCardIdSet = new HashSet<String>();// 次卡id

	private long storeId;

	private int state;

	public static BatchUpdProductCardStateData newInstance() {
		return new BatchUpdProductCardStateData();
	}
	
	public List<UpdProductCardStateData> toUpdProductCardStateDataList(){
		List<UpdProductCardStateData> list = new ArrayList<UpdProductCardStateData>();
		for (String id : this.prdCardIdSet) {
			list.add(UpdProductCardStateData.newInstance(id, this.storeId, this.state));
		}
		return list;
	}

	public Set<String> getPrdCardIdSet() {
		return prdCardIdSet;
	}

	public void setPrdCardIdSet(Set<String> prdCardIdSet) {
		this.prdCardIdSet = prdCardIdSet;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
