package com.hq.storeMS.service.storeConfig.bs.update;

import java.util.Collection;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeAddForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeSortForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeStatusForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.LeaguerConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfigBeanHelper;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.zenmind.common.hotSwap.HotSwap;

public class ExpandAttributeMgr {
	public static ExpandAttributeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ExpandAttributeMgr.class);
	}

	// 添加客户扩展属性
	public OperateTips addExpandAttribute(long storeId, ExpandAttributeAddForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.AddExpandAttribute.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		
		if (checkLabelExists(leaguerConfig, inputForm.getLabel(), inputForm.getId())) {
			tips.setTips("该属性已存在");
			return tips;
		}
		
		if(StoreConfigBeanHelper.getInstance().addExpandAttribute(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 扩展属性升降序
	public OperateTips sortExpandAttribute(long storeId, ExpandAttributeSortForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.SortExpandAttribute.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		if(StoreConfigBeanHelper.getInstance().sortExpandAttribute(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 启用禁用 必填选填
	public OperateTips statusExpandAttribute(long storeId, ExpandAttributeStatusForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.StatusExpandAttribute.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		if(StoreConfigBeanHelper.getInstance().statusExpandAttribute(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 更新扩展属性信息
	public OperateTips updateExpandAttribute(long storeId, ExpandAttributeUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateExpandAttribute.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		LeaguerConfig leaguerConfig = storeConfig.getLeaguerConfig();
		
		if (checkLabelExists(leaguerConfig, inputForm.getLabel(), inputForm.getId())) {
			tips.setTips("该属性已存在");
			return tips;
		}
		
		if(StoreConfigBeanHelper.getInstance().updateExpandAttribute(leaguerConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	/**
	 * 检查label是否已经存在  
	 * @param leaguerConfig
	 * @param label 待检验的label
	 * @param id 排除自己的ID
	 * @return
	 */
	private boolean checkLabelExists(LeaguerConfig leaguerConfig, String label, long id) {
		Collection<LeaguerExpandAttribute> values = leaguerConfig.getLeaguerExpandAttributeMap().values();
		for (LeaguerExpandAttribute config : values) {
			if(config.getLabel().equals(label) && config.getId() != id) {
				return true;
			}
		}
		return false;
	}
	
}
