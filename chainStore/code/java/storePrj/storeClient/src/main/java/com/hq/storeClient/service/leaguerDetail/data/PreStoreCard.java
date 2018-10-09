package com.hq.storeClient.service.leaguerDetail.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.storeLeaguerInfo.data.LeaguerPrdCardItem;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PreStoreCard {
	// 客户次卡的ID {leaguerId}_{orderId}
	private String id;
	// 订单ID
	private long orderId;
	// 次卡状态 对应 LeaguerCardEnum
	private int state;
	// 创建时间
	private long createdTime;
	// 次卡内容
	private List<LeaguerPrdCardItem> leaguerPrdCardItems = new ArrayList<LeaguerPrdCardItem>();

	public static PreStoreCard newInstance() {
		PreStoreCard data = new PreStoreCard();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}
