package com.hq.chainClient.service.chainProduct.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddProductInfoData {
	private long index;
	private String number;// 编号
	private String name;
	private String typeId;
	private float sellPrice;
	private int state;
	private String defaultImg;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();
	
	private float cost;// 成本
	private String descript;
	private List<String> imgPathList = new ArrayList<String>();

	public static AddProductInfoData newInstance() {
		return new AddProductInfoData();
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

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeId() {
		return typeId;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Set<Long> getApplyStoreIds() {
		return applyStoreIds;
	}

	public void setApplyStoreIds(Set<Long> applyStoreIds) {
		this.applyStoreIds = applyStoreIds;
	}
}
