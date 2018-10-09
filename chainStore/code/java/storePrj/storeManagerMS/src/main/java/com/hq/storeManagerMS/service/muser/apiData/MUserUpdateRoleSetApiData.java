package com.hq.storeManagerMS.service.muser.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeManagerMS.service.muser.data.MUser;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class MUserUpdateRoleSetApiData {
	private long muserId;
	// 角色 MUserAdminRoleIds
	private Set<Long> muserAdminRoleIds = new HashSet<Long>();

	public static MUserUpdateRoleSetApiData newInstance() {
		return new MUserUpdateRoleSetApiData();
	}

	public void updateMUser(MUser data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public long getMuserId() {
		return muserId;
	}

	public void setMuserId(long muserId) {
		this.muserId = muserId;
	}

	public Set<Long> getMuserAdminRoleIds() {
		return muserAdminRoleIds;
	}

	public void setMuserAdminRoleIds(Set<Long> muserAdminRoleIds) {
		this.muserAdminRoleIds = muserAdminRoleIds;
	}

}
