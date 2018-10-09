package com.hq.storeClient.service.dataReport.apiData;

import com.zenmind.dao.rest.ReqMap;

public class DataReportQueryForm {
	private long storeId;

	private long maxTime;
	private long minTime;

	public static DataReportQueryForm newInstance() {
		DataReportQueryForm data = new DataReportQueryForm();
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = new ReqMap();
		reqMap.add("storeId", storeId).add("minTime", minTime).add("maxTime", maxTime);
		return reqMap;
	}

	public long getStoreId() {
		return storeId;
	}

	public DataReportQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public DataReportQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public DataReportQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}
}
