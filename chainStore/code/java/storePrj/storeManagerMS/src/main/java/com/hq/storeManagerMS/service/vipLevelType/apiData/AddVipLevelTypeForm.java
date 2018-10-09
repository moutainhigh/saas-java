package com.hq.storeManagerMS.service.vipLevelType.apiData;

import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AddVipLevelTypeForm {
	
	private String name;

	public static AddVipLevelTypeForm newInstance() {
		return new AddVipLevelTypeForm();
	}

	public VipLevelType toVipLevelType() {
		VipLevelType vipLevelType = VipLevelType.newInstance();
		FastBeanCopyer.getInstance().copy(this, vipLevelType);
		return vipLevelType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
