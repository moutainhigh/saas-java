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

public class TShopKeeper {
	private long phone;
	private String imgPath;
	private String name;
	private int gender;
	private int age;
	private int isAdmin = 1;

	private Set<Integer> jobs = new HashSet<Integer>();
	
	public static List<TShopKeeper> buildTShopKeeperList() {
		List<TShopKeeper> list = new ArrayList<TShopKeeper>();
		
		String[] tmpNames = {"佘清霁","侯茹薇","善桐华","荆德宇","辜春冬","咸沛萍","隽雅媚","俎夜柳","拜智志","缪海白"};
		List<String> names = Arrays.asList(tmpNames);
		Collections.shuffle(names);
		
		DateFormat df = new SimpleDateFormat("ddHHmmss");//月日时分
		long mark = Long.valueOf(df.format(new Date()));
		int count = 1;
		for (String name : names) {
			TShopKeeper data = new TShopKeeper();
			data.setName(name);
			data.setGender(RandomUtils.nextInt(1, 3));
			data.setAge(RandomUtils.nextInt(20, 60));
			data.setImgPath("人物头像"+count+".jpg");
			data.setIsAdmin(0);
			data.setPhone(13200000000L + mark + count + RandomUtils.nextInt(10, 99)*100);
			Set<Integer> roleSet = new HashSet<Integer>();
			roleSet.add(3);
			data.setJobs(roleSet);
			list.add(data);
			count++;
		}
		
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
