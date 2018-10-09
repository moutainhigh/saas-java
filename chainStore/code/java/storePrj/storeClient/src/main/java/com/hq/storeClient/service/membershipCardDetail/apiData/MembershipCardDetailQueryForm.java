package com.hq.storeClient.service.membershipCardDetail.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class MembershipCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 卡状态
	private Set<Integer> status = new HashSet<Integer>();
	// 卡名称或者编号
	private String cardNameOrNumber;
	private int pageItemCount;
	private int pageNo;

	public static MembershipCardDetailQueryForm newInstance() {
		return new MembershipCardDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId)
				.add("status", StringUtils4Client.join(status, ",")).add("cardNameOrNumber", cardNameOrNumber);
		return reqMap;
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

	public Set<Integer> getStatus() {
		return status;
	}

	public MembershipCardDetailQueryForm setStatus(Set<Integer> status) {
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
}
