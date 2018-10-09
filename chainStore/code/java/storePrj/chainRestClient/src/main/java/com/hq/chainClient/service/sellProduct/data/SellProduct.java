package com.hq.chainClient.service.sellProduct.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "sellProduct")
public class SellProduct {
	// 产品类型 对应 SellProductTypeEnum
	private int sellProductType;
	// ID
	private String id;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态
	private int state;
	// 售价
	private float sellPrice;
	// 默认图片
	private String defaultImg;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static SellProduct newInstance() {
		SellProduct data = new SellProduct();
		return data;
	}

	public int getSellProductType() {
		return sellProductType;
	}

	public void setSellProductType(int sellProductType) {
		this.sellProductType = sellProductType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public Set<Long> getApplyStoreIds() {
		return applyStoreIds;
	}

	public void setApplyStoreIds(Set<Long> applyStoreIds) {
		this.applyStoreIds = applyStoreIds;
	}

}
