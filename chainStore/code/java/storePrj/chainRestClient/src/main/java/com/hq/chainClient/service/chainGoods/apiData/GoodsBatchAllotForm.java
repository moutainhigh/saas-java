package com.hq.chainClient.service.chainGoods.apiData;

import java.util.ArrayList;
import java.util.List;

public class GoodsBatchAllotForm {
	private List<GoodsAllotForm> goodsAllotForms = new ArrayList<GoodsAllotForm>();

	public static GoodsBatchAllotForm newInstance() {
		return new GoodsBatchAllotForm();
	}

	public List<GoodsAllotForm> getGoodsAllotForms() {
		return goodsAllotForms;
	}

	public void setGoodsAllotForms(List<GoodsAllotForm> goodsAllotForms) {
		this.goodsAllotForms = goodsAllotForms;
	}
}
