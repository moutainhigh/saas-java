package com.hq.storeMS.service.workFlowData.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeMS.service.workFlowData.data.MemCardInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class MemCardInfoForm {
	// 店铺会员卡类型ID
	private String memTypeId;
	// 充值金额
	private float cost;
	// 赠送金额
	private float largess;
	// 会员卡号
	private String number;
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();
	// 有效期 eg：365天/1年
	private int limitTime;
	// 有效期时间的单位 对应LimitUnitEnum
	private int limitUnit;

	public static MemCardInfoForm newInstance() {
		return new MemCardInfoForm();
	}
	
	public MemCardInfo toMemCardInfo() {
		MemCardInfo data = MemCardInfo.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
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

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
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
