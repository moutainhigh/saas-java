package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerOriginUpdateForm {
	private int id;

	private String originName;

	public static LeaguerOriginUpdateForm newInstance() {
		return new LeaguerOriginUpdateForm();
	}
	
	public void updateLeaguerOriginConfig(LeaguerOriginConfig data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}
}
