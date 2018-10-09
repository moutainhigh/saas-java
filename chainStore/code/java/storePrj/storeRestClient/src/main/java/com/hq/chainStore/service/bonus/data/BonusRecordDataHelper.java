package com.hq.chainStore.service.bonus.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.common.DateUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 数据处理帮助类
 *
 */
public class BonusRecordDataHelper {

	public static BonusRecordDataHelper getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordDataHelper.class);
	}

	/**
	 * 获取店铺指定月份的用户月度提成列表
	 * @param list
	 * @param date 月份
	 * @return{buserId:MonthBonus}
	 */
	public Map<Long, MonthBonus> getStoreBuserMonthBonusMap(List<BonusRecord> list, Date date) {
		Map<Long, MonthBonus> map = new HashMap<Long, MonthBonus>();
		String month = DateUtils4Client.getMonthStr(date);
		List<BonusRecord> bonusRecords = collectBonusRecordByTime(list, DateUtils4Client.getMonthFirstDay(date), DateUtils4Client.getNextMonthFirstDay(date));
		for (BonusRecord bonusRecord : bonusRecords) {
			MonthBonus monthBonus = map.get(bonusRecord.getBuserId());
			if(monthBonus == null){
				monthBonus = MonthBonus.newInstance(); 
				monthBonus.setDate(month);
				monthBonus.setAmount(0f);
				monthBonus.setCost(0f);
				map.put(bonusRecord.getBuserId(), monthBonus);
			}
			monthBonus.setAmount(monthBonus.getAmount() + bonusRecord.getAmount());
			monthBonus.setCost(monthBonus.getCost()+bonusRecord.getCost());
		}
		return map;
	}
	
	private List<BonusRecord> collectBonusRecordByTime(List<BonusRecord> list, long minTime, long maxTime){
		List<BonusRecord> bonusRecords = new ArrayList<BonusRecord>();
		for (BonusRecord bonusRecord : list) {
			if(bonusRecord.getOrderTime() > minTime && bonusRecord.getOrderTime() < maxTime){
				bonusRecords.add(bonusRecord);
			}
		}
		return bonusRecords;
	}
	
	/**
	 * 获取指定用户所有的月度提成列表
	 * @param list
	 * @param buserId用户ID
	 * @return{monthStr:MonthBonus}
	 */
	public Map<String, MonthBonus> getBuserMonthBonusMap(List<BonusRecord> list, long buserId) {
		Map<String, MonthBonus> map = new HashMap<String, MonthBonus>();
		List<BonusRecord> bonusRecords = collectBonusRecordByBuserId(list, buserId);
		String month = "";
		for (BonusRecord bonusRecord : bonusRecords) {
			Date orderTime = new Date(bonusRecord.getOrderTime());
			month = DateUtils4Client.getMonthStr(orderTime);
			MonthBonus monthBonus = map.get(month);
			if(monthBonus == null){
				monthBonus = MonthBonus.newInstance(); 
				monthBonus.setDate(month);
				monthBonus.setAmount(0f);
				monthBonus.setCost(0f);
				map.put(month, monthBonus);
			}
			monthBonus.setAmount(monthBonus.getAmount() + bonusRecord.getAmount());
			monthBonus.setCost(monthBonus.getCost()+bonusRecord.getCost());
		}
		return map;
	}
	
	private List<BonusRecord> collectBonusRecordByBuserId(List<BonusRecord> list, long buserId){
		List<BonusRecord> bonusRecords = new ArrayList<BonusRecord>();
		for (BonusRecord bonusRecord : list) {
			if(bonusRecord.getBuserId() == buserId){
				bonusRecords.add(bonusRecord);
			}
		}
		return bonusRecords;
	}
	
}
