package com.hq.orderMS.service.order.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 充值清单
 */
@SynClass
public class RechargeItem {
	// 会员卡类型ID
	private String membershipCardId;
	// 会员卡类型名称
	private String membershipCardName;
	// 支付金额
	private float pay;
	// 赠送金额
	private float largess;
	// 实充金额
	private float amount;
	// 会员卡号
	private String number;
	//有效期  eg：365天/1年
	private int limitTime;
	//有效期时间的单位 对应LimitUnitEnum
	private int limitUnit;

	public static RechargeItem newInstance() {
		RechargeItem data = new RechargeItem();
		return data;
	}

	public String getMembershipCardId() {
		return membershipCardId;
	}

	public void setMembershipCardId(String membershipCardId) {
		this.membershipCardId = membershipCardId;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public float getLargess() {
		return largess;
	}

	public void setLargess(float largess) {
		this.largess = largess;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMembershipCardName() {
		return membershipCardName;
	}

	public void setMembershipCardName(String membershipCardName) {
		this.membershipCardName = membershipCardName;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public int getLimitUnit() {
		return limitUnit;
	}

	public void setLimitUnit(int limitUnit) {
		this.limitUnit = limitUnit;
	}
}
