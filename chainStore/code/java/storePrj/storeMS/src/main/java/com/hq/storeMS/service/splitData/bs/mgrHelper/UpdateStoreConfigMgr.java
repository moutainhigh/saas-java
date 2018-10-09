package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.LeaguerConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBirthdayConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitBirthdayEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerAnalysisEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class UpdateStoreConfigMgr {
	
	public static UpdateStoreConfigMgr getInstance() {
		return HotSwap.getInstance().getSingleton(UpdateStoreConfigMgr.class);
	}
	
	public void updateConfig(long storeId) {
		try {
			StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
			updateAnalysisConfig(storeConfig);
			updateBirthdayConfig(storeConfig);
			StoreConfigMgr.getInstance().update(storeConfig);
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "UpdateStoreConfigMgr[updateConfig]", "更新店铺配置项失败"+storeId, e);
		}
	}
	
	private void updateAnalysisConfig(StoreConfig storeConfig) {
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		Map<Integer, LeaguerAnalysisConfig> analysisMap = leaguerConfig.getLeaguerAnalysisConfigMap();
		if(MapUtils.isEmpty(analysisMap)){
			SysInitLeaguerAnalysisEnum[] leaguerAnalysisValues = SysInitLeaguerAnalysisEnum.values();
			leaguerConfig.setLeaguerAnalysisConfigIndex(leaguerAnalysisValues.length);
			for (SysInitLeaguerAnalysisEnum sysInitLeaguerAnalysisEnum : leaguerAnalysisValues) {
				LeaguerAnalysisConfig leaguerAnalysisConfig = LeaguerAnalysisConfig.newInstance(sysInitLeaguerAnalysisEnum);
				analysisMap.put(leaguerAnalysisConfig.getId(), leaguerAnalysisConfig);
			}
		}
	}
	
	private void updateBirthdayConfig(StoreConfig storeConfig) {
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		List<LeaguerBirthdayConfig> birthdayConfigs = leaguerConfig.getBirthdayConfigs();
		SysInitBirthdayEnum[] birthdayEnums = SysInitBirthdayEnum.values();
		if(CollectionUtils.isEmpty(birthdayConfigs) || birthdayConfigs.size() != birthdayEnums.length){
			birthdayConfigs.clear();
			for (SysInitBirthdayEnum birthdayEnum : birthdayEnums) {
				birthdayConfigs.add(LeaguerBirthdayConfig.newInstance(birthdayEnum));
			}
		}
	}
}
