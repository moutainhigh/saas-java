package com.hq.customerRestClient.service.orderDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SimpleOrderInfo {
	private long orderId;
	// 订单编号
	private String number;
	// 订单类型 OrderTypeEnum
	private int orderType;
	// 客户ID
	private String leaguerId;
	// 应结金额
	private float cost;
	// 实付金额
	private float realPay;
	// 订单的状态 OrderStatusEnum
	private int status;
	// 实体状态
	private int entityState;
	// 创建时间
	private long createdTime;
	// 付款时间
	private long payTime;
	// 已退金额
	private float chargeBackCost;
	// 客户名称
	private String name;
	// 店铺ID
	private long storeId;
	// 创建者ID
	private long creatorId;
	// 订单来源 OrderOriginEnum
	private int origin;

	// 跟进人员
	private String buserName;

	public static SimpleOrderInfo newInstance() {
		SimpleOrderInfo data = new SimpleOrderInfo();
		return data;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public float getRealPay() {
		return realPay;
	}

	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

	
	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public float getChargeBackCost() {
		return chargeBackCost;
	}

	public void setChargeBackCost(float chargeBackCost) {
		this.chargeBackCost = chargeBackCost;
	}

}
