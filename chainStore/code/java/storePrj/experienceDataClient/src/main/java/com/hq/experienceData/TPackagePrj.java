package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hq.experienceData.service.RandomUtils;


public class TPackagePrj {
	// 名称
	private String name;
	// 分类名称
	private String typeName;
	// 状态 对应 PackageStateEnum
	private int state;
	// 售价
	private float sellPrice;
	// 成本
	private float cost;
	// 默认图片
	private String defaultImg;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static List<TPackagePrj> buildTPackagePrjs() {
		List<TPackagePrj> list = new ArrayList<TPackagePrj>();
		String[] names = {"减肥套餐","美白套餐","护肤套餐","养生套餐","脱毛套餐"};
		for (String name : names) {
			TPackagePrj data = new TPackagePrj();
			data.setName(name);
			data.setTypeName(name.replace("套餐", ""));
			data.setState(0);
			data.setSellPrice(RandomUtils.nextFloat(100f, 999f));
			data.setCost(RandomUtils.nextFloat(100f, data.getSellPrice()));
			data.setDescript(name+"描述");
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
