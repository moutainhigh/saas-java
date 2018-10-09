package com.hq.chainClient.service.sellProduct.apiData;

public class SellProductUpdateStateForm {
	private SellAllotId sellAllotId;
	// 对应 SellProductStateEnum
	private int state;

	public static SellProductUpdateStateForm newInstance() {
		return new SellProductUpdateStateForm();
	}

	public SellAllotId getSellAllotId() {
		return sellAllotId;
	}

	public void setSellAllotId(SellAllotId sellAllotId) {
		this.sellAllotId = sellAllotId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
