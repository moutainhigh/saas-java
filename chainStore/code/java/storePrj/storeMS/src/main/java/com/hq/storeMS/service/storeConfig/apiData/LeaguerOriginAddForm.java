package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerOriginAddForm {
	private int id;

	private String originName;

	public static LeaguerOriginAddForm newInstance() {
		return new LeaguerOriginAddForm();
	}
	
	public LeaguerOriginConfig toLeaguerOriginConfig() {
		LeaguerOriginConfig data = LeaguerOriginConfig.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
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
