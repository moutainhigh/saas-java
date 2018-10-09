package com.hq.storeMS.service.storeClerkInfo.apiData;

public class RemoveStoreAdminRoleData {

	private long storeId;

	private int roleId;

	public static RemoveStoreAdminRoleData newInstance() {
		return new RemoveStoreAdminRoleData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
