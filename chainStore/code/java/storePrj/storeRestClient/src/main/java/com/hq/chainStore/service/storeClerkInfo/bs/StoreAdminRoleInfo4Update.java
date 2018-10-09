package com.hq.chainStore.service.storeClerkInfo.bs;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRoleState;

public class StoreAdminRoleInfo4Update {

	
	private long storeId;
	private String storeClerkInfoId;
	private int roleIdIndex;
	private String name;
	private Set<Integer> permSet = new HashSet<Integer>();
	private String descript;
	private StoreAdminRoleState state;
	
	public static StoreAdminRoleInfo4Update newInstance(){
		return new StoreAdminRoleInfo4Update();
	}
	
	public StoreAdminRoleInfo4Update setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}
	public StoreAdminRoleInfo4Update setStoreClerkInfoId(String storeClerkInfoId) {
		this.storeClerkInfoId = storeClerkInfoId;
		return this;
	}
	public StoreAdminRoleInfo4Update setRoleIdIndex(int roleIdIndex) {
		this.roleIdIndex = roleIdIndex;
		return this;
	}
	public StoreAdminRoleInfo4Update setName(String name) {
		this.name = name;
		return this;
	}
	public StoreAdminRoleInfo4Update setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
		return this;
	}
	
	public StoreAdminRoleInfo4Update addPermSet(StoreAdminPermEnum permEnum) {
		this.permSet.add(permEnum.ordinal());
		return this;
	}
	public StoreAdminRoleInfo4Update setState(StoreAdminRoleState state) {
		this.state = state;
		return this;
	}
	
	public StoreAdminRoleInfo4Update setDescript(String descript) {
		this.descript = descript;
		return this;
	}
	public long getStoreId() {
		return storeId;
	}
	public String getStoreClerkInfoId() {
		return storeClerkInfoId;
	}
	public int getRoleIdIndex() {
		return roleIdIndex;
	}
	public String getName() {
		return name;
	}
	public Set<Integer> getPermSet() {
		return permSet;
	}
	public String getDescript() {
		return descript;
	}
	
	public StoreAdminRoleState getState() {
		return state;
	}



	public StoreAdminRole toRole(){
		StoreAdminRole role = StoreAdminRole.newInstance(this.getRoleIdIndex());
		long storeId = this.getStoreId();
		
		role.setStoreId(storeId);		
		role.setPermSet(this.getPermSet());
		role.setDescript(this.getDescript());
		role.setName(this.getName());
		role.setState(this.state.ordinal());
		return role;
	}
	
	
	
	
	
	
	
}
