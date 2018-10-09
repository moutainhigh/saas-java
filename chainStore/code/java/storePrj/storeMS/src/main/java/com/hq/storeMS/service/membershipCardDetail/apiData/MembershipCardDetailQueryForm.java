package com.hq.storeMS.service.membershipCardDetail.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class MembershipCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

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
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
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

	public MembershipCardDetailQueryForm setStatus(String status) {
		if (StringUtils.isNoneBlank(status)) {
			String[] ss = status.split(",");
			for (String s : ss) {
				statusSet.add(Integer.valueOf(s));
			}
		}
		return this;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public MembershipCardDetailQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
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
