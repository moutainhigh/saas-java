package com.hq.storeManagerRestClient.service.muser.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Table;

@Table(name = "muser")
public class MUser {
	private long id;
	// 账号
	private String account;
	// 名称
	private String name;
	// 密码
	private String password;
	//头像
	private String headImg;
	//性别
	private int gender;
	// 角色 MUserAdminRoleIds
	private Set<Long> muserAdminRoleIds = new HashSet<Long>();
	// 给密码加点盐
	private String salt;
	// 状态 MUserStatusEnum
	private int status;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static MUser newInstance() {
		return new MUser();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Set<Long> getMuserAdminRoleIds() {
		return muserAdminRoleIds;
	}

	public void setMuserAdminRoleIds(Set<Long> muserAdminRoleIds) {
		this.muserAdminRoleIds = muserAdminRoleIds;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
