package com.hq.customerMS.service.sms.apiData;

public class QuerySmsForm {
	private long phone;
	private int isUse;

	private int pageItemCount;
	private int pageNo;

	public static QuerySmsForm newInstance() {
		QuerySmsForm data = new QuerySmsForm();
		data.pageItemCount = 0;
		data.pageNo = 1;
		data.phone = 0L;
		return data;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
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
