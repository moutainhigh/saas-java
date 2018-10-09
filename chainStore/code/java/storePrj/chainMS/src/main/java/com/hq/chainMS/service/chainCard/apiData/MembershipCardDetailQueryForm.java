package com.hq.chainMS.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainMS.common.util.AppUtils;

public class MembershipCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long chainId;

	// 卡状态
	private Set<Integer> statusSet = new HashSet<Integer>();
	// 卡名称或者编号
	private String cardNameOrNumber;
	
	private int pageItemCount;
	private int pageNo;

	public static MembershipCardDetailQueryForm newInstance() {
		return new MembershipCardDetailQueryForm();
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, chainId);
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
