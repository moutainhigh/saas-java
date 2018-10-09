package com.hq.experienceData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

public class TCustomer {
	private String name;
	private int gender;
	private long phone;

	public static void main(String[] args) {
		List<TCustomer> list = TCustomer.buildTCustomerList();
		for (TCustomer tCustomer : list) {
			System.out.println(tCustomer.getPhone());
		}
	}
	
	public static List<TCustomer> buildTCustomerList() {
		List<TCustomer> list = new ArrayList<TCustomer>();
		
		String[] tmpNames = { "Angelababy", "唐艺昕", "杨幂", "杨洋", "胡歌", "舒畅", "范冰冰", "赵丽颖", "迪丽热巴", "靳东" };
//		String[] tmpNames = { "陈佩雯", "董力", "马梓铭", "滕雨佳", "云蕾", "卫念恩", "夏诗洁", "张思敏", "杨馥宇", "王仁君" };
		List<String> names = Arrays.asList(tmpNames);
		Collections.shuffle(names);

		DateFormat df = new SimpleDateFormat("ddHHmmss");// 月日时分
		long mark = Long.valueOf(df.format(new Date()));
		int count = 1;
		for (String name : names) {
			TCustomer data = new TCustomer();
			data.setName(name);
			data.setGender(RandomUtils.nextInt(1, 3));
			data.setPhone(13300000000L + mark + count + RandomUtils.nextInt(10, 99)*100);
			list.add(data);
			count++;
		}
		Collections.shuffle(list);
		return list.subList(0, DataConstants.NUMBER_COUNT);
	}
	
	public static List<TCustomer> buildRandomTCustomerList(int count) {
		List<TCustomer> list = new ArrayList<TCustomer>();
		
		String[] tmpNames = { "守天路", "台柔怀", "考芳馨", "东郭春翠", "森韶华", "甄今歌", "镇南蕾", "福振博", "尤俊健", "乜觅柔" };
		List<String> names = Arrays.asList(tmpNames);
		Collections.shuffle(names);
		
		DateFormat df = new SimpleDateFormat("ddHHmmss");// 月日时分
		long mark = Long.valueOf(df.format(new Date()));
		for (int i = 0; i < count; i++) {
			TCustomer data = new TCustomer();
			data.setName(tmpNames[RandomUtils.nextInt(0, tmpNames.length)]+"-"+RandomUtils.nextLong(1000L, 9999L));
			data.setGender(RandomUtils.nextInt(1, 3));
			data.setPhone(13300000000L + mark + i);
			list.add(data);
		}
		Collections.shuffle(list);
		return list;
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

}
