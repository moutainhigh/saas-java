package com.hq.storeMS.service.footprint.apiData;

import com.hq.storeMS.service.footprint.data.Footprint;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class FootprintAddForm {
	// C端用户ID
	private long cuserId;
	// 动态ID
	private long dynamicId;

	public static FootprintAddForm newInstance() {
		FootprintAddForm data = new FootprintAddForm();
		return data;
	}

	public Footprint toFootprint() {
		Footprint data = Footprint.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(long dynamicId) {
		this.dynamicId = dynamicId;
	}
}
