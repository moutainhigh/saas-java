package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

public class TMaterialRecord {
	private float price;
	private int count;

	public TMaterialRecord(float price, int count) {
		super();
		this.price = price;
		this.count = count;
	}

	public static List<TMaterialRecord> buildTMaterialRecords() {
		List<TMaterialRecord> list = new ArrayList<TMaterialRecord>();
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		list.add(new TMaterialRecord(RandomUtils.nextFloat(20.0f, 200.0f),RandomUtils.nextInt(50, 200)));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
