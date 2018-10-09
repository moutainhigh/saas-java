package com.hq.chainStore.service.leaguerAffair.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.chainStore.service.storeCardInfo.data.DiscountCard;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfoSynDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerAffairSynDataHolder {

	public static LeaguerAffairSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerAffairSynDataHolder.class);
	}
	
	public LeaguerAffair getData(String ownerId, String targetId) {
		return LeaguerAffairDAO.getInstance().get(targetId);
	}
	
	/**
	 * 获取客户档案列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<Archives> getArchives(String ownerId, String leaguerId){
		LeaguerAffair leaguerAffair = getData(ownerId, leaguerId);
		return new ArrayList<Archives>(leaguerAffair.getArchivesMap().values());
	}
	
	/**
	 * 获取客户闹钟列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<AlarmClock> getAlarmClocks(String ownerId, String leaguerId){
		LeaguerAffair leaguerAffair = getData(ownerId, leaguerId);
		return new ArrayList<AlarmClock>(leaguerAffair.getAlarmClockMap().values());
	}
	
	/**
	 * 获取客户会员卡列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<MembershipCard> getMembershipCards(String ownerId, String leaguerId){
		List<MembershipCard> list = new ArrayList<MembershipCard>();
		LeaguerAffair leaguerAffair = getData(ownerId, leaguerId);
		Set<String> ids = leaguerAffair.getMembershipCardIds();
		StoreCardInfo storeCardInfo = StoreCardInfoSynDataHolder.getInstance().getData(ownerId, String.valueOf(leaguerAffair.getStoreId()));
		Map<String, MembershipCard> membershipCardMap = storeCardInfo.getMembershipCardMap();
		for (String id : ids) {
			list.add(membershipCardMap.get(id));
		}
		return list;
	}
	
	/**
	 * 获取客户优惠卷列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<DiscountCard> getDiscountCards(String ownerId, String leaguerId){
		List<DiscountCard> list = new ArrayList<DiscountCard>();
		LeaguerAffair leaguerAffair = getData(ownerId, leaguerId);
		Set<String> ids = leaguerAffair.getDiscountCardIds();
		StoreCardInfo storeCardInfo = StoreCardInfoSynDataHolder.getInstance().getData(ownerId, String.valueOf(leaguerAffair.getStoreId()));
		Map<String, DiscountCard> discountCardMap = storeCardInfo.getDiscountCardMap();
		for (String id : ids) {
			list.add(discountCardMap.get(id));
		}
		return list;
	}
	
	/**
	 * 获取客户耗卡列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<ProductCard> getProductCards(String ownerId, String leaguerId){
		List<ProductCard> list = new ArrayList<ProductCard>();
		LeaguerAffair leaguerAffair = getData(ownerId, leaguerId);
		Set<String> ids = leaguerAffair.getProductCardIds();
		StoreCardInfo storeCardInfo = StoreCardInfoSynDataHolder.getInstance().getData(ownerId, String.valueOf(leaguerAffair.getStoreId()));
		Map<String, ProductCard> productCardMap = storeCardInfo.getProductCardMap();
		for (String id : ids) {
			list.add(productCardMap.get(id));
		}
		return list;
	}
}
