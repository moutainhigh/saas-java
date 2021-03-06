package com.hq.orderMS.service.order.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.orderMS.service.common.SplitMarkEnum;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "order")
public class Order {
	@Id
	private long id;
	// 订单编号
	private String number;
	// 订单类型 OrderTypeEnum
	private int orderType;
	// 客户ID
	private String leaguerId;
	// cuserID
	private long cuserId;
	// 客户会员卡ID
	private String memberCardId;
	// 应结金额
	private float cost;
	// 实付金额
	private float realPay;
	// 订单的状态 OrderStatusEnum
	private int status;
	// 实体状态
	private int entityState;
	// 创建时间
	@IndexField
	private long createdTime;
	// 付款时间
	@IndexField
	private long payTime;
	// 已退金额
	private float chargeBackCost;

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
	// 支付明细
	private List<PayItem> payItems = new ArrayList<PayItem>();

	// 客户名称
	private String name;
	// 店铺ID
	@IndexField
	private long storeId;
	// 创建者ID
	private long creatorId;
	// 流程id
	private long workFlowDataId;
	// 订单类型 OrderDataTypeEnum 补单、开单
	private int recordType;
	// 订单来源 OrderOriginEnum
	private int origin;
	
	// 推广信息Id
	private long dynamicId;

	// 数据拆分标识 SplitMarkEnum
	@IndexField(groupName = "splitMark")
	private int splitMark;
	private long lastUpdateTime;
	private int ver;

	public static Order newInstance() {
		Order order = new Order();
		long curTime = System.currentTimeMillis();
		order.createdTime = curTime;
		order.lastUpdateTime = curTime;
		order.splitMark = SplitMarkEnum.FINISH.ordinal();
		return order;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
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

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
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

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public float getRealPay() {
		return realPay;
	}

	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
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

	public float getChargeBackCost() {
		return chargeBackCost;
	}

	public void setChargeBackCost(float chargeBackCost) {
		this.chargeBackCost = chargeBackCost;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
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

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
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
