package com.hq.chainStore.service.workFlowType.apiData;

import com.zenmind.common.StringFormatUtil;

public class QueryWorkFlowTypeForm {
	private Integer pageItemCount;
	private Integer pageNo;

	public static QueryWorkFlowTypeForm newInstance() {
		QueryWorkFlowTypeForm data = new QueryWorkFlowTypeForm();
		data.pageItemCount = 0;
		data.pageNo = 1;
		return data;
	}

	public String getListId() {
		String format = "{}_{}";
		return StringFormatUtil.format(format, pageItemCount, pageNo);
	}

	public Integer getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(Integer pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}
