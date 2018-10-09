package com.hq.chainClient.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class MembershipCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long chainId;

	// 卡状态 如果需要查询多个，请用,号分割 对应 CardStatusEnum
	private Set<Integer> statusSet = new HashSet<Integer>();
	// 卡名称或者编号
	private String cardNameOrNumber;

	private int pageItemCount;
	private int pageNo;

	public static MembershipCardDetailQueryForm newInstance() {
		return new MembershipCardDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("chainId", chainId)
				.add("statusSet", StringUtils4Client.join(statusSet, ",")).add("cardNameOrNumber", cardNameOrNumber);
		return reqMap;
	}

	public void addStatus(int statusP) {
		statusSet.add(statusP);
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

	public long getChainId() {
		return chainId;
	}

	public MembershipCardDetailQueryForm setChainId(long chainId) {
		this.chainId = chainId;
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

	public String getCardNameOrNumber() {
		return cardNameOrNumber;
	}

	public MembershipCardDetailQueryForm setCardNameOrNumber(String cardNameOrNumber) {
		this.cardNameOrNumber = cardNameOrNumber;
		return this;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public MembershipCardDetailQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
		return this;
	}

}
