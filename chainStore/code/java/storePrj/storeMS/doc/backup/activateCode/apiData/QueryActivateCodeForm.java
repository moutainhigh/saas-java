package com.hq.storeMS.service.activateCode.apiData;

import com.zenmind.common.StringFormatUtil;

public class QueryActivateCodeForm {
	private long phone;
	private int status;
	private String activateCode;

	private int pageItemCount;
	private int pageNo;

	public static QueryActivateCodeForm newInstance() {
		QueryActivateCodeForm data = new QueryActivateCodeForm();
		data.phone = 0L;
		data.status = 0;
		data.activateCode = "";
		data.pageItemCount = 0;
		data.pageNo = 1;
		return data;
	}

	public String getListId() {
		String format = "{}_{}_{}_{}";
		return StringFormatUtil.format(format, phone, status, pageItemCount,
				pageNo);
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getActivateCode() {
		return activateCode;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}
}
