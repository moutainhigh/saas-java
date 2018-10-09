package com.hq.chainStore.service.chainPackageProject.data;

public class PackageItem {
	// 类型 PackageItemEnum
	private int itemType;
	// 项目/商品 简版信息ID 
	private String pgId;
	// 数量
	private int count;
	// 单次折后价
	private float discountPrice;

	public static PackageItem newInstance() {
		PackageItem data = new PackageItem();
		return data;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}
}
