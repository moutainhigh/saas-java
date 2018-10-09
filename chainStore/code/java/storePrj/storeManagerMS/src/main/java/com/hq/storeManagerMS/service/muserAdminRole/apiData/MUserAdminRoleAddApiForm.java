package com.hq.storeManagerMS.service.muserAdminRole.apiData;


import java.util.HashSet;
import java.util.Set;

import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class MUserAdminRoleAddApiForm {
	private String name;
	private String descript;
	// MUserAdminPermEnum 权限集合
	private Set<Integer> permSet = new HashSet<Integer>();

	public static MUserAdminRoleAddApiForm newInstance(){
		return new MUserAdminRoleAddApiForm();
	}
	
	public MUserAdminRole toMUserAdminRole(){
		MUserAdminRole data = MUserAdminRole.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}
	
}
