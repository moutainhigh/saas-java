package com.hq.chainClient.service.chainClerk.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainClient.service.chainClerk.data.adminRole.AdminRole;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "chainClerk")
public class ChainClerk {
	@Id
	private long id;
	private long chainId;

	private int roleIdIndex = 1;
	// 管理员角色权限定义
	private Map<Integer, AdminRole> roleMap = new HashMap<Integer, AdminRole>();

	private Map<Long, ClerkInfo> clerkInfoMap = new HashMap<Long, ClerkInfo>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static ChainClerk newInstance(){
		ChainClerk data = new ChainClerk();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public int getRoleIdIndex() {
		return roleIdIndex;
	}

	public void setRoleIdIndex(int roleIdIndex) {
		this.roleIdIndex = roleIdIndex;
	}

	public Map<Integer, AdminRole> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<Integer, AdminRole> roleMap) {
		this.roleMap = roleMap;
	}

	public Map<Long, ClerkInfo> getClerkInfoMap() {
		return clerkInfoMap;
	}

	public void setClerkInfoMap(Map<Long, ClerkInfo> clerkInfoMap) {
		this.clerkInfoMap = clerkInfoMap;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

}
