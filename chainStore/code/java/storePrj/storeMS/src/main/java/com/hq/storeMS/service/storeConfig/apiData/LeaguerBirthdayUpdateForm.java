package com.hq.storeMS.service.storeConfig.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBirthdayConfig;

public class LeaguerBirthdayUpdateForm {
	private List<LeaguerBirthdayConfig> birthdayConfigs = new ArrayList<LeaguerBirthdayConfig>();

	public static LeaguerBirthdayUpdateForm newInstance() {
		LeaguerBirthdayUpdateForm data = new LeaguerBirthdayUpdateForm();
		return data;
	}

	public List<LeaguerBirthdayConfig> getBirthdayConfigs() {
		return birthdayConfigs;
	}

	public void setBirthdayConfigs(List<LeaguerBirthdayConfig> birthdayConfigs) {
		this.birthdayConfigs = birthdayConfigs;
	}
}
