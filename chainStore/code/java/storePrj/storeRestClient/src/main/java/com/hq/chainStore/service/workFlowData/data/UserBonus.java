package com.hq.chainStore.service.workFlowData.data;


public class UserBonus {
	// 医美师ID
	private long buserId;
	// 业绩金额
	private float amount;
	// 提成类型 BonusTypeEnum 固定提成 比例提成
	private int bonusType;
	// 提成比例
	private float percentage;
	// 提成金额
	private float cost;

	public static UserBonus newInstance() {
		return new UserBonus();
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

}
