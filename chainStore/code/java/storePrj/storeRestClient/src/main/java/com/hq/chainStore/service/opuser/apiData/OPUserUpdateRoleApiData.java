package com.hq.chainStore.service.opuser.apiData;

import java.util.Set;

public class OPUserUpdateRoleApiData {
	
	private long opuserId;
	
	private Set<Integer> roleIdSet;
	
	public static OPUserUpdateRoleApiData newInstance(){
		return new OPUserUpdateRoleApiData();
	}
	
	public long getOpuserId() {
		return opuserId;
	}

	public void setOpuserId(long opuserId) {
		this.opuserId = opuserId;
	}

	public Set<Integer> getRoleIdSet() {
		return roleIdSet;
	}

	public void setRoleIdSet(Set<Integer> roleIdSet) {
		this.roleIdSet = roleIdSet;
	}

	
	
	
}
