package com.hq.storeMS.service.storeGoods.apiData;

public class GoodsCancelTopForm {
	private String goodsId;

	public static GoodsCancelTopForm newInstance() {
		return new GoodsCancelTopForm();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
}
