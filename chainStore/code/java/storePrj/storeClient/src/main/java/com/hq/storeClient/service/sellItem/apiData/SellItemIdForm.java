package com.hq.storeClient.service.sellItem.apiData;

import com.hq.storeClient.service.sellItem.data.SellItemTypeEnum;

public class SellItemIdForm {
	// 产品类型 对应 SellItemTypeEnum
	private int sellItemType;
	// ID
	private String id;

	public static SellItemIdForm newInstance() {
		SellItemIdForm data = new SellItemIdForm();
		return data;
	}
	
	public static SellItemIdForm newInstance(int sellItemTypeP, String idP) {
		SellItemIdForm data = newInstance();
		data.sellItemType = sellItemTypeP;
		data.id = idP;
		return data;
	}
	
	public SellItemTypeEnum getSellItemTypeEnum() {
		return SellItemTypeEnum.valueOf(sellItemType);
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
