package com.hq.chainClient.service.sellProduct.apiData;

import com.hq.chainClient.service.sellProduct.data.SellProductTypeEnum;

public class SellAllotId {
	// 产品类型 对应 SellProductTypeEnum
	private int sellProductType;
	// 产品ID
	private String id;

	public static SellAllotId newInstance() {
		return new SellAllotId();
	}
	
	public static SellAllotId newInstance(int sellProductTypeP, String idP) {
		SellAllotId data = new SellAllotId();
		data.id = idP;
		data.sellProductType = sellProductTypeP;
		return data;
	}
	
	public SellProductTypeEnum getSellProductTypeEnum() {
		return SellProductTypeEnum.valueOf(sellProductType);
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
}
