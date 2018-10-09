package com.hq.chainStore.service.opuser.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;


@Table(name = "op/opuser")
public class OPUser implements IntfSynData{

	@Id	
	private long id;
	
	private String name;
	
	private long phone;
	
	private String password;
	
	private Set<Integer> opAdminRoleSet = new HashSet<Integer>();
	
	private long createdTime;
	
	private long ver;

	
	public static OPUser newInstance(String name, long phone){
		OPUser opuser = new OPUser();
		opuser.name = name;
		opuser.phone = phone;
		long curTime = System.currentTimeMillis();
		
		opuser.createdTime = curTime;		
		return opuser;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}


	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}
	
	public void incrVer() {
		this.ver = ver+1;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}


	public Set<Integer> getOpAdminRoleSet() {
		return opAdminRoleSet;
	}


	public void setOpAdminRoleSet(Set<Integer> opAdminRoleSet) {
		this.opAdminRoleSet = opAdminRoleSet;
	}

	
}
