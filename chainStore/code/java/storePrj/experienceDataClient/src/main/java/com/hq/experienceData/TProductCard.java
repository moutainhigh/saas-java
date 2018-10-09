package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

public class TProductCard {
	private String number;//编号
	private String name;
	private int type;//类型   productCardTypeEnum
	private float sellPrice;
	private int time;//次数
	private int validPeriod;//有效期 eg：365日/12月/1年	
	private int validPeriodUnit;//有效期时间的单位 对应ValidPeriodUnitEnum
	private int status;// 对应上下架状态
	private String imgPath;
	private String descript;//描述
	
	public static List<TProductCard> buildProductCardList() {
		List<TProductCard> list = new ArrayList<TProductCard>();
		String[] tmpNames = {"面部护理套餐","腿部护理套餐","面部美白套餐","无限制卡","至尊无敌卡","脱毛套餐","护发套餐","形体美容套餐","青春美白卡","减肥护肤卡"};
		for (String name : tmpNames) {
			TProductCard data = new TProductCard();
			data.setNumber(RandomStringUtils.random(10, DataConstants.RANDOM_STRING));
			data.setName(name);
			data.setType(RandomUtils.nextInt(0, 3));
			data.setSellPrice(RandomUtils.nextFloat(1000f, 5000f));
			data.setTime(RandomUtils.nextInt(1, 10));
			data.setValidPeriod(RandomUtils.nextInt(1, 100));
			data.setValidPeriodUnit(RandomUtils.nextInt(0, 5));
			data.setStatus(RandomUtils.nextInt(0, 3));
			data.setImgPath("");
			data.setDescript("套餐卡，按次数计算");
			list.add(data);
		}
		Collections.shuffle(list);
		return list.subList(0, DataConstants.NUMBER_COUNT);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}

	public int getValidPeriodUnit() {
		return validPeriodUnit;
	}

	public void setValidPeriodUnit(int validPeriodUnit) {
		this.validPeriodUnit = validPeriodUnit;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
