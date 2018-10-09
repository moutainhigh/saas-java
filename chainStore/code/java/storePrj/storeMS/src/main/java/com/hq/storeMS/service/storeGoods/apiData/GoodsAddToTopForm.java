package com.hq.storeMS.service.storeGoods.apiData;

public class GoodsAddToTopForm {
	private String goodsId;

	public static GoodsAddToTopForm newInstance() {
		return new GoodsAddToTopForm();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
}
