package com.hq.storeMS.service.bonusRecord.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordForm;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.UserBonus;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusBeanHelper {

	public static BonusBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(BonusBeanHelper.class);
	}
	
	/**
	 * 一个订单的提成 转换成多个提成明细
	 * @return
	 */
	public List<BonusRecord> addForm2BonusRecords(BonusRecordForm addForm){
		List<BonusRecord> bonusRecords = new ArrayList<BonusRecord>();
		String bonusId = BonusInfo.genBonusId(addForm.getPrdCardPayType(), addForm.getBuyType(), addForm.getPgId(), addForm.getProductCardId());
		Collection<UserBonus> userBonus = addForm.getUserBonusMap().values();
		for (UserBonus bonus : userBonus) {
			BonusRecord record = BonusRecord.newInstance();
			FastBeanCopyer.getInstance().copy(addForm, record);
			FastBeanCopyer.getInstance().copy(bonus, record);
			record.setBuyId(bonusId);
			bonusRecords.add(record);
		}
		return bonusRecords;
	}
	
	/**
	 * 多个OrderBonus 转换成 提成明细列表 
	 * @param list
	 * @return
	 */
	public List<BonusRecord> mutiOrderBonus2BonusRecords(List<OrderBonus> list){
		List<BonusRecord> result = new ArrayList<BonusRecord>();
		for (OrderBonus bonus : list) {
			result.addAll(orderBonus2BonusRecords(bonus));
		}
		return result;
	}
	
	/**
	 * 单个OrderBonus 转换成 提成明细列表 
	 * @param orderBonus
	 * @return
	 */
	public List<BonusRecord> orderBonus2BonusRecords(OrderBonus orderBonus){
		List<BonusRecord> bonusRecords = new ArrayList<BonusRecord>();
		
		Collection<BonusInfo> bonusInfos = orderBonus.getBonusInfoMap().values();
		for (BonusInfo info : bonusInfos) {
			Collection<UserBonus> userBonus = info.getUserBonusMap().values();
			for (UserBonus bonus : userBonus) {
				BonusRecord record = BonusRecord.newInstance();
				FastBeanCopyer.getInstance().copy(orderBonus, record);
				FastBeanCopyer.getInstance().copy(info, record);
				FastBeanCopyer.getInstance().copy(bonus, record);
				record.setBuyId(info.getBonusId());
				bonusRecords.add(record);
			}
		}
		return bonusRecords;
	}
	
	/**
	 * 提成明细列表 转成OrderBonus列表
	 * @param list
	 * @return
	 */
	public List<OrderBonus> bonusRecords2MutiOrderBonus(List<BonusRecord> list){
		Map<Long, List<BonusRecord>> map = new HashMap<Long, List<BonusRecord>>();//按单个订单分组
		for (BonusRecord bonusRecord : list) {
			List<BonusRecord> orderBonusRecords = map.get(bonusRecord.getOrderId());
			if(orderBonusRecords == null) {
				orderBonusRecords = new ArrayList<BonusRecord>();
				map.put(bonusRecord.getOrderId(), orderBonusRecords);
			}
			orderBonusRecords.add(bonusRecord);
		}
		
		List<OrderBonus> result = new ArrayList<OrderBonus>();
		Collection<List<BonusRecord>> values = map.values();
		for (List<BonusRecord> records : values) {
			result.add(bonusRecords2OrderBonus(records));
		}
		return result;
	}
	
	/**
	 * 单个订单的提成列表 转成单个OrderBonus
	 * @param list
	 * @return
	 */
	public OrderBonus bonusRecords2OrderBonus(List<BonusRecord> list){
		OrderBonus orderBonus = OrderBonus.newInstance();
		if(CollectionUtils.isEmpty(list)) {
			return orderBonus;
		}
		
		addOrderInfo(list.get(0), orderBonus);
		for (BonusRecord record : list) {
			BonusInfo info = BonusInfo.newInstance();
			
			String tmpId = record.getBuyId();
			String payType = tmpId.split("_")[0];
			if(StringUtils.contains(tmpId, ServerConstants.STORE_LEAGUERPRDCARD_ID_SUFFFIX)) {
				String leaguerPrdCardId = ServerConstants.STORE_LEAGUERPRDCARD_ID_SUFFFIX+StringUtils.substringAfterLast(tmpId, ServerConstants.STORE_LEAGUERPRDCARD_ID_SUFFFIX);
				info.setLeaguerPrdCardId(leaguerPrdCardId);
			}else {
				info.setLeaguerPrdCardId("");
			}
			
			info.setBonusId(record.getBuyId());
			info.setBuyType(record.getBuyType());
			info.setPgId(record.getPgId());
			info.setPrdCardPayType(Integer.valueOf(payType));
			
			UserBonus userBonus = UserBonus.newInstance();
			FastBeanCopyer.getInstance().copy(record, userBonus);
			info.getUserBonusMap().put(userBonus.getBuserId(), userBonus);
			addBonusInfo(info, orderBonus);
		}
		return orderBonus;
	}
	
	private void addOrderInfo(BonusRecord record, OrderBonus orderBonus) {
		FastBeanCopyer.getInstance().copy(record, orderBonus);
		orderBonus.setId(record.getOrderId());
	}
	
	private void addBonusInfo(BonusInfo info, OrderBonus orderBonus) {
		Map<String, BonusInfo> bonusInfoMap = orderBonus.getBonusInfoMap();
		if(!bonusInfoMap.containsKey(info.getBonusId())) {
			bonusInfoMap.put(info.getBonusId(), info);
		}else {
			Map<Long, UserBonus> userBonusMap = bonusInfoMap.get(info.getBonusId()).getUserBonusMap();
			userBonusMap.putAll(info.getUserBonusMap());
		}
	}
	
}
