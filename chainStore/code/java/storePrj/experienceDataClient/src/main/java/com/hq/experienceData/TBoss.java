package com.hq.experienceData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hq.experienceData.service.RandomUtils;

public class TBoss {
	private String name;
	private int gender;
	private int age;
	private String imgPath;
	private long phone;
	private Set<Integer> roleSet = new HashSet<Integer>();
	
	public static TBoss buildTBoss(){
		TBoss tBoss = new TBoss();
		DateFormat df = new SimpleDateFormat("MMdd0000");//月日时分
		long mark = Long.valueOf(df.format(new Date()));
		Set<Integer> roles = new HashSet<Integer>();
		roles.add(0);
		tBoss.setName("张三");
		tBoss.setGender(1);
		tBoss.setAge(30);
		tBoss.setRoleSet(roles);
//		tBoss.setPhone(12100000000L);
		tBoss.setPhone(13500000000L + mark + RandomUtils.nextLong(1000L, 9999L));
		tBoss.setImgPath("老板头像.jpg");
		return tBoss;
	}
	
	public static TBoss newInstance(long random){
		TBoss tBoss = new TBoss();
		DateFormat df = new SimpleDateFormat("MMdd0000");//月日时分
		long mark = Long.valueOf(df.format(new Date()));
		Set<Integer> roles = new HashSet<Integer>();
		roles.add(0);
		tBoss.setName("张三"+random);
		tBoss.setGender(RandomUtils.nextInt(1, 3));
		tBoss.setAge(RandomUtils.nextInt(18, 58));
		tBoss.setRoleSet(roles);
		tBoss.setPhone(13500000000L + mark + RandomUtils.nextLong(1000L, 9999L));
		tBoss.setImgPath("老板头像.jpg");
		return tBoss;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}
}
