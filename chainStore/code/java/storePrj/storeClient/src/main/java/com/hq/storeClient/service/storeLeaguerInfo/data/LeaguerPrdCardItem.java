package com.hq.storeClient.service.storeLeaguerInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerPrdCardItem {
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目/商品/套餐 简版信息ID -1为不限项目
	private String pgId;
	// 次卡初始数量 -1为无限次
	private int count;
	// 剩余次数 -1为无限次  初始值和count一致
	private int restCount;

	public static LeaguerPrdCardItem newInstance() {
		LeaguerPrdCardItem data = new LeaguerPrdCardItem();
		return data;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}

}
