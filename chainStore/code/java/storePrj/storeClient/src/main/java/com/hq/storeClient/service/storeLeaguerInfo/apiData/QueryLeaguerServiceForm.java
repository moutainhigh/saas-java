package com.hq.storeClient.service.storeLeaguerInfo.apiData;

public class QueryLeaguerServiceForm {
	private String leaguerId;
	private long minTime;
	private long maxTime;
	private int pageItemCount;
	private int pageNo;

	public static QueryLeaguerServiceForm newInstance() {
		QueryLeaguerServiceForm params = new QueryLeaguerServiceForm();
		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public QueryLeaguerServiceForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public QueryLeaguerServiceForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public QueryLeaguerServiceForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryLeaguerServiceForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryLeaguerServiceForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

}
