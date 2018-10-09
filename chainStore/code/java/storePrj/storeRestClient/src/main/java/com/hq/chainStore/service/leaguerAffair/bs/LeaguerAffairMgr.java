package com.hq.chainStore.service.leaguerAffair.bs;

import com.hq.chainStore.service.leaguerAffair.apiData.AddAlarmClock;
import com.hq.chainStore.service.leaguerAffair.apiData.AddArchives;
import com.hq.chainStore.service.leaguerAffair.apiData.AddLeaguerDiscountCard;
import com.hq.chainStore.service.leaguerAffair.apiData.AddLeaguerMembershipCard;
import com.hq.chainStore.service.leaguerAffair.apiData.AddLeaguerProductCard;
import com.hq.chainStore.service.leaguerAffair.apiData.DelAlarmClock;
import com.hq.chainStore.service.leaguerAffair.apiData.DelArchives;
import com.hq.chainStore.service.leaguerAffair.apiData.DelLeaguerDiscountCard;
import com.hq.chainStore.service.leaguerAffair.apiData.DelLeaguerMembershipCard;
import com.hq.chainStore.service.leaguerAffair.apiData.DelLeaguerProductCard;
import com.hq.chainStore.service.leaguerAffair.apiData.LeaguerAffairUpdateApiForm;
import com.hq.chainStore.service.leaguerAffair.apiData.LeaguerAffairUpdateType;
import com.hq.chainStore.service.leaguerAffair.data.LeaguerAffair;
import com.hq.chainStore.service.leaguerAffair.data.LeaguerAffairDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerAffairMgr {

	public static LeaguerAffairMgr getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerAffairMgr.class);
	}
	
	public LeaguerAffair getLeaguerAffair(String leaguerAffairId) {
		return LeaguerAffairDAO.getInstance().get(leaguerAffairId);
	}

	public void updateLeaguerAffair(String leaguerAffairId, LeaguerAffairUpdateApiForm apiForm) {
		LeaguerAffairDAO.getInstance().update(leaguerAffairId, apiForm);
	}
	
	//=======================具体的修改操作=======================
	public void addMembershipCard(long storeId, String leaguerAffairId, AddLeaguerMembershipCard param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setAddMembershipCard(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.AddMembershipCard.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void delMembershipCard(long storeId, String leaguerAffairId, DelLeaguerMembershipCard param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setDelMembershipCard(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.DelMembershipCard.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void addDiscountCard(long storeId, String leaguerAffairId, AddLeaguerDiscountCard param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setAddDiscountCard(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.AddDiscountCard.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void delDiscountCard(long storeId, String leaguerAffairId, DelLeaguerDiscountCard param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setDelDiscountCard(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.DelDiscountCard.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void addProductCard(long storeId, String leaguerAffairId, AddLeaguerProductCard param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setAddProductCard(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.AddProductCard.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void delProductCard(long storeId, String leaguerAffairId, DelLeaguerProductCard param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setDelProductCard(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.DelProductCard.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void addArchives(long storeId, String leaguerAffairId, AddArchives param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setAddArchives(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.AddArchives.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void delArchives(long storeId, String leaguerAffairId, DelArchives param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setDelArchives(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.DelArchives.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void addAlarmClock(long storeId, String leaguerAffairId, AddAlarmClock param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setAddAlarmClock(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.AddAlarmClock.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
	public void delAlarmClock(long storeId, String leaguerAffairId, DelAlarmClock param){
		LeaguerAffairUpdateApiForm updateForm = LeaguerAffairUpdateApiForm.newInstance();
		updateForm.setDelAlarmClock(param);
		updateForm.setUpdateType(LeaguerAffairUpdateType.DelAlarmClock.ordinal());
		updateForm.setStoreId(storeId);
		updateLeaguerAffair(leaguerAffairId, updateForm);
	}
	
}
