package com.hq.storeMS.service.leaguerDetail.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PayTypeEnum;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.leaguerDetail.apiData.PreStoreCardAddCountForm;
import com.hq.storeMS.service.leaguerDetail.apiData.PreStoreCardAddForm;
import com.hq.storeMS.service.leaguerDetail.apiData.PreStoreCardReduceCountForm;
import com.hq.storeMS.service.leaguerDetail.apiData.PreStoreCardRemoveForm;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItem;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.AddProductCardCountForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.RechargeMemberCardForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.ReduceProductCardCountForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerCardEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerPrdCardItem;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LimitUnitEnum;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailBeanHelper {
	public static LeaguerDetailBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailBeanHelper.class);
	}
	
	/***************************************客户预存卡操作***************************************/
	//新增预存卡 购买
	public boolean addPreStoreCard(LeaguerDetail leaguer, PreStoreCardAddForm inputForm) {
		Map<String, PreStoreCard> leaguerPreStoreCardMap = leaguer.getLeaguerPreStoreCardMap();
		PreStoreCard preStoreCard = inputForm.toPreStoreCard();
		if(!leaguerPreStoreCardMap.containsKey(preStoreCard.getId())) {
			leaguerPreStoreCardMap.put(preStoreCard.getId(), preStoreCard);
			leaguer.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	//删除预存卡 整体退单
	public boolean removePreStoreCard(LeaguerDetail leaguer, PreStoreCardRemoveForm inputForm) {
		Map<String, PreStoreCard> leaguerPreStoreCardMap = leaguer.getLeaguerPreStoreCardMap();
		if(leaguerPreStoreCardMap.containsKey(inputForm.getId())) {
			PreStoreCard preStoreCard = leaguerPreStoreCardMap.get(inputForm.getId());
			preStoreCard.setState(LeaguerCardEnum.BACKCARD.ordinal());
			leaguer.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	//增加次数 部分退单
	public boolean increasePreStoreCardCount(LeaguerDetail leaguer, PreStoreCardAddCountForm inputForm) {
		Map<String, PreStoreCard> leaguerPreStoreCardMap = leaguer.getLeaguerPreStoreCardMap();
		if(leaguerPreStoreCardMap.containsKey(inputForm.getId())) {
			PreStoreCard preStoreCard = leaguerPreStoreCardMap.get(inputForm.getId());
			LeaguerPrdCardItem item = preStoreCard.takeLeaguerPrdCardItem(inputForm.getItemType(), inputForm.getPgId());
			item.setRestCount(item.getRestCount()+inputForm.getCount());
			updateLeaguerPreStoreCardState(preStoreCard);
			leaguer.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	//减少次数 划卡
	public boolean reducePreStoreCardCount(LeaguerDetail leaguer, PreStoreCardReduceCountForm inputForm) {
		Map<String, PreStoreCard> leaguerPreStoreCardMap = leaguer.getLeaguerPreStoreCardMap();
		
		if(leaguerPreStoreCardMap.containsKey(inputForm.getId())) {
			PreStoreCard preStoreCard = leaguerPreStoreCardMap.get(inputForm.getId());
			if(isLeaguerPreStoreCardValid(preStoreCard)) {
				LeaguerPrdCardItem item = preStoreCard.takeLeaguerPrdCardItem(inputForm.getItemType(), inputForm.getPgId());
				item.setRestCount(item.getRestCount()-inputForm.getCount());
				updateLeaguerPreStoreCardState(preStoreCard);
				leaguer.setLastUpdateTime(System.currentTimeMillis());
				return true;
			}
		}
		return false;
	}
	
	//检查预存卡是否有效
	public boolean checkLeaguerPreStoreCardValid(LeaguerDetail leaguer, String preStoreCardId) {
		PreStoreCard card = leaguer.takePreStoreCardById(preStoreCardId);
		if(card == null) {
			return false;
		}
		return isLeaguerPreStoreCardValid(card);
	}
	
	private boolean isLeaguerPreStoreCardValid(PreStoreCard card) {
		int state = card.getState();
		if(state == LeaguerCardEnum.USING.ordinal() || state == LeaguerCardEnum.NOTUSE.ordinal()) {
			return true;
		}
		return false;
	}
	
	private void updateLeaguerPreStoreCardState(PreStoreCard card) {
		List<LeaguerPrdCardItem> leaguerPrdCardItems = card.getLeaguerPrdCardItems();
		int state = LeaguerCardEnum.USING.ordinal();
		if(checkFinish(leaguerPrdCardItems)) {
			state = LeaguerCardEnum.FINISH.ordinal();
		}else if(checkNotUse(leaguerPrdCardItems)) {
			state = LeaguerCardEnum.NOTUSE.ordinal();
		}
		card.setState(state);
	}
	
	/***************************************客户会员卡操作***************************************/
	//会员卡余额是否比抵扣金额大
	public boolean isBalanceGteCost(LeaguerMemberCard leaguerMemberCard, float memshipCardCost) {
		if (leaguerMemberCard != null && leaguerMemberCard.getBalance() >= memshipCardCost) {
			return true;
		}
		return false;
	}

	//校验客户的会员卡是否有效
	public boolean checkLeaguerMemberCardStatus(LeaguerMemberCard leaguerMemberCard) {
		if (leaguerMemberCard.getState() == LeaguerCardEnum.VALID.ordinal()) {
			return true;
		}
		return false;
	}

	//减少客户余额
	public boolean reduceBalance(LeaguerMemberCard leaguerMemberCard, float memshipCardCost) {
		if (isBalanceGteCost(leaguerMemberCard, memshipCardCost)) {
			leaguerMemberCard.setBalance(leaguerMemberCard.getBalance() - memshipCardCost);
			return true;
		}
		return false;
	}

	//获取会员卡抵扣金额
	public float getMemshipCardCost(List<PayItem> payItems) {
		float memshipCardCost = 0;
		for (PayItem payItem : payItems) {
			if (payItem.getPayType() == PayTypeEnum.MEMBERSHIPCARD.ordinal()) {
				memshipCardCost = BigDecimalUtil.round(payItem.getCost(), 2);
				break;
			}
		}
		return memshipCardCost;
	}

	//设置会员卡
	public boolean updateMemberCard(LeaguerMemberCard leaguerMemberCard, UpdateMemberCardForm updateMemberCardForm) {
		// 这里只是设置会员卡 余额信息需要保留
		leaguerMemberCard.setCardId(updateMemberCardForm.getCardId());
		leaguerMemberCard.setNumber(updateMemberCardForm.getNumber());
		int limitUnit = updateMemberCardForm.getLimitUnit();
		LimitUnitEnum limitUnitEnum = LimitUnitEnum.valueOf(limitUnit);
		int limitTime = updateMemberCardForm.getLimitTime();
		leaguerMemberCard.setLimitTime(limitTime);
		leaguerMemberCard.setLimitUnit(limitUnit);

		leaguerMemberCard.setEndTime(getCardEndTime(limitUnitEnum, limitTime));

		leaguerMemberCard.setState(LeaguerCardEnum.VALID.ordinal());
		long currentTimeMillis = System.currentTimeMillis();
		leaguerMemberCard.setSettingTime(currentTimeMillis);
		return true;
	}
	
	//会员充值
	public boolean rechargeMemberCard(LeaguerMemberCard leaguerMemberCard, RechargeMemberCardForm rechargeMemberCardForm) {
		float balance = leaguerMemberCard.getBalance() + rechargeMemberCardForm.getAmount();
		leaguerMemberCard.setBalance(balance);
		return true;
	}

	/***************************************客户次卡操作***************************************/
	//退卡 与订单付款时间最接近的次卡
	public boolean untieProductCard(Map<String, LeaguerProductCard> leaguerProductCardMap, String prdCardId, long payTime) {
		String leaguerPrdCardId = "";
		long minTime = payTime;
		Collection<LeaguerProductCard> values = leaguerProductCardMap.values();
		for (LeaguerProductCard leaguerProductCard : values) {
			if(leaguerProductCard.getState() == LeaguerCardEnum.BACKCARD.ordinal()) {//跳过已退的卡
				continue;
			}
			if(prdCardId.equals(leaguerProductCard.getCardId())) {
				if(minTime > Math.abs(leaguerProductCard.getCreatedTime() - payTime)) {
					leaguerPrdCardId = leaguerProductCard.getId();
					minTime = leaguerProductCard.getCreatedTime() - payTime;
				}
			}
		}
		if(StringUtils.isNoneBlank(leaguerPrdCardId)) {
			leaguerProductCardMap.get(leaguerPrdCardId).setState(LeaguerCardEnum.BACKCARD.ordinal());
		}
		return true;
	}
	
	//购买次卡
	public boolean purchaseProductCard(LeaguerDetail leaguer, ProductCardDetail productCard) {
		int index = leaguer.getLeaguerPrdCardIndex() + 1;
		LeaguerProductCard leaguerProductCard = buildLeaguerProductCard(productCard, index);
		leaguer.setLeaguerPrdCardIndex(index);
		leaguer.getLeaguerProductCardMap().put(leaguerProductCard.getId(), leaguerProductCard);
		return true;
	}

	//退单 增加可用次数
	public boolean addProductCardCount(Map<String, LeaguerProductCard> leaguerProductCardMap, AddProductCardCountForm addProductCardCountForm) {
		String leaguerProductCardId = addProductCardCountForm.getLeaguerProductCardId();
		LeaguerProductCard leaguerProductCard = leaguerProductCardMap.get(leaguerProductCardId);
		if (leaguerProductCard == null) {
			return false;
		}
		
		int itemType = addProductCardCountForm.getItemType();
		String pgId = addProductCardCountForm.getPgId();
		int count = addProductCardCountForm.getCount();
		
		List<LeaguerPrdCardItem> leaguerPrdCardItems = leaguerProductCard.getLeaguerPrdCardItems();
		Map<String, LeaguerPrdCardItem> map = new HashMap<String, LeaguerPrdCardItem>();
		for (LeaguerPrdCardItem item : leaguerPrdCardItems) {
			String key = AppUtils.joinByUnderline(item.getItemType(), item.getPgId());
			map.put(key, item);
		}
		
		LeaguerPrdCardItem leaguerPrdCardItem = map.get(AppUtils.joinByUnderline(itemType, pgId));
		if(leaguerPrdCardItem != null) {
			leaguerPrdCardItem.setRestCount(count + leaguerPrdCardItem.getRestCount());
		}
		updateLeaguerProductCardState(leaguerProductCard);
		return true;
	}
	
	public void updateLeaguerProductCardState(LeaguerProductCard leaguerProductCard) {
		long currentTimeMillis = System.currentTimeMillis();
		if((leaguerProductCard.getEndTime() != 0)  && (currentTimeMillis >= leaguerProductCard.getEndTime())) {
			leaguerProductCard.setState(LeaguerCardEnum.INVALID.ordinal());
			return;
		}
		
		List<LeaguerPrdCardItem> leaguerPrdCardItems = leaguerProductCard.getLeaguerPrdCardItems();
		int state = LeaguerCardEnum.USING.ordinal();
		if(checkFinish(leaguerPrdCardItems)) {
			state = LeaguerCardEnum.FINISH.ordinal();
		}else if(checkNotUse(leaguerPrdCardItems)) {
			state = LeaguerCardEnum.NOTUSE.ordinal();
		}
		leaguerProductCard.setState(state);
	}
	
	private boolean checkFinish(List<LeaguerPrdCardItem> leaguerPrdCardItems) {
		for (LeaguerPrdCardItem leaguerPrdCardItem : leaguerPrdCardItems) {
			if(leaguerPrdCardItem.getRestCount() != 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkNotUse(List<LeaguerPrdCardItem> leaguerPrdCardItems) {
		for (LeaguerPrdCardItem leaguerPrdCardItem : leaguerPrdCardItems) {
			if(leaguerPrdCardItem.getRestCount() != leaguerPrdCardItem.getCount()) {
				return false;
			}
		}
		return true;
	}
	
	//划卡 减少次卡对应项目可用次数
	public boolean reduceProductCardCount(Map<String, LeaguerProductCard> leaguerProductCardMap, ReduceProductCardCountForm reduceProductCardCountForm) {
		String leaguerProductCardId = reduceProductCardCountForm.getLeaguerProductCardId();
		LeaguerProductCard leaguerProductCard = leaguerProductCardMap.get(leaguerProductCardId);
		if (leaguerProductCard == null || leaguerProductCard.getState() == LeaguerCardEnum.INVALID.ordinal()) {
			return false;
		}
		
		int itemType = reduceProductCardCountForm.getItemType();
		String pgId = reduceProductCardCountForm.getPgId();
		int reduceCount = reduceProductCardCountForm.getCount();
		
		List<LeaguerPrdCardItem> leaguerPrdCardItems = leaguerProductCard.getLeaguerPrdCardItems();
		Map<String, LeaguerPrdCardItem> map = new HashMap<String, LeaguerPrdCardItem>();
		String tmpSuffix = "prd";
		for (LeaguerPrdCardItem item : leaguerPrdCardItems) {
			String key = AppUtils.joinByUnderline(tmpSuffix, item.getItemType(), item.getPgId());
			map.put(key, item);
		}
		
		//精确匹配  优先级最高
		LeaguerPrdCardItem leaguerPrdCardItem = map.get(AppUtils.joinByUnderline(tmpSuffix, itemType, pgId));
		if(leaguerPrdCardItem != null) {
			int count = leaguerPrdCardItem.getRestCount();
			if(count == ServerConstants.FOREVER_INT_VALUE) {//无限次
				updateLeaguerProductCardState(leaguerProductCard);
				return true;
			}else if(count >= reduceCount) {
				leaguerPrdCardItem.setRestCount(leaguerPrdCardItem.getRestCount() - reduceCount);
				updateLeaguerProductCardState(leaguerProductCard);
				return true;
			}else {//精确匹配 次数不够时， 能销多少是多少  剩余的由其他类型项目补充
				reduceCount = reduceCount - count;
				leaguerPrdCardItem.setRestCount(0);
			}
		}
		
		//泛型匹配  [不限项目的] 在精确匹配不上时，由泛型匹配补充
		leaguerPrdCardItem = map.get(AppUtils.joinByUnderline(tmpSuffix, itemType, ServerConstants.FOREVER_INT_VALUE));
		if(leaguerPrdCardItem != null) {//不限项目
			int count = leaguerPrdCardItem.getRestCount();
			if(count == ServerConstants.FOREVER_INT_VALUE) {//无限次
				updateLeaguerProductCardState(leaguerProductCard);
				return true;
			}else if(count >= reduceCount){
				updateLeaguerProductCardState(leaguerProductCard);
				leaguerPrdCardItem.setRestCount(count - reduceCount);
				return true;
			}
		}
		
		return false;
	}

	// 根据店铺次卡 构建客户的次卡信息
	public LeaguerProductCard buildLeaguerProductCard(ProductCardDetail productCard, int leaguerPrdCardIndex) {
		LeaguerProductCard leaguerProductCard = LeaguerProductCard.newInstanceByIndex(leaguerPrdCardIndex);
		leaguerProductCard.setCardId(getLocalId(productCard.getId()));

		LimitUnitEnum limitUnitEnum = LimitUnitEnum.valueOf(productCard.getValidPeriodUnit());
		leaguerProductCard.setEndTime(getCardEndTime(limitUnitEnum, productCard.getValidPeriod()));

		List<LeaguerPrdCardItem> leaguerPrdCardItems = new ArrayList<LeaguerPrdCardItem>();
		List<ProductCardItem> productCardItems = productCard.getProductCardItems();
		for (ProductCardItem productCardItem : productCardItems) {
			leaguerPrdCardItems.add(LeaguerPrdCardItem.newInstance(productCardItem));
		}		
		
		leaguerProductCard.setLeaguerPrdCardItems(leaguerPrdCardItems);
		leaguerProductCard.setState(LeaguerCardEnum.NOTUSE.ordinal());
		return leaguerProductCard;
	}
	
	private String getLocalId(String id) {
		if(id.contains(ServerConstants.CHAIN_ID_SUFFFIX)) {
			return ServerConstants.CHAIN_ID_SUFFFIX+StringUtils.substringAfter(id, ServerConstants.CHAIN_ID_SUFFFIX);
		}
		return id;
	}

	// 根据日期单位和数值 计算有效时间
	public long getCardEndTime(LimitUnitEnum limitUnitEnum, int validPeriod) {
		long result = 0;
		Date currentDate = new Date();
		switch (limitUnitEnum) {
		case DAY:
			result = DateUtils.addDays(currentDate, validPeriod).getTime();
			break;
		case MONTH:
			result = DateUtils.addMonths(currentDate, validPeriod).getTime();
			break;
		case YEAR:
			result = DateUtils.addYears(currentDate, validPeriod).getTime();
			break;
		default:
			break;
		}
		return result;
	}
}
