package com.hq.chainStore.service.workFlowData.apiData;

import java.util.HashSet;
import java.util.Set;

public class DecreasePrdCardAddForm {
	// 店铺次卡ID 新购状态的 没有客户次卡ID
	private String cardTypeId;
	// 项目ID
	private long prdId;
	// 次卡状态 DecreasePrdCardRecordStatusEnum 新购/已购
	private int status;
	// 抵消次数
	private int count;
	// 折扣
	private float discount;
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();

	public static DecreasePrdCardAddForm newInstance() {
		return new DecreasePrdCardAddForm();
	}

	public String getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public long getPrdId() {
		return prdId;
	}

	public void setPrdId(long prdId) {
		this.prdId = prdId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
