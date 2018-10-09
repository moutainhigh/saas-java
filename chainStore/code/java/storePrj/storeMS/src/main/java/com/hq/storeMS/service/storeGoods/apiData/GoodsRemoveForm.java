package com.hq.storeMS.service.storeGoods.apiData;

public class GoodsRemoveForm {
	private String goodsId;

	public static GoodsRemoveForm newInstance() {
		return new GoodsRemoveForm();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
}
