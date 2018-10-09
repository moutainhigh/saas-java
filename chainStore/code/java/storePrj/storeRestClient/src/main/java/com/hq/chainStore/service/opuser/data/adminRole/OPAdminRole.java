package com.hq.chainStore.service.opuser.data.adminRole;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Table;


@Table(name = "op/opAdminRole")
public class OPAdminRole {

	private int id;
	
	private String name;
	
	private String descript;
	
	//role的状态，是否有效等
	private int state;
	
	
	private Set<Integer> permSet = new HashSet<Integer>();
	
	public static OPAdminRole newInstance(int idP){
		OPAdminRole role = new OPAdminRole();
		role.id = idP;
		role.state = OPAdminRoleState.Available.ordinal();
		return role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public OPAdminRoleState getStateEnum() {
		return OPAdminRoleState.valueOf(this.state);
	}
	
	public void setStateEnum(OPAdminRoleState state) {
		this.state = state.ordinal();
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}
	
	public OPAdminRole addPerm(OPAdminPermEnum permEnum){
		this.permSet.add(permEnum.ordinal());
		return this;
	}
	
	public Set<String> getPermSet4Shiro(){
		Set<String> permSetTmp = new HashSet<String>();
		for (int adminPermOrdinalTmp : permSet) {
			OPAdminPermEnum adminPermTmp = OPAdminPermEnum.valueOf(adminPermOrdinalTmp);
			permSetTmp.add(adminPermTmp.getPerm());
		}
		return permSetTmp;
	}

	public String getRole4Shiro(){
		return this.name;
	}
	
	
}
