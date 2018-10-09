package com.hq.chainStore.service.storeConfig.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerTypeConfig;

public class LeaguerTypeUpdateForm {
	private Map<Integer, LeaguerTypeConfig> leaguerTypeConfigMap = new HashMap<Integer, LeaguerTypeConfig>();

	public static LeaguerTypeUpdateForm newInstance() {
		return new LeaguerTypeUpdateForm();
	}

	public Map<Integer, LeaguerTypeConfig> getLeaguerTypeConfigMap() {
		return leaguerTypeConfigMap;
	}

	public void setLeaguerTypeConfigMap(Map<Integer, LeaguerTypeConfig> leaguerTypeConfigMap) {
		this.leaguerTypeConfigMap = leaguerTypeConfigMap;
	}
}
