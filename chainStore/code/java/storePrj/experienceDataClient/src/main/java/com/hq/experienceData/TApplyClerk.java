package com.hq.experienceData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

public class TApplyClerk {
	private long phone;
	private String imgPath;
	private String name;
	private int gender;
	private int age;
	private int isAdmin = 0;

	private Set<Integer> jobs = new HashSet<Integer>();
	
	public static List<TApplyClerk> buildTApplyClerkList(){
		List<TApplyClerk> list = new ArrayList<TApplyClerk>();
		String[] tmpNames = {"卜可可","圣笑槐","柴承望","犁君丽","淡格菲","剑梦竹","衷秀婉","舒俊力","贺雪卉","弘嘉福"};
		List<String> names = Arrays.asList(tmpNames);
		Collections.shuffle(names);
		
		DateFormat df = new SimpleDateFormat("ddHHmmss");//月日时分
		long mark = Long.valueOf(df.format(new Date()));
		int count = 1;
		for (String name : names) {
			TApplyClerk data = new TApplyClerk();
			data.setName(name);
			data.setGender(RandomUtils.nextInt(1, 2));
			data.setAge(RandomUtils.nextInt(20, 60));
			data.setImgPath("人物头像"+count+".jpg");
			data.setIsAdmin(1);
			data.setPhone(13500000000L + mark + count + RandomUtils.nextInt(10, 99)*100);
			list.add(data);
			count++;
		}
		Collections.shuffle(list);
		return list.subList(0, DataConstants.NUMBER_COUNT);
	}
	
	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set<Integer> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Integer> jobs) {
		this.jobs = jobs;
	}

}
