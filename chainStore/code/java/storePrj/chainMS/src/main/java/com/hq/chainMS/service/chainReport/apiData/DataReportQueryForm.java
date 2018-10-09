package com.hq.chainMS.service.chainReport.apiData;


import com.hq.chainMS.common.util.AppUtils;

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

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
	}


}
