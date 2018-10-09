package com.hq.storeMS.service.arrearage.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ArrearageGroup {
	private String leaguerId;
	// 客户名称
	private String leaguerName;
	// 客户手机号
	private long leaguerPhone;
	// 当前欠款总额
	private float totalAmount;

	public static ArrearageGroup newInstance() {
		return new ArrearageGroup();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public void setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
	}

	public long getLeaguerPhone() {
		return leaguerPhone;
	}

	public void setLeaguerPhone(long leaguerPhone) {
		this.leaguerPhone = leaguerPhone;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

}
