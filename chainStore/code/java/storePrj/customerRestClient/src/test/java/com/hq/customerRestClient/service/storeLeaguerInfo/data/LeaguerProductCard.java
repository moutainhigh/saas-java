package com.hq.customerRestClient.service.storeLeaguerInfo.data;

import java.util.HashMap;
import java.util.Map;

public class LeaguerProductCard {
	
	private String id;//客户次卡的ID
	
	private String cardId;//次卡类型id 对应ProductCard id
	
	private long purchaseTime;//次卡购买时间
	
	private long endTime;//到期时间
	
	private int state;//次卡状态 对应LeaguerCardEnum
	
	//限项目限次数和限项目不限次数(永久次数为-1) 项目id对应项目剩余次数
	private Map<Long, Long> useCountMap = new HashMap<Long, Long>();
	
	private long count;//不限项目限次数 对应次数
	
	private long createdTime;//创建时间

	public static LeaguerProductCard newInstance(){
		LeaguerProductCard prdCard = new LeaguerProductCard();
		
		long curTime = System.currentTimeMillis();
		prdCard.createdTime = curTime;
		return prdCard;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
