package com.hq.chainStore.service.bonus.data;


public class MonthBonus {
	//yyyy-MM
	private String date;
	//业绩
	private float amount;
	//提成
	private float cost;

	public static MonthBonus newInstance() {
		MonthBonus data = new MonthBonus();
		return data;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
}
