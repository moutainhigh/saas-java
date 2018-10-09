package com.hq.storeMS.service.dynamic.data;

public class DynamicItem {
	// 产品类型 对应 SellItemTypeEnum
	private int sellItemType;
	// 项目、商品、套餐、次卡 ID
	private String id;
		
	public static DynamicItem newInstance() {
		DynamicItem data = new DynamicItem();
		return data;
	}

	public int getSellItemType() {
		return sellItemType;
	}

	public void setSellItemType(int sellItemType) {
		this.sellItemType = sellItemType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
