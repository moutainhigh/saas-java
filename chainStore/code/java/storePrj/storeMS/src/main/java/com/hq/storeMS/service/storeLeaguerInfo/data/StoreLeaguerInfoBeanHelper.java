package com.hq.storeMS.service.storeLeaguerInfo.data;

import java.util.Map;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerLabelAddForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerLabelRemoveForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerLabelUpdateForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerUpdateInfoApiForm;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoBeanHelper {

	public static StoreLeaguerInfoBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoBeanHelper.class);
	}

	public boolean addLeaguerInfo(StoreLeaguerInfo info, Leaguer leaguer) {
		if(info == null){
			return false;
		}
		Map<String, Leaguer> leaguerInfoMap = info.getLeaguerInfoMap();
		if (!leaguerInfoMap.containsKey(leaguer.getId())) {
			leaguerInfoMap.put(leaguer.getId(), leaguer);
			return true;
		} else if(leaguerInfoMap.get(leaguer.getId()).getEntityState() == EntityState.Deleted.ordinal()){
			leaguerInfoMap.put(leaguer.getId(), leaguer);
			return true;
		}
		return false;
	}
	
	public boolean removeLeaguerInfo(StoreLeaguerInfo info, String leaguerId) {
		if(info == null){
			return false;
		}
		Map<String, Leaguer> leaguerInfoMap = info.getLeaguerInfoMap();
		if (leaguerInfoMap.containsKey(leaguerId)) {
			Leaguer leaguer = leaguerInfoMap.get(leaguerId);
			leaguer.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}
	
	public boolean updateLeaguerInfo(StoreLeaguerInfo info, LeaguerUpdateInfoApiForm updateInfoApiForm) {
		if(info == null){
			return false;
		}
		Map<String, Leaguer> leaguerInfoMap = info.getLeaguerInfoMap();
		if (leaguerInfoMap.containsKey(updateInfoApiForm.getId())) {
			Leaguer leaguer = leaguerInfoMap.get(updateInfoApiForm.getId());
			String oldValue = leaguer.toString();
			updateInfoApiForm.updateLeaguer(leaguer);
			String newValue = leaguer.toString();
			if(!oldValue.equals(newValue)) {//新旧对象不一样  则更新
				leaguerInfoMap.put(leaguer.getId(), leaguer);
				return true;
			}
		}
		return false;
	}
	
	/*************************** 标签基本操作 ***************************/
	public boolean addLeaguerLabel(StoreLeaguerInfo storeData, LeaguerLabelAddForm inputForm) {
		if (storeData == null) {
			return false;
		}
		boolean success = false;
		Map<String, LeaguerLabel> leaguerLabelMap = storeData.getLeaguerLabelMap();
		LeaguerLabel data = inputForm.toLeaguerLabel();
		long index = inputForm.getIndex();
		if (!leaguerLabelMap.containsKey(data.getId()) && storeData.getLabelIndex() + 1 == index) {
			leaguerLabelMap.put(data.getId(), data);
			storeData.setLabelIndex(index);
			success = true;
		}
		return success;
	}
	
	public boolean removeLeaguerLabel(StoreLeaguerInfo storeData, LeaguerLabelRemoveForm inputForm) {
		if (storeData == null) {
			return false;
		}
		boolean success = false;
		Map<String, LeaguerLabel> leaguerLabelMap = storeData.getLeaguerLabelMap();
		if (leaguerLabelMap.containsKey(inputForm.getId())) {
			LeaguerLabel data = leaguerLabelMap.get(inputForm.getId());
			data.setEntityState(EntityState.Deleted.ordinal());
			data.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}
	
	public boolean updateLeaguerLabel(StoreLeaguerInfo storeData, LeaguerLabelUpdateForm inputForm) {
		if (storeData == null) {
			return false;
		}
		boolean success = false;
		Map<String, LeaguerLabel> leaguerLabelMap = storeData.getLeaguerLabelMap();
		if (leaguerLabelMap.containsKey(inputForm.getId())) {
			LeaguerLabel data = leaguerLabelMap.get(inputForm.getId());
			inputForm.updateLeaguerLabel(data);
			data.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

}
