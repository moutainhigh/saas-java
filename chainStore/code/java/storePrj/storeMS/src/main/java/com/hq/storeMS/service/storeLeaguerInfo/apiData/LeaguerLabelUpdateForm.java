package com.hq.storeMS.service.storeLeaguerInfo.apiData;

import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerLabel;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerLabelUpdateForm {
	private String id;
	private String name;

	public static LeaguerLabelUpdateForm newInstance() {
		return new LeaguerLabelUpdateForm();
	}
	
	public void updateLeaguerLabel(LeaguerLabel data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
