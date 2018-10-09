package com.hq.chainStore.service.cardRecord.apiData;

public class QueryCardRecordForm {
	private long storeId;
	private long maxTime;
	private long minTime;
	private String cardId;
	private int status;

	private int pageItemCount;
	private int pageNo;

	public static QueryCardRecordForm newInstance() {
		return new QueryCardRecordForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public QueryCardRecordForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public QueryCardRecordForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public QueryCardRecordForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryCardRecordForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryCardRecordForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getCardId() {
		return cardId;
	}

	public QueryCardRecordForm setCardId(String cardId) {
		this.cardId = cardId;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public QueryCardRecordForm setStatus(int status) {
		this.status = status;
		return this;
	}
}
