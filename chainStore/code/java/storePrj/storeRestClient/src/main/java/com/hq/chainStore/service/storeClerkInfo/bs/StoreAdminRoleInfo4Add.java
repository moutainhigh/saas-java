package com.hq.chainStore.service.storeClerkInfo.bs;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRoleState;

public class StoreAdminRoleInfo4Add {

	
	private long storeId;
	private String storeClerkInfoId;
	private int roleIdIndex;
	private String name;
	private Set<Integer> permSet = new HashSet<Integer>();
	private String descript;
	
	public static StoreAdminRoleInfo4Add newInstance(){
		return new StoreAdminRoleInfo4Add();
	}
	
	public StoreAdminRoleInfo4Add setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}
	public StoreAdminRoleInfo4Add setStoreClerkInfoId(String storeClerkInfoId) {
		this.storeClerkInfoId = storeClerkInfoId;
		return this;
	}
	public StoreAdminRoleInfo4Add setRoleIdIndex(int roleIdIndex) {
		this.roleIdIndex = roleIdIndex;
		return this;
	}
	public StoreAdminRoleInfo4Add setName(String name) {
		this.name = name;
		return this;
	}
	public StoreAdminRoleInfo4Add setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
		return this;
	}
	
	public StoreAdminRoleInfo4Add addPermSet(StoreAdminPermEnum permEnum) {
		this.permSet.add(permEnum.ordinal());
		return this;
	}
	
	public StoreAdminRoleInfo4Add setDescript(String descript) {
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

	public StoreAdminRole toRole(){
		StoreAdminRole role = StoreAdminRole.newInstance(this.getRoleIdIndex());
		long storeId = this.getStoreId();
		
		role.setStoreId(storeId);		
		role.setPermSet(this.getPermSet());
		role.setState(StoreAdminRoleState.Available.ordinal());
		role.setDescript(this.getDescript());
		role.setName(this.getName());
		return role;
	}
	
	
	
	
	
}
