package com.hq.storeManagerMS.service.vipLevelType.apiData;

import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class UpdateVipLevelTypeForm {

	private long id;

	private String name;

	public static UpdateVipLevelTypeForm newInstance() {
		UpdateVipLevelTypeForm data = new UpdateVipLevelTypeForm();
		return data;
	}

	public void updateVipLevelType(VipLevelType vipLevelType) {
		FastBeanCopyer.getInstance().copy(this, vipLevelType);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
