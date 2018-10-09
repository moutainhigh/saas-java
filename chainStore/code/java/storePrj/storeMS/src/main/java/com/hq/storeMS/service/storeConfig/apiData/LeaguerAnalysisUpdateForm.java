package com.hq.storeMS.service.storeConfig.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;

public class LeaguerAnalysisUpdateForm {
	
	private Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap = new HashMap<Integer, LeaguerAnalysisConfig>();

	public static LeaguerAnalysisUpdateForm newInstance() {
		return new LeaguerAnalysisUpdateForm();
	}

	public Map<Integer, LeaguerAnalysisConfig> getLeaguerAnalysisConfigMap() {
		return leaguerAnalysisConfigMap;
	}

	public void setLeaguerAnalysisConfigMap(Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap) {
		this.leaguerAnalysisConfigMap = leaguerAnalysisConfigMap;
	}

}
