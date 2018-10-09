package com.hq.chainMS.service.chainClerk.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ClerkInfo {
	private long userId;
	// 岗位信息
	private Set<Integer> roleSet = new HashSet<Integer>();
	// 实体状态
	private int entityState;
	// 名称
	private String name;
	// 手机号码
	private long phone;
	// 性别 对应 GenderEnum
	private int gender;
	private long createTime;

	public static ClerkInfo newInstance(long userIdP) {
		ClerkInfo info = new ClerkInfo();
		info.userId = userIdP;
		info.createTime = System.currentTimeMillis();
		return info;
	}

	public static ClerkInfo newInstance(long userIdP, String nameP, long phoneP) {
		ClerkInfo info = newInstance(userIdP);
		info.name = nameP;
		info.phone = phoneP;
		return info;
	}

	public void addRole(Integer roleId) {
		this.roleSet.add(roleId);
	}

	public void removeRole(Integer roleId) {
		this.roleSet.remove(roleId);
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
}
