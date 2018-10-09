package com.hq.storeClient.service.buser.apiData;

public class BUserVipRechargeData {
	// 用户ID
	private long buserId;
	// 会员类型
	private long vipType;
	// 到期时间
	private long expiredTime;

	/*****************************遗留字段*****************************/
	// 充值金额
	private float amount;

	public static BUserVipRechargeData newInstance() {
		return new BUserVipRechargeData();
	}

	public long getVipType() {
		return vipType;
	}

	public void setVipType(long vipType) {
		this.vipType = vipType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

}
