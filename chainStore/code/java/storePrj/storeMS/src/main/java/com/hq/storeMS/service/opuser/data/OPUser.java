package com.hq.storeMS.service.opuser.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.common.EntityState;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;


@SynClass
@Table(name = "opuser")
public class OPUser {

	@Id	
	private long id;
	
	private String name;
	
	private long phone;
	
	private String password;
	
	private Set<Integer> opAdminRoleSet = new HashSet<Integer>();
	
	//给密码加点盐
	@SynIgnoreField
	private String salt;
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	private EntityState state = EntityState.Normal;
	
	public static OPUser newInstance(String name, String password){
		OPUser opuser = new OPUser();
		opuser.name = name;
		opuser.password = password;
		long curTime = System.currentTimeMillis();
		
		opuser.createdTime = curTime;
		opuser.lastUpdateTime = curTime;
		
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public String getCredentialsSalt() {
		return this.name+this.salt;
	}

	public Set<Integer> getOpAdminRoleSet() {
		return opAdminRoleSet;
	}

	public void setOpAdminRoleSet(Set<Integer> opAdminRoleSet) {
		this.opAdminRoleSet = opAdminRoleSet;
	}
	
	public OPUser addOpAdminRole(int roleId){
		this.opAdminRoleSet.add(roleId);
		return this;
	}
	
}
