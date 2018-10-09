package com.hq.storeMS.service.buser.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SimpleBUser {
	private long id;
	private String name;
	private long phone;
	private String headImg;
	private int gender;
	private int age;
	private Set<Long> storeIdSet = new HashSet<Long>();
	private Set<Integer> roleSet = new HashSet<Integer>();
	
	public static SimpleBUser newInstance(BUser bUser) {
		SimpleBUser data = new SimpleBUser();
		FastBeanCopyer.getInstance().copy(bUser, data);
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Long> getStoreIdSet() {
		return storeIdSet;
	}

	public void setStoreIdSet(Set<Long> storeIdSet) {
		this.storeIdSet = storeIdSet;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}
	
	

}
