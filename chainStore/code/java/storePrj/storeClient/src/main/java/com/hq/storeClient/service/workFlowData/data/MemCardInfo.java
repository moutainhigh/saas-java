package com.hq.storeClient.service.workFlowData.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MemCardInfo {
	// 店铺会员卡类型ID
	private String memTypeId;
	// 充值金额
	private float cost;
	// 赠送金额
	private float largess;
	// 会员卡号
	private String number;
	// 有效期 eg：365天/1年
	private int limitTime;
	// 有效期时间的单位 对应LimitUnitEnum
	private int limitUnit;

	public static MemCardInfo newInstance() {
		return new MemCardInfo();
	}

	public String getMemTypeId() {
		return memTypeId;
	}

	public void setMemTypeId(String memTypeId) {
		this.memTypeId = memTypeId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getLargess() {
		return largess;
	}

	public void setLargess(float largess) {
		this.largess = largess;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
