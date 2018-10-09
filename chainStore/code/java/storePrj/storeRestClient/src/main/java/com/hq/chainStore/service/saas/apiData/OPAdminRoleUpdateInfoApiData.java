package com.hq.chainStore.service.saas.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainStore.service.opuser.data.OPUser;
import com.hq.chainStore.service.opuser.data.adminRole.OPAdminRole;

public class OPAdminRoleUpdateInfoApiData {
	
	private int id;
	
	private String name;
	
	private String descript;
	
	private Set<Integer> permSet = new HashSet<Integer>();
	
	public static OPAdminRoleUpdateInfoApiData newInstance(){
		return new OPAdminRoleUpdateInfoApiData();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void update(OPUser opuser){
		opuser.setName(this.name);
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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

	public OPAdminRole toRole(){
		OPAdminRole role = OPAdminRole.newInstance(this.id);
		role.setName(this.name);
		role.setDescript(this.descript);
		role.setPermSet(this.permSet);
		return role;
	}

	
	
}
