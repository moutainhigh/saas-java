package com.hq.storeMS.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 客户订单总数统计实体
 */
@SynClass
public class MemberDataCount {
	//店铺ID
	private long storeId;
	//店铺客户总数
	private long memberCount;
	//店铺订单总数
	private long orderCount;
	//店铺订单总额
	private float orderCost;

	public static MemberDataCount newInstance() {
		MemberDataCount data = new MemberDataCount();
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(long memberCount) {
		this.memberCount = memberCount;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

	public float getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(float orderCost) {
		this.orderCost = orderCost;
	}

}
