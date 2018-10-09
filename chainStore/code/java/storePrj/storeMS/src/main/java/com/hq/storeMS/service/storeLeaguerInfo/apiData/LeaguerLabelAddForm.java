package com.hq.storeMS.service.storeLeaguerInfo.apiData;

import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerLabel;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerLabelAddForm {
	private long index;
	private String name;

	public static LeaguerLabelAddForm newInstance() {
		return new LeaguerLabelAddForm();
	}

	public LeaguerLabel toLeaguerLabel() {
		LeaguerLabel data = LeaguerLabel.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setId(String.valueOf(index));
		return data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

}
