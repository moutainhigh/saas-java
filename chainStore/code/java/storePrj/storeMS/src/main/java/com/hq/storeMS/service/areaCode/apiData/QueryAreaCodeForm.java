package com.hq.storeMS.service.areaCode.apiData;

public class QueryAreaCodeForm {
	private int pageItemCount;
	private int pageNo;

	public static QueryAreaCodeForm newInstance() {
		QueryAreaCodeForm data = new QueryAreaCodeForm();
		data.pageItemCount = 0;
		data.pageNo = 0;
		return data;
	}

	public String getListId() {
		return "";
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryAreaCodeForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryAreaCodeForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
