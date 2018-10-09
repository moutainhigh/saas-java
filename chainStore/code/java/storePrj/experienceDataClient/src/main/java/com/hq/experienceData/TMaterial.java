package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;


public class TMaterial {
	private String name;
	private float referencePrice;
	private int inventory;
	private int threshold;
	private String imgUrl;

	public static List<TMaterial> buildTMaterials() {
		List<TMaterial> list = new ArrayList<TMaterial>();
		String[] tmpNames = {"修复液一号","修复液二号","修复液五号","防晒霜","洗面奶","乳液","面膜","胶手套","注射用水","一次性注射器","小砂轮","医用胶布卷","医用棉球","手术丝线","圆角缝针","手术刀片"};
		for (String name : tmpNames) {
			TMaterial data = new TMaterial();
			data.setName(name);
			data.setReferencePrice(RandomUtils.nextFloat(50.0f, 500.0f));
			data.setInventory(RandomUtils.nextInt(20, 100));
			data.setThreshold(RandomUtils.nextInt(10, 20));
			data.setImgUrl("img/logo/store/zhimeitong-logo.png");
			list.add(data);
		}
		Collections.shuffle(list);
		return list.subList(0, DataConstants.OTHER_COUNT);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(float referencePrice) {
		this.referencePrice = referencePrice;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
