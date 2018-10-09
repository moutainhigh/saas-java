package com.hq.chainClient.service.chainGoods.apiData;

public class GoodsTypeRemoveForm {
	private String id;

	public static GoodsTypeRemoveForm newInstance() {
		return new GoodsTypeRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
