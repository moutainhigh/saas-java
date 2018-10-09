package com.hq.chainStore.service.membershipCardDetail.apiData;

import com.zenmind.dao.rest.ReqMap;

public class MembershipCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 状态，如果需要查询多个，请用,号分割
	private String status;
	// 卡名称或者编号
	private String cardNameOrNumber;

	private int pageItemCount;
	private int pageNo;

	public static MembershipCardDetailQueryForm newInstance() {
		return new MembershipCardDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("status", status)
				.add("cardNameOrNumber", cardNameOrNumber);
		return reqMap;
	}

	public void addStatus(int statusP) {
		if (this.status == null) {
			this.status = statusP + "";
		} else {
			this.status = this.status + "," + statusP;
		}
	}

	public long getMinTime() {
		return minTime;
	}

	public MembershipCardDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public MembershipCardDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public MembershipCardDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public MembershipCardDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public MembershipCardDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public MembershipCardDetailQueryForm setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getCardNameOrNumber() {
		return cardNameOrNumber;
	}

	public MembershipCardDetailQueryForm setCardNameOrNumber(String cardNameOrNumber) {
		this.cardNameOrNumber = cardNameOrNumber;
		return this;
	}
}
