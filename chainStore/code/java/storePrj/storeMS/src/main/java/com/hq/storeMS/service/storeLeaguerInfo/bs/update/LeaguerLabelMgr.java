package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import java.util.List;
import java.util.Map;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerLabelAddForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerLabelRemoveForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerLabelUpdateForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerLabel;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfoBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerLabelMgr {

	public static LeaguerLabelMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerLabelMgr.class);
	}

	public OperateTips addLeaguerLabel(long storeId, LeaguerLabelAddForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.AddLeaguerLabel.getMark() + "失败");
		StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);

		Map<String, LeaguerLabel> leaguerLabelMap = storeData.takeLeaguerLabelMapWithNameKey();
		if (!checkNameExists(leaguerLabelMap, inputForm.getName()) && StoreLeaguerInfoBeanHelper.getInstance().addLeaguerLabel(storeData, inputForm)) {// 名称不存在
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeData);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, inputForm.getName(), OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.AddLeaguerLabel.getMark()));
			tips.setSuccess(true);
		}

		return tips;
	}

	public OperateTips removeLeaguerLabel(long storeId, LeaguerLabelRemoveForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.RemoveLeaguerLabel.getMark() + "失败");
		StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);

		if (StoreLeaguerInfoBeanHelper.getInstance().removeLeaguerLabel(storeData, inputForm)) {
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeData);
			String labelName = storeData.getLeaguerLabelMap().get(inputForm.getId()).getName();
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, labelName, OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.RemoveLeaguerLabel.getMark()));
		}
		tips.setSuccess(true);
		return tips;
	}

	public OperateTips updateLeaguerLabel(long storeId, LeaguerLabelUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.UpdateLeaguerLabel.getMark() + "失败");
		StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);

		Map<String, LeaguerLabel> leaguerLabelMap = storeData.takeLeaguerLabelMapWithNameKey();
		if (!checkNameExists(leaguerLabelMap, inputForm.getName())) {// 名称不存在
			StoreLeaguerInfoBeanHelper.getInstance().updateLeaguerLabel(storeData, inputForm);
		}
		StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeData);
		String labelName = storeData.getLeaguerLabelMap().get(inputForm.getId()).getName();
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, labelName, OpLogTypeEnum.Leaguer, StoreLeaguerInfoUpdateType.UpdateLeaguerLabel.getMark()));
		tips.setSuccess(true);
		return tips;
	}

	public OperateTips addLeaguerLabelList(long storeId, List<LeaguerLabelAddForm> inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreLeaguerInfoUpdateType.AddLeaguerLabelList.getMark() + "失败");
		StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);
		long labelIndex = storeData.getLabelIndex();
		Map<String, LeaguerLabel> leaguerLabelMap = storeData.takeLeaguerLabelMapWithNameKey();

		for (LeaguerLabelAddForm leaguerLabelAddForm : inputForm) {
			if (!checkNameExists(leaguerLabelMap, leaguerLabelAddForm.getName())) {// 名称不存在
				leaguerLabelAddForm.setIndex(++labelIndex);
				StoreLeaguerInfoBeanHelper.getInstance().addLeaguerLabel(storeData, leaguerLabelAddForm);
			}
		}
		
		StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeData);
		tips.setSuccess(true);
		return tips;
	}

	//检查标签是否已存在 true:存在 false:不存在
	private boolean checkNameExists(Map<String, LeaguerLabel> leaguerLabelMap, String name) {
		LeaguerLabel label = leaguerLabelMap.get(name);
		if (label != null) {// 名称已存在
			if(label.getEntityState() == EntityState.Deleted.ordinal()) {
				label.setEntityState(EntityState.Normal.ordinal());
				label.setLastUpdateTime(System.currentTimeMillis());
			}
			return true;
		}
		return false;
	}
}
