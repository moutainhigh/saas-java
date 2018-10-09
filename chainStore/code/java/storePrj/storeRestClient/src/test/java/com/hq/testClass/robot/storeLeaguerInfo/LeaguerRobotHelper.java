package com.hq.testClass.robot.storeLeaguerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeLeaguerInfo.apiData.AddAttention;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerUpdateInfoApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.RechargeMemberCardForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.RemoveAttention;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerService;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerRobotHelper {

	private static LeaguerRobotHelper instance = new LeaguerRobotHelper();

	public static LeaguerRobotHelper getInstance() {
		return instance;
	}

	public List<StoreLeaguerService> findLeaguerServiceList(LeaguerRobot robot, long minTime, long maxTime, String leaguerId) {
		return StoreLeaguerInfoMgr.getInstance().findLeaguerServiceList(minTime, maxTime, leaguerId, 10 , 1);
	}
	
	public Leaguer addLeaguer(LeaguerRobot robot, long storeId) {
		LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
		LeaguerRobotData data = robot.getData();
		FastBeanCopyer.getInstance().copy(data, addForm);

		StoreLeaguerInfo info = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		List<Leaguer> list = info.getLeaguerInfoList();
		Map<Long,Leaguer> map = new HashMap<Long,Leaguer>();
		for (Leaguer tmp : list) {
			map.put(tmp.getPhone(), tmp);
		}
		
		while (map.containsKey(addForm.getPhone())) {
			addForm.setPhone(addForm.getPhone()+100);
		}
		
		StoreLeaguerInfoMgr.getInstance().addLeaguerInfo(storeId, addForm);
		StoreLeaguerInfo info2 = StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		List<Leaguer> list2 = info.getLeaguerInfoList();
		Leaguer leaguer = info2.getLeaguerInfoList().get(0);
		for (Leaguer tmp : list2) {
			if(addForm.getPhone() == tmp.getPhone()){
				leaguer = tmp;
				break;
			}
		}
		return leaguer;
	}
	
	public void addLeaguerList(LeaguerRobot robot, long storeId) {
		List<LeaguerAddApiForm> datas = new ArrayList<LeaguerAddApiForm>();
		for (int i = 0; i < 3; i++) {
			LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
			int mark = RandomUtils.nextInt(100, 500);
			addForm.setName("name-" + mark);
			addForm.setPhone(13800000000L + mark);
			datas.add(addForm);
		}
		datas.get(0).setPhone(13800005776L);
		StoreLeaguerInfoMgr.getInstance().addLeaguerInfoList(storeId, datas);
	}
	
	public void addAttention(LeaguerRobot robot, long storeId, String leaguerId) {
		AddAttention addAttention = AddAttention.newInstance();
		addAttention.setLeaguerId(leaguerId);
		StoreLeaguerInfoMgr.getInstance().addAttention(storeId, addAttention);
	}
	
	public void removeAttention(LeaguerRobot robot, long storeId, String leaguerId) {
		RemoveAttention removeAttention = RemoveAttention.newInstance();
		removeAttention.setLeaguerId(leaguerId);
		StoreLeaguerInfoMgr.getInstance().removeAttention(storeId, removeAttention);
	}

	public void updateLeaguer(LeaguerRobot robot, Leaguer leaguer) {
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		LeaguerUpdateInfoApiForm leaguerUpdateInfoData = LeaguerUpdateInfoApiForm.newInstance();
		
		String leaguerId = leaguer.getId();
		
		long storeId = Long.valueOf(leaguerId.split("_")[0]);
		
		LeaguerRobotData data = robot.getData();
		
		FastBeanCopyer.getInstance().copy(leaguer, leaguerUpdateInfoData);
		
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.UpdateLeaguerInfo.ordinal());
		updateForm.setLeaguerUpdateInfoData(leaguerUpdateInfoData);

		StoreLeaguerInfoMgr.getInstance().updateStoreLeaguerInfo(data.getStoreId(), updateForm);
	}

	public Leaguer getLeaguer(LeaguerRobot robot, String leaguerId) {
		return StoreLeaguerInfoMgr.getInstance().getLeaguer(leaguerId);
	}
	
	public StoreLeaguerInfo getStoreLeaguerInfo(LeaguerRobot robot, long storeId) {
		return StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
	}

	/*********************客户修改新增接口 充值、设置会员卡、购买次卡、划卡（消费次卡）******************/
	public void rechargeMemberCard(long storeId,String cardId,String leaguerId) {
		RechargeMemberCardForm rechargeMemberCardForm = RechargeMemberCardForm.newInstance();
		rechargeMemberCardForm.setCardId(cardId);//次卡id
		rechargeMemberCardForm.setLeaguerId(leaguerId);//会员id
		rechargeMemberCardForm.setAmount(3000f);
		StoreLeaguerInfoMgr.getInstance().rechargeMemberCard(storeId, rechargeMemberCardForm);
	}
	
	public void updateMemberCard(long storeId,String cardId,String leaguerId) {
		UpdateMemberCardForm updateMemberCardForm = UpdateMemberCardForm.newInstance();
		updateMemberCardForm.setCardId(cardId);//次卡id
		updateMemberCardForm.setLeaguerId(leaguerId);//会员id
		updateMemberCardForm.setLimitTime(100);
		updateMemberCardForm.setLimitUnit(1);
		updateMemberCardForm.setNumber(String.valueOf(RandomUtils.nextInt(2000, 3000)));
		StoreLeaguerInfoMgr.getInstance().updateMemberCard(storeId, updateMemberCardForm);
	}
}