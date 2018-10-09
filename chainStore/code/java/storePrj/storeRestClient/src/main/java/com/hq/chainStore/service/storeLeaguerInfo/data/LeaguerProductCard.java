package com.hq.chainStore.service.storeLeaguerInfo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.productCardDetail.data.ProductCardItem;

public class LeaguerProductCard {
	// 客户次卡的ID
	private String id;
	// 次卡类型id 对应ProductCard id
	private String cardId;
	// 次卡购买时间
	private long purchaseTime;
	// 到期时间
	private long endTime;
	// 次卡状态 对应 LeaguerCardEnum
	private int state;
	// 创建时间
	private long createdTime;

	// 次卡内容
	private List<LeaguerPrdCardItem> leaguerPrdCardItems = new ArrayList<LeaguerPrdCardItem>();

	/******************** 遗留字段 *********************************/
	// 次卡内容
	private List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();
	// 限项目限次数和限项目不限次数(永久次数为-1) 项目id对应项目剩余次数
	private Map<Long, Long> useCountMap = new HashMap<Long, Long>();
	// 不限项目限次数 对应次数
	private long count;

	public static LeaguerProductCard newInstance() {
		LeaguerProductCard prdCard = new LeaguerProductCard();

		long curTime = System.currentTimeMillis();
		prdCard.createdTime = curTime;
		return prdCard;
	}
	
	public boolean isValid() {
		if(state == LeaguerCardEnum.NOTUSE.ordinal() || state == LeaguerCardEnum.USING.ordinal()) {
			return true;
		}
		return false;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public long getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(long purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Map<Long, Long> getUseCountMap() {
		return useCountMap;
	}

	public void setUseCountMap(Map<Long, Long> useCountMap) {
		this.useCountMap = useCountMap;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProductCardItem> getProductCardItems() {
		return productCardItems;
	}

	public void setProductCardItems(List<ProductCardItem> productCardItems) {
		this.productCardItems = productCardItems;
	}

	public List<LeaguerPrdCardItem> getLeaguerPrdCardItems() {
		return leaguerPrdCardItems;
	}

	public void setLeaguerPrdCardItems(List<LeaguerPrdCardItem> leaguerPrdCardItems) {
		this.leaguerPrdCardItems = leaguerPrdCardItems;
	}

}
