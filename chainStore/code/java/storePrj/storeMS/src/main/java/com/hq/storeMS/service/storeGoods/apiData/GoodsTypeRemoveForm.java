package com.hq.storeMS.service.storeGoods.apiData;

public class GoodsTypeRemoveForm {

	private String goodsTypeId;

	public static GoodsTypeRemoveForm newInstance() {
		return new GoodsTypeRemoveForm();
	}

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
}
