package com.hq.storeClient.service.dataReport.data.vo;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 日销售额
 */
@SynClass
public class SellDate {
	// 日期
	private String date;
	// 销售额
	private float cost;

	public static SellDate newInstance() {
		SellDate data = new SellDate();
		return data;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}
