package com.hq.customerRestClient.service.orderDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class UserBonusDetail {
	// 员工ID
	private long buserId;
	// 员工名称
	private String buserName;
	// 业绩金额
	private float amount;
	// 提成类型 BonusTypeEnum 固定提成 比例提成
	private int bonusType;
	// 提成比例
	private float percentage;
	// 提成金额
	private float cost;

	public static UserBonusDetail newInstance() {
		UserBonusDetail data = new UserBonusDetail();
		return data;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getBonusType() {
		return bonusType;
	}

	public void setBonusType(int bonusType) {
		this.bonusType = bonusType;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

}
