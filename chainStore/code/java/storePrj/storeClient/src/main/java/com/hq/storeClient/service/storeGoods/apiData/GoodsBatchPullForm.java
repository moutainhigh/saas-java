package com.hq.storeClient.service.storeGoods.apiData;

import java.util.ArrayList;
import java.util.List;

public class GoodsBatchPullForm {
	private List<GoodsPullForm> goodsPullForms = new ArrayList<GoodsPullForm>();

	public static GoodsBatchPullForm newInstance() {
		return new GoodsBatchPullForm();
	}

	public List<GoodsPullForm> getGoodsPullForms() {
		return goodsPullForms;
	}

	public void setGoodsPullForms(List<GoodsPullForm> goodsPullForms) {
		this.goodsPullForms = goodsPullForms;
	}
}
