package com.hq.chainStore.service.workFlowData.apiData;

import java.util.HashSet;
import java.util.Set;

public class DecreasePrdCardUpdateForm {
	private String decreasePrdCardId;
	// 抵消次数
	private int count;
	// 折扣
	private float discount;
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();
	
	public static DecreasePrdCardUpdateForm newInstance() {
		return new DecreasePrdCardUpdateForm();
	}

	public String getDecreasePrdCardId() {
		return decreasePrdCardId;
	}

	public void setDecreasePrdCardId(String decreasePrdCardId) {
		this.decreasePrdCardId = decreasePrdCardId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}
}
