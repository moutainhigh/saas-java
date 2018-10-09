package com.hq.chainStore.service.buser.apiData;

public class BUserVipRechargeData {
	// 用户ID
	private long buserId;
	// 会员类型
	private int vipType;
	// 充值金额
	private long amount;
	// 到期时间
	private long expiredTime;

	public static BUserVipRechargeData newInstance() {
		return new BUserVipRechargeData();
	}

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
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
