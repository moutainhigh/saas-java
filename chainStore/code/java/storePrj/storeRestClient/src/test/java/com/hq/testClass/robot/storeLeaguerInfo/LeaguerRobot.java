package com.hq.testClass.robot.storeLeaguerInfo;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerService;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;

public class LeaguerRobot {

	private LeaguerRobotData data;

	public static LeaguerRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static LeaguerRobot newInstance(int mark) {
		LeaguerRobot robot = new LeaguerRobot();
		robot.data = LeaguerRobotData.newInstance(mark);
		return robot;
	}

	public List<StoreLeaguerService> findLeaguerServiceList(long minTime, long maxTime, String leaguerId) {
		return LeaguerRobotHelper.getInstance().findLeaguerServiceList(this, minTime, maxTime, leaguerId);
	}

	public Leaguer addLeaguer(long storeId) {
		return LeaguerRobotHelper.getInstance().addLeaguer(this, storeId);
	}

	public void addLeaguerList(long storeId) {
		LeaguerRobotHelper.getInstance().addLeaguerList(this, storeId);
	}

	public void addAttention(long storeId, String leaguerId) {
		LeaguerRobotHelper.getInstance().addAttention(this, storeId, leaguerId);
	}

	public void removeAttention(long storeId, String leaguerId) {
		LeaguerRobotHelper.getInstance().removeAttention(this, storeId, leaguerId);
	}

	public void updateLeaguer(Leaguer leaguer) {
		LeaguerRobotHelper.getInstance().updateLeaguer(this, leaguer);
	}

	public Leaguer getLeaguer(String leaguerId) {
		return LeaguerRobotHelper.getInstance().getLeaguer(this, leaguerId);
	}

	public StoreLeaguerInfo getStoreLeaguerInfo(long storeId) {
		return LeaguerRobotHelper.getInstance().getStoreLeaguerInfo(this, storeId);
	}

	/********************* 客户修改新增接口 充值、设置会员卡、购买次卡、划卡（消费次卡） ******************/

	public void rechargeMemberCard(long storeId, String cardId, String leaguerId) {
		LeaguerRobotHelper.getInstance().rechargeMemberCard(storeId, cardId, leaguerId);
	}

	public void updateMemberCard(long storeId, String cardId, String leaguerId) {
		LeaguerRobotHelper.getInstance().updateMemberCard(storeId, cardId, leaguerId);
	}

	public LeaguerRobotData getData() {
		return data;
	}

	public void setData(LeaguerRobotData data) {
		this.data = data;
	}

	public String getId() {
		return this.data.getLeaguerId();
	}

}
