package com.hq.chainStore.service.appVersion.apiData;

import com.zenmind.common.StringFormatUtil;

public class QueryAppVersionForm {
	private long storeId;
	private long maxTime;
	private long minTime;
	private String cardId;
	private int status;

	private int pageItemCount;
	private int pageNo;

	public static QueryAppVersionForm newInstance() {
		QueryAppVersionForm data = new QueryAppVersionForm();
		data.storeId = 0L;
		data.maxTime = 0L;
		data.minTime = 0L;
		data.cardId = "";
		data.status = 0;
		data.pageItemCount = 0;
		data.pageNo = 0;
		return data;
	}

	public String getListId() {
		String format = "{}_{}_{}_{}_{}_{}_{}";
		return StringFormatUtil.format(format, storeId, maxTime, minTime,
				cardId, status, pageItemCount, pageNo);
	}

	public long getStoreId() {
		return storeId;
	}

	public QueryAppVersionForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public QueryAppVersionForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public QueryAppVersionForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryAppVersionForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryAppVersionForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getCardId() {
		return cardId;
	}

	public QueryAppVersionForm setCardId(String cardId) {
		this.cardId = cardId;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
