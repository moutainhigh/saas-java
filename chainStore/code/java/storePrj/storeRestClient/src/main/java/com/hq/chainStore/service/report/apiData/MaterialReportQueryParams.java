package com.hq.chainStore.service.report.apiData;

import com.zenmind.dao.rest.ReqMap;

public class MaterialReportQueryParams {
	private long buserId;
	private long storeId;

	private long maxTime;
	private long minTime;

	private int pageItemCount;
	private int pageNo;

	public static MaterialReportQueryParams newInstance() {
		MaterialReportQueryParams data = new MaterialReportQueryParams();
		return data;
	}
	
	public ReqMap toReqMap(){
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", this.storeId).add("buserId", this.buserId)
			.add("maxTime", this.maxTime).add("minTime", this.minTime);
		return reqMap;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
