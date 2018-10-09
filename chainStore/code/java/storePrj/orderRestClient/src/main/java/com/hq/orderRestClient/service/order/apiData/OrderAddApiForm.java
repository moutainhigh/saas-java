package com.hq.orderRestClient.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.PreStoreCardItem;
import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class OrderAddApiForm {
	// 订单类型 OrderTypeEnum
	private int orderType;
	// 客户ID
	private String leaguerId;
	// 客户会员卡ID
	private String memberCardId;
	// 客户名称
	private String name;
	// 应结金额
	private float cost;

	// 会员充值清单
	private List<RechargeItem> rechargeItems = new ArrayList<RechargeItem>();
	// 购买消费清单
	private List<BuyItem> buyItems = new ArrayList<BuyItem>();
	// 划卡消费清单
	private List<DelimitCardItem> delimitCardItems = new ArrayList<DelimitCardItem>();
	// 预存卡消费清单
	private List<PreStoreCardItem> preStoreCardItems = new ArrayList<PreStoreCardItem>();
	// 赠送消费清单
	private List<DonationItem> donationItems = new ArrayList<DonationItem>();

	// 店铺ID
	private long storeId;
	// 创建者ID
	private long creatorId;
	// 流程id
	private long workFlowDataId;
	// 开单类型 OrderDataTypeEnum 补单、开单
	private int recordType;
	// 补单时间
	private long orderTime;
	// 订单来源 OrderOriginEnum
	private int origin;

	// 推广信息Id
	private long dynamicId;

	public static OrderAddApiForm newInstance() {
		return new OrderAddApiForm();
	}

	public Order toOrder() {
		Order data = Order.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public List<RechargeItem> getRechargeItems() {
		return rechargeItems;
	}

	public void setRechargeItems(List<RechargeItem> rechargeItems) {
		this.rechargeItems = rechargeItems;
	}

	public List<BuyItem> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(List<BuyItem> buyItems) {
		this.buyItems = buyItems;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public List<DelimitCardItem> getDelimitCardItems() {
		return delimitCardItems;
	}

	public void setDelimitCardItems(List<DelimitCardItem> delimitCardItems) {
		this.delimitCardItems = delimitCardItems;
	}

	public List<DonationItem> getDonationItems() {
		return donationItems;
	}

	public void setDonationItems(List<DonationItem> donationItems) {
		this.donationItems = donationItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}

	public List<PreStoreCardItem> getPreStoreCardItems() {
		return preStoreCardItems;
	}

	public void setPreStoreCardItems(List<PreStoreCardItem> preStoreCardItems) {
		this.preStoreCardItems = preStoreCardItems;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(long dynamicId) {
		this.dynamicId = dynamicId;
	}
}
