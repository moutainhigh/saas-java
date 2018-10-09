package com.hq.storeMS.service.workFlowType.apiData;


public class QueryWorkFlowTypeForm {
	private int pageItemCount;
	private int pageNo;

	public static QueryWorkFlowTypeForm newInstance() {
		QueryWorkFlowTypeForm data = new QueryWorkFlowTypeForm();
		data.pageItemCount = 0;
		data.pageNo = 1;
		return data;
	}

	public String getListId() {
		return "";
	}

	public Integer getPageItemCount() {
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
