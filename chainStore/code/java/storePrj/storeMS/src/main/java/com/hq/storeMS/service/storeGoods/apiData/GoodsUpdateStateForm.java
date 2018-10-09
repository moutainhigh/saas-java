package com.hq.storeMS.service.storeGoods.apiData;

public class GoodsUpdateStateForm {
	private String goodsId;
	private int state;

	public static GoodsUpdateStateForm newInstance() {
		return new GoodsUpdateStateForm();
	}
	
	public static GoodsUpdateStateForm newInstance(String goodsIdP, int stateP) {
		GoodsUpdateStateForm data = newInstance();
		data.goodsId = goodsIdP;
		data.state = stateP;
		return data;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
