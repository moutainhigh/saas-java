package com.hq.storeMS.service.daySnapshot.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PreDaySnapshotData {
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

	public static PreDaySnapshotData newInstance() {
		PreDaySnapshotData data = new PreDaySnapshotData();
		return data;
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

}
