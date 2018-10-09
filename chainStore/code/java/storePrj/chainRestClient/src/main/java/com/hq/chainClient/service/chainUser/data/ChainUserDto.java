package com.hq.chainClient.service.chainUser.data;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.service.chainClerk.data.adminRole.AdminRole;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ChainUserDto {
	private long id;
	// 名称
	private String name;
	// 手机号码区号
	private String areaCode;
	// 手机号码
	private long phone;
	// 头像
	private String headImg;
	// 性别 对应 GenderEnum
	private int gender;
	// 出生日期 格式 yyyyMMdd
	private String birthday;
	// 是否是跨店员工 CrossClerkEnum
	private int crossClerk;
	// 用户关联的连锁店id列表
	private Set<Long> storeIds = new HashSet<Long>();
	private Set<String> storeNames = new HashSet<String>();
	// 岗位信息
	private Set<AdminRole> adminRoles = new HashSet<AdminRole>();

	public static ChainUserDto newInstance() {
		ChainUserDto data = new ChainUserDto();
		return data;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Set<AdminRole> getAdminRoles() {
		return adminRoles;
	}

	public void setAdminRoles(Set<AdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public Set<String> getStoreNames() {
		return storeNames;
	}

	public void setStoreNames(Set<String> storeNames) {
		this.storeNames = storeNames;
	}

	public int getCrossClerk() {
		return crossClerk;
	}

	public void setCrossClerk(int crossClerk) {
		this.crossClerk = crossClerk;
	}

}
