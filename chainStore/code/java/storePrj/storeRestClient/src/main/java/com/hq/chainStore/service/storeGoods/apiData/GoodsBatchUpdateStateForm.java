package com.hq.chainStore.service.storeGoods.apiData;

import java.util.HashSet;
import java.util.Set;

public class GoodsBatchUpdateStateForm {
	private int state;
	// 商品id
	private Set<String> goodIds = new HashSet<String>();

	public static GoodsBatchUpdateStateForm newInstance() {
		return new GoodsBatchUpdateStateForm();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Set<String> getGoodIds() {
		return goodIds;
	}

	public void setGoodIds(Set<String> goodIds) {
		this.goodIds = goodIds;
	}
}
