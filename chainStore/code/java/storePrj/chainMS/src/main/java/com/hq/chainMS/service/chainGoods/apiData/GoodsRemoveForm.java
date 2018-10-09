package com.hq.chainMS.service.chainGoods.apiData;

public class GoodsRemoveForm {
	private String id;

	public static GoodsRemoveForm newInstance() {
		return new GoodsRemoveForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
