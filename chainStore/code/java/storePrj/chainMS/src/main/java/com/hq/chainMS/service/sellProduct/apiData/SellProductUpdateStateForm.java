package com.hq.chainMS.service.sellProduct.apiData;

import com.hq.chainMS.service.sellProduct.data.SellProductTypeEnum;

public class SellProductUpdateStateForm {
	private SellAllotId sellAllotId;
	// 对应 SellProductStateEnum
	private int state;

	public static SellProductUpdateStateForm newInstance() {
		return new SellProductUpdateStateForm();
	}

	public static SellProductUpdateStateForm newInstance(SellAllotId sellAllotIdP, int stateP) {
		SellProductUpdateStateForm data = newInstance();
		data.sellAllotId = sellAllotIdP;
		data.state = stateP;
		return data;
	}

	public SellProductTypeEnum getSellProductTypeEnum() {
		return sellAllotId.getSellProductTypeEnum();
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
