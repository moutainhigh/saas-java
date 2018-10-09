package com.hq.chainStore.service.activateCode.apiData;

import com.zenmind.dao.rest.ReqMap;

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

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("phone", phone).add("status", status)
				.add("activateCode", activateCode);
		return reqMap;
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
