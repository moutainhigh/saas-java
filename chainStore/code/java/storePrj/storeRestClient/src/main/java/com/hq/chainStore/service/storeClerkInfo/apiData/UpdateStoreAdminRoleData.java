package com.hq.chainStore.service.storeClerkInfo.apiData;

import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;

public class UpdateStoreAdminRoleData {

	private long storeId;
	
	private StoreAdminRole role;
	
	public static UpdateStoreAdminRoleData newInstance(){
		return new UpdateStoreAdminRoleData();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public StoreAdminRole getRole() {
		return role;
	}

	public void setRole(StoreAdminRole role) {
		this.role = role;
	}
	
}
