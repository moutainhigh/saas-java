package com.hq.chainMS.service.chainGoods.apiData;

public class GoodsUpdateStateForm {
	private String id;
	private int state;

	public static GoodsUpdateStateForm newInstance() {
		return new GoodsUpdateStateForm();
	}

	public static GoodsUpdateStateForm newInstance(String idP, int stateP) {
		GoodsUpdateStateForm data = newInstance();
		data.id = idP;
		data.state = stateP;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
