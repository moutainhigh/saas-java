package com.hq.chainMS.service.chainUser.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

//连锁店、分店、用户的关联关系
@SynClass
public class ChainStoreUserRelative {
	private long chainId;
	// 连锁店对应的角色  ChainUserRoleEnum
	private int role;
	private Set<Long> storeIds = new HashSet<Long>();

	public static ChainStoreUserRelative newInstance() {
		ChainStoreUserRelative data = new ChainStoreUserRelative();
		return data;
	}
	
	public void removeStoreId(Long storeId) {
		storeIds.remove(storeId);
	}
	
	public void addStoreId(Long storeId) {
		storeIds.add(storeId);
	}
	
	public void addStoreIds(Set<Long> storeIdsP) {
		storeIds.addAll(storeIdsP);
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

}
