package com.hq.storeManagerMS.service.muserAdminRole.apiData;


import java.util.HashSet;
import java.util.Set;

import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class MUserAdminRoleUpdApiForm {
	private long id;
	private String name;
	private String descript;
	// MUserAdminPermEnum 权限集合
	private Set<Integer> permSet = new HashSet<Integer>();

	public static MUserAdminRoleUpdApiForm newInstance(){
		return new MUserAdminRoleUpdApiForm();
	}
	
	public void updMUserAdminRole(MUserAdminRole data){
		FastBeanCopyer.getInstance().copy(this, data);
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
