package com.hq.storeMS.service.leaguerAffair.data;

import java.util.Map;
import java.util.Set;

import com.hq.storeMS.service.leaguerAffair.apiData.AddAlarmClock;
import com.hq.storeMS.service.leaguerAffair.apiData.AddLeaguerDiscountCard;
import com.hq.storeMS.service.leaguerAffair.apiData.AddLeaguerMembershipCard;
import com.hq.storeMS.service.leaguerAffair.apiData.AddLeaguerProductCard;
import com.hq.storeMS.service.leaguerAffair.apiData.DelAlarmClock;
import com.hq.storeMS.service.leaguerAffair.apiData.DelArchives;
import com.hq.storeMS.service.leaguerAffair.apiData.DelLeaguerDiscountCard;
import com.hq.storeMS.service.leaguerAffair.apiData.DelLeaguerMembershipCard;
import com.hq.storeMS.service.leaguerAffair.apiData.DelLeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerAffairBeanHelper {

	public static LeaguerAffairBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerAffairBeanHelper.class);
	}

	public boolean addMembershipCard(LeaguerAffair leaguerAffair, AddLeaguerMembershipCard data) {
		Set<String> ids = leaguerAffair.getMembershipCardIds();
		if (!ids.contains(data.getMembershipCardId())) {
			ids.add(data.getMembershipCardId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delMembershipCard(LeaguerAffair leaguerAffair, DelLeaguerMembershipCard data) {
		Set<String> ids = leaguerAffair.getMembershipCardIds();
		if (ids.contains(data.getMembershipCardId())) {
			ids.remove(data.getMembershipCardId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addDiscountCard(LeaguerAffair leaguerAffair, AddLeaguerDiscountCard data) {
		Set<String> ids = leaguerAffair.getDiscountCardIds();
		if (!ids.contains(data.getDiscountCardId())) {
			ids.add(data.getDiscountCardId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delDiscountCard(LeaguerAffair leaguerAffair, DelLeaguerDiscountCard data) {
		Set<String> ids = leaguerAffair.getDiscountCardIds();
		if (ids.contains(data.getDiscountCardId())) {
			ids.remove(data.getDiscountCardId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addProductCard(LeaguerAffair leaguerAffair, AddLeaguerProductCard data) {
		Set<String> ids = leaguerAffair.getProductCardIds();
		if (!ids.contains(data.getProductCardId())) {
			ids.add(data.getProductCardId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delProductCard(LeaguerAffair leaguerAffair, DelLeaguerProductCard data) {
		Set<String> ids = leaguerAffair.getProductCardIds();
		if (ids.contains(data.getProductCardId())) {
			ids.remove(data.getProductCardId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addArchives(LeaguerAffair leaguerAffair, Archives data) {
		long index = leaguerAffair.getArchivesIndex();
		Map<Long, Archives> map = leaguerAffair.getArchivesMap();
		if (!map.containsKey(data.getId()) && index+1 == data.getId()) {
			map.put(data.getId(), data);
			leaguerAffair.setArchivesIndex(data.getId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delArchives(LeaguerAffair leaguerAffair, DelArchives data) {
		Map<Long, Archives> map = leaguerAffair.getArchivesMap();
		if (map.containsKey(data.getArchivesId())) {
			map.remove(data.getArchivesId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addAlarmClock(LeaguerAffair leaguerAffair, AddAlarmClock data) {
		long index = leaguerAffair.getAlarmClockIndex();
		Map<Long, AlarmClock> map = leaguerAffair.getAlarmClockMap();
		AlarmClock alarmClock = data.toAlarmClock();
		if (!map.containsKey(alarmClock.getId()) && index+1 == alarmClock.getId()) {
			map.put(alarmClock.getId(), alarmClock);
			leaguerAffair.setAlarmClockIndex(alarmClock.getId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delAlarmClock(LeaguerAffair leaguerAffair, DelAlarmClock data) {
		Map<Long, AlarmClock> map = leaguerAffair.getAlarmClockMap();
		if (map.containsKey(data.getAlarmClockId())) {
			map.remove(data.getAlarmClockId());
			return true;
		} else {
			return false;
		}
	}

}
