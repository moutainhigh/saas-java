package com.hq.storeClient.service.daySnapshot.apiData;

public class DaySnapshotAddForm {
	private long storeId;
	// 开始时间
	private long startTime;
	// 结束时间
	private long endTime;
	// 用户ID
	private long buserId;
	// 用户名称
	private String buserName;
	
	// 营业金额
	private float amount;
	// 会员卡抵扣金额
	private float memberCardCost;
	// 支出金额
	private float payCost;
	// 收入金额
	private float incomeCost;
	// 订单数
	private int orderNum;
	// 未支付的订单数
	private int notPayNum;
	// 现金总额
	private float cashCost;
	// 退单金额
	private float chargeBackCost;
	// 欠款入账
	private float arrearBackCost;
	
	//备注
	private String remark;

	public static DaySnapshotAddForm newInstance() {
		DaySnapshotAddForm data = new DaySnapshotAddForm();
		return data;
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getMemberCardCost() {
		return memberCardCost;
	}

	public void setMemberCardCost(float memberCardCost) {
		this.memberCardCost = memberCardCost;
	}

	public float getPayCost() {
		return payCost;
	}

	public void setPayCost(float payCost) {
		this.payCost = payCost;
	}

	public float getIncomeCost() {
		return incomeCost;
	}

	public void setIncomeCost(float incomeCost) {
		this.incomeCost = incomeCost;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getNotPayNum() {
		return notPayNum;
	}

	public void setNotPayNum(int notPayNum) {
		this.notPayNum = notPayNum;
	}

	public float getCashCost() {
		return cashCost;
	}

	public void setCashCost(float cashCost) {
		this.cashCost = cashCost;
	}

	public float getChargeBackCost() {
		return chargeBackCost;
	}

	public void setChargeBackCost(float chargeBackCost) {
		this.chargeBackCost = chargeBackCost;
	}

	public float getArrearBackCost() {
		return arrearBackCost;
	}

	public void setArrearBackCost(float arrearBackCost) {
		this.arrearBackCost = arrearBackCost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
