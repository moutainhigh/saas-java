package com.hq.storeClient.service.storeLeaguerInfo.data;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
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

	public static LeaguerProductCard newInstance() {
		LeaguerProductCard prdCard = new LeaguerProductCard();
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

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public List<LeaguerPrdCardItem> getLeaguerPrdCardItems() {
		return leaguerPrdCardItems;
	}

	public void setLeaguerPrdCardItems(List<LeaguerPrdCardItem> leaguerPrdCardItems) {
		this.leaguerPrdCardItems = leaguerPrdCardItems;
	}

}
