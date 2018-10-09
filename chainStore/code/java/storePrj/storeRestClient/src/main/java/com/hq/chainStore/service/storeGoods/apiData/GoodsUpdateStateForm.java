package com.hq.chainStore.service.storeGoods.apiData;

public class GoodsUpdateStateForm {
	private String goodsId;
	private int state;

	public static GoodsUpdateStateForm newInstance() {
		return new GoodsUpdateStateForm();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsId() {
		return goodsId;
	}
}
