package com.hq.chainStore.service.storeCardInfo.apiData;

import java.util.HashSet;
import java.util.Set;

/**
 * 批量修改会员卡状态
 */
public class BatchUpdMemberCardStateData {
	private Set<String> mbCardIdSet = new HashSet<String>();// 会员卡id
	private long storeId;
	private int state;

	public static BatchUpdMemberCardStateData newInstance() {
		return new BatchUpdMemberCardStateData();
	}

	public Set<String> getMbCardIdSet() {
		return mbCardIdSet;
	}

	public void setMbCardIdSet(Set<String> mbCardIdSet) {
		this.mbCardIdSet = mbCardIdSet;
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
