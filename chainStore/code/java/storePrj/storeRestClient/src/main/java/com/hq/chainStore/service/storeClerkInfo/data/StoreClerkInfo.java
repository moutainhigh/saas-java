package com.hq.chainStore.service.storeClerkInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.common.dataSyn.bs.IntfSynData;


@Table(name = "storeClerkInfo")
public class StoreClerkInfo implements IntfSynData{

	@Id	
	private String id;
	
	private long storeId;
	
	private int roleIdIndex = 1;
	//管理员角色权限定义
	private Map<Integer,StoreAdminRole> roleMap = new HashMap<Integer,StoreAdminRole>();
	
	private Map<Long,ApplyClerkInfo> applyClerkInfoMap = new HashMap<Long,ApplyClerkInfo>();
	
	private Map<Long,ClerkInfo> clerkInfoMap = new HashMap<Long,ClerkInfo>();
	
	//对应店铺工资月结天数
	private int monthPayDays;
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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
	
	public void incrVer() {
		this.ver = ver+1;
	}
	
	public Map<Long, ClerkInfo> getClerkInfoMap() {
		return clerkInfoMap;
	}

	public void setClerkInfoMap(Map<Long, ClerkInfo> clerkInfoMap) {
		this.clerkInfoMap = clerkInfoMap;
	}

	public int getRoleIdIndex() {
		return roleIdIndex;
	}

	public void setRoleIdIndex(int roleIdIndex) {
		this.roleIdIndex = roleIdIndex;
	}

	public Map<Integer, StoreAdminRole> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<Integer, StoreAdminRole> roleMap) {
		this.roleMap = roleMap;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public Map<Long, ApplyClerkInfo> getApplyClerkInfoMap() {
		return applyClerkInfoMap;
	}

	public void setApplyClerkInfoMap(Map<Long, ApplyClerkInfo> applyClerkInfoMap) {
		this.applyClerkInfoMap = applyClerkInfoMap;
	}

	public int getMonthPayDays() {
		return monthPayDays;
	}

	public void setMonthPayDays(int monthPayDays) {
		this.monthPayDays = monthPayDays;
	}
	
	
}
