package com.hq.storeManagerMS.service.muser.data;

import java.util.Set;

public interface MUserRO {
	public long getId();
	public String getName();
	public String getAccount();
	public String getPassword();
	public String getSalt();
	public long getCreatedTime();
	public long getLastUpdateTime();
	public long getVer();
	public int getStatus();
	public String getCredentialsSalt();
	public Set<Long> getMuserAdminRoleIds();
}
