package com.hq.testClass.robot.leaguerAffair;

import org.apache.commons.lang3.RandomUtils;

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
import com.hq.chainStore.service.leaguerAffair.bs.LeaguerAffairMgr;
import com.hq.chainStore.service.leaguerAffair.data.LeaguerAffair;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerAffairRobot {
	
	private LeaguerAffairRobotData data;
	
	public static LeaguerAffairRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static LeaguerAffairRobot newInstance(int mark){
		LeaguerAffairRobot robot = new LeaguerAffairRobot();
		robot.data = LeaguerAffairRobotData.newInstance(mark);
		return robot;
	}
	
	public LeaguerAffairRobotData getData() {
		return data;
	}

	public void setData(LeaguerAffairRobotData data) {
		this.data = data;
	}
	
	public LeaguerAffair getLeaguerAffair(String leaguerAffairId) {
		return  LeaguerAffairMgr.getInstance().getLeaguerAffair(leaguerAffairId);
	}
	
	public void addMembershipCard(long storeId, String leaguerAffairId, String membershipCardId){
		AddLeaguerMembershipCard param = AddLeaguerMembershipCard.newInstance();
		param.setMembershipCardId(membershipCardId);
		LeaguerAffairMgr.getInstance().addMembershipCard(storeId, leaguerAffairId, param);
	}
	
	public void delMembershipCard(long storeId, String leaguerAffairId, String membershipCardId){
		DelLeaguerMembershipCard param = DelLeaguerMembershipCard.newInstance();
		param.setMembershipCardId(membershipCardId);
		LeaguerAffairMgr.getInstance().delMembershipCard(storeId, leaguerAffairId, param);
	}
	
	public void addDiscountCard(long storeId, String leaguerAffairId, String discountCardId){
		AddLeaguerDiscountCard param = AddLeaguerDiscountCard.newInstance();
		param.setDiscountCardId(discountCardId);
		LeaguerAffairMgr.getInstance().addDiscountCard(storeId, leaguerAffairId, param);
	}
	
	public void delDiscountCard(long storeId, String leaguerAffairId, String discountCardId){
		DelLeaguerDiscountCard param = DelLeaguerDiscountCard.newInstance();
		param.setDiscountCardId(discountCardId);
		LeaguerAffairMgr.getInstance().delDiscountCard(storeId, leaguerAffairId, param);
	}
	
	public void addProductCard(long storeId, String leaguerAffairId, String productCardId){
		AddLeaguerProductCard param = AddLeaguerProductCard.newInstance();
		param.setProductCardId(productCardId);
		LeaguerAffairMgr.getInstance().addProductCard(storeId, leaguerAffairId, param);
	}
	
	public void delProductCard(long storeId, String leaguerAffairId, String productCardId){
		DelLeaguerProductCard param = DelLeaguerProductCard.newInstance();
		param.setProductCardId(productCardId);
		LeaguerAffairMgr.getInstance().delProductCard(storeId, leaguerAffairId, param);
	}
	
	public void addArchives(long storeId, String leaguerAffairId, long index){
		AddArchives param = AddArchives.newInstance();
		FastBeanCopyer.getInstance().copy(this.getData(), param);
		param.setId(index);
		LeaguerAffairMgr.getInstance().addArchives(storeId, leaguerAffairId, param);
	}
	
	public void delArchives(long storeId, String leaguerAffairId, long archivesId){
		DelArchives param = DelArchives.newInstance();
		param.setArchivesId(archivesId);
		LeaguerAffairMgr.getInstance().delArchives(storeId, leaguerAffairId, param);
	}
	
	public void addAlarmClock(long storeId, String leaguerAffairId, long index){
		AddAlarmClock param = AddAlarmClock.newInstance();
		FastBeanCopyer.getInstance().copy(this.getData(), param);
		param.setId(index);
		LeaguerAffairMgr.getInstance().addAlarmClock(storeId, leaguerAffairId, param);
	}
	
	public void delAlarmClock(long storeId, String leaguerAffairId, long alarmClockId){
		DelAlarmClock param = DelAlarmClock.newInstance();
		param.setAlarmClockId(alarmClockId);
		LeaguerAffairMgr.getInstance().delAlarmClock(storeId, leaguerAffairId, param);
	}
	
}
