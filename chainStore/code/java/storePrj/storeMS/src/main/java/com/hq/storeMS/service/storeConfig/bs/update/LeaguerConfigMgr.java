package com.hq.storeMS.service.storeConfig.bs.update;

import java.util.List;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeConfig.apiData.BaseAttributeStatusForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerAnalysisUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerBirthdayUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerTypeUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBaseAttribute;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerConfigMgr {
	public static LeaguerConfigMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerConfigMgr.class);
	}

	// 设置客户类型
	public OperateTips updateLeaguerType(long storeId, LeaguerTypeUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateLeaguerType.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		storeConfig.getLeaguerConfig().setLeaguerTypeConfigMap(inputForm.getLeaguerTypeConfigMap());
		StoreConfigMgr.getInstance().update(storeConfig);
		tips.setSuccess(true);
		return tips;
	}
	
	// 设置客户基础属性
	public OperateTips updateBaseAttribute(long storeId, BaseAttributeStatusForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateBaseAttribute.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		List<LeaguerBaseAttribute> leaguerBaseAttributes = storeConfig.getLeaguerConfig().getLeaguerBaseAttributes();
		String attributeName = inputForm.getAttributeName();
		boolean flag = false;
		for (LeaguerBaseAttribute leaguerBaseAttribute : leaguerBaseAttributes) {
			if(leaguerBaseAttribute.getAttributeName().equals(attributeName)) {
				leaguerBaseAttribute.setStatus(inputForm.getStatus());
				leaguerBaseAttribute.setRequire(inputForm.getRequire());
				flag = true;
				break;
			}
		}
		if(flag) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 设置会员分析
	public OperateTips updateLeaguerAnalysis(long storeId, LeaguerAnalysisUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateLeaguerAnalysis.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		storeConfig.getLeaguerConfig().setLeaguerAnalysisConfigMap(inputForm.getLeaguerAnalysisConfigMap());
		StoreConfigMgr.getInstance().update(storeConfig);
		tips.setSuccess(true);
		return tips;
	}
	
	// 会员生日提醒
	public OperateTips updateLeaguerBirthday(long storeId, LeaguerBirthdayUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateLeaguerBirthday.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		storeConfig.getLeaguerConfig().setBirthdayConfigs(inputForm.getBirthdayConfigs());
		StoreConfigMgr.getInstance().update(storeConfig);
		tips.setSuccess(true);
		return tips;
	}
}
