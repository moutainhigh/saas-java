package com.hq.storeClient.service.leaguerDetail.apiData;

import com.zenmind.dao.rest.ReqMap;

public class LeaguerDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 手机或者名称
	private String leaguerNameOrPhone;
	// 客户类型 对应leaguerTypeEnum
	private int leaguerType;
	// 排序类型 对应sortTypeEnum
	private int sortType;
	// 升/降序 对应sortEnum
	private int sort;
	private long buserId;
	
	//会员卡到期状态 对应ExpiredStateEnum
	private int memberCardExpiredState;

	private int pageItemCount;
	private int pageNo;

	public static LeaguerDetailQueryForm newInstance() {
		return new LeaguerDetailQueryForm();
	}
	
	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("buserId", buserId)
				.add("leaguerNameOrPhone", leaguerNameOrPhone).add("leaguerType", leaguerType).add("sortType", sortType)
				.add("sort", sort);
		return reqMap;
	}

	public long getMinTime() {
		return minTime;
	}

	public LeaguerDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public LeaguerDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public LeaguerDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public LeaguerDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public LeaguerDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getLeaguerNameOrPhone() {
		return leaguerNameOrPhone;
	}

	public LeaguerDetailQueryForm setLeaguerNameOrPhone(String leaguerNameOrPhone) {
		this.leaguerNameOrPhone = leaguerNameOrPhone;
		return this;
	}

	public int getLeaguerType() {
		return leaguerType;
	}

	public LeaguerDetailQueryForm setLeaguerType(int leaguerType) {
		this.leaguerType = leaguerType;
		return this;
	}

	public int getSortType() {
		return sortType;
	}

	public LeaguerDetailQueryForm setSortType(int sortType) {
		this.sortType = sortType;
		return this;
	}

	public int getSort() {
		return sort;
	}

	public LeaguerDetailQueryForm setSort(int sort) {
		this.sort = sort;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public LeaguerDetailQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public int getMemberCardExpiredState() {
		return memberCardExpiredState;
	}

	public LeaguerDetailQueryForm setMemberCardExpiredState(int memberCardExpiredState) {
		this.memberCardExpiredState = memberCardExpiredState;
		return this;
	}
}
