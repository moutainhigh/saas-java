package com.hq.storeMS.service.storeConfig.bs.update;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerOriginAddForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerOriginRemoveForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerOriginUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.LeaguerConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfigBeanHelper;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerOriginMgr {
	public static LeaguerOriginMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerOriginMgr.class);
	}

	// 添加客户来源
	public OperateTips addLeaguerOrigin(long storeId, LeaguerOriginAddForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.AddLeaguerOrigin.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		
		if (checkNameExists(leaguerConfig, inputForm.getOriginName())) {
			tips.setTips("该来源已存在");
			return tips;
		}
		
		if(StoreConfigBeanHelper.getInstance().addLeaguerOrigin(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 删除客户来源
	public OperateTips removeLeaguerOrigin(long storeId, LeaguerOriginRemoveForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.RemoveLeaguerOrigin.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		if(StoreConfigBeanHelper.getInstance().removeLeaguerOrigin(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			//级联更新客户的来源
			List<LeaguerDetail> list = LeaguerDetailMgr.getInstance().getLeaguerDetailListByStoreId(storeId);
			for (LeaguerDetail leaguerDetail : list) {
				if(leaguerDetail.getOriginId() == inputForm.getId()) {
					leaguerDetail.setOriginId(0);
					LeaguerDetailMgr.getInstance().updateSimple(leaguerDetail);
				}
			}
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 更新客户来源
	public OperateTips updateLeaguerOrigin(long storeId, LeaguerOriginUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateLeaguerOrigin.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		
		if (checkNameExists(leaguerConfig, inputForm.getOriginName())) {
			tips.setTips("该来源已存在");
			return tips;
		}
		
		if(StoreConfigBeanHelper.getInstance().updateLeaguerOrigin(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkNameExists(LeaguerConfig leaguerConfig, String name) {
		Collection<LeaguerOriginConfig> values = leaguerConfig.getLeaguerOriginConfigMap().values();
		for (LeaguerOriginConfig leaguerOriginConfig : values) {
			if(leaguerOriginConfig.getOriginName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
