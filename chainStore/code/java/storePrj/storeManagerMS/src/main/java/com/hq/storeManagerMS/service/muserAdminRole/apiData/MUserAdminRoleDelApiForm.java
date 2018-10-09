package com.hq.storeManagerMS.service.muserAdminRole.apiData;

public class MUserAdminRoleDelApiForm {
	private long id;

	public static MUserAdminRoleDelApiForm newInstance() {
		return new MUserAdminRoleDelApiForm();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
