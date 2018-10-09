package com.hq.chainClient.service.chainClerk.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ClerkInfo {
	private long userId;
	private Set<Integer> roleSet = new HashSet<Integer>();
	// 实体状态
	private int entityState;
	// 名称
	private String name;
	// /手机号码
	private long phone;
	// 性别 对应 GenderEnum
	private int gender;
	private long createTime;

	public static ClerkInfo newInstance() {
		ClerkInfo info = new ClerkInfo();
		return info;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

}
