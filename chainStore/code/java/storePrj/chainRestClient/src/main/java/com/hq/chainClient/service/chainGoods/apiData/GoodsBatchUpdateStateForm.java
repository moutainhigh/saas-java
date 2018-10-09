package com.hq.chainClient.service.chainGoods.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoodsBatchUpdateStateForm {
	private int state;
	// 商品id
	private Set<String> goodIds = new HashSet<String>();

	public static GoodsBatchUpdateStateForm newInstance() {
		return new GoodsBatchUpdateStateForm();
	}
	
	public List<GoodsUpdateStateForm> toGoodsUpdateStateFormList(){
		List<GoodsUpdateStateForm> list = new ArrayList<GoodsUpdateStateForm>();
		for (String id : goodIds) {
			list.add(GoodsUpdateStateForm.newInstance(id, state));
		}
		return list;
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
