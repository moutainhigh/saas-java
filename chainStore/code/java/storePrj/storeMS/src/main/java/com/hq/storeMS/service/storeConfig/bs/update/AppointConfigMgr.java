package com.hq.storeMS.service.storeConfig.bs.update;

import java.util.Collection;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeConfig.apiData.AppointTimeUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.CancelAppointAddForm;
import com.hq.storeMS.service.storeConfig.apiData.CancelAppointRemoveForm;
import com.hq.storeMS.service.storeConfig.apiData.CancelAppointUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.AppointConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfigBeanHelper;
import com.hq.storeMS.service.storeConfig.data.appoint.AppointTimeConfig;
import com.hq.storeMS.service.storeConfig.data.appoint.CancelAppointConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointConfigMgr {
	public static AppointConfigMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppointConfigMgr.class);
	}

	// 设置预约时间段
	public OperateTips updateAppointTime(long storeId, AppointTimeUpdateForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateAppointTime.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		AppointTimeConfig appointTimeConfig = storeConfig.takeAppointTimeConfig();
		inputData.updateAppointTime(appointTimeConfig);
		StoreConfigMgr.getInstance().update(storeConfig);
		tips.setSuccess(true);
		return tips;
	}
	
	// 新增取消原因
	public OperateTips addCancelReason(long storeId, CancelAppointAddForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.AddCancelReason.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		AppointConfig appointConfig = storeConfig.getAppointConfig();
		
		if (checkReasonExists(appointConfig, inputForm.getReason())) {
			tips.setTips("该原因已存在");
			return tips;
		}
		
		if(StoreConfigBeanHelper.getInstance().addCancelAppointConfig(appointConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 删除取消原因
	public OperateTips removeCancelReason(long storeId, CancelAppointRemoveForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.RemoveCancelReason.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		AppointConfig appointConfig = storeConfig.getAppointConfig();
		if(StoreConfigBeanHelper.getInstance().removeCancelAppointConfig(appointConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 修改取消原因
	public OperateTips updateCancelReason(long storeId, CancelAppointUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.UpdateCancelReason.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		AppointConfig appointConfig = storeConfig.getAppointConfig();
		
		if (checkReasonExists(appointConfig, inputForm.getReason())) {
			tips.setTips("该原因已存在");
			return tips;
		}
		
		if(StoreConfigBeanHelper.getInstance().updateCancelReason(appointConfig, inputForm)) {
			StoreConfigMgr.getInstance().update(storeConfig);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkReasonExists(AppointConfig appointConfig, String reason) {
		Collection<CancelAppointConfig> values = appointConfig.getCancelAppointConfigMap().values();
		for (CancelAppointConfig config : values) {
			if(config.getReason().equals(reason)) {
				return true;
			}
		}
		return false;
	}
}
