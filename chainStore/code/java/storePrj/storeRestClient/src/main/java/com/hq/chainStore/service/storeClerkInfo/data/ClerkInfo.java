package com.hq.chainStore.service.storeClerkInfo.data;

import java.util.HashSet;
import java.util.Set;

public class ClerkInfo {
	
	private long buserId;

	private Set<Integer> roleSet = new HashSet<Integer>();
	
	private int entityState;//实体状态
	
	private long createTime;
	
	public static ClerkInfo newInstance(long buserIdP){
		ClerkInfo info = new ClerkInfo();
		info.buserId = buserIdP;
		info.createTime = System.currentTimeMillis();
		return info;
	}
	
	public void addRole(Integer roleId){
		this.roleSet.add(roleId);	
	}
	public void removeRole(Integer roleId){
		this.roleSet.remove(roleId);	
	}


	public Set<Integer> getRoleSet() {
		return roleSet;
	}



	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}



	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}
	
	
}
