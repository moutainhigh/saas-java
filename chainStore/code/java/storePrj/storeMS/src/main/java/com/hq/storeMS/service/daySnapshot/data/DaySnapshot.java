package com.hq.storeMS.service.daySnapshot.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "daySnapshot")
public class DaySnapshot {
	@Id
	private long id;
	@IndexField
	private long storeId;
	// 开始时间
	@IndexField
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

	private long createTime;
	private long lastUpdateTime;
	private long ver;

	public static DaySnapshot newInstance() {
		DaySnapshot data = new DaySnapshot();
		long curTime = System.currentTimeMillis();
		data.createTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

}
