package com.hq.chainStore.service.dataReport.apiData;

import com.zenmind.dao.rest.ReqMap;

public class DataReportQueryForm {
	private long storeId;

	private long maxTime;
	private long minTime;

	public static DataReportQueryForm newInstance() {
		DataReportQueryForm data = new DataReportQueryForm();
		data.storeId = 0L;
		data.maxTime = 0L;
		data.maxTime = 0L;
		return data;
	}
	
	public ReqMap toReqMap() {
		ReqMap reqMap=ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("maxTime", maxTime).add("minTime", minTime);
		return reqMap;
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

}
