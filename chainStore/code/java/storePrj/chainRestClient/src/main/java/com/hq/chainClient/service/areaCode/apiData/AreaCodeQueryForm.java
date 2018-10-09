package com.hq.chainClient.service.areaCode.apiData;

import com.zenmind.dao.rest.ReqMap;

public class AreaCodeQueryForm {
	private int pageItemCount;
	private int pageNo;

	public static AreaCodeQueryForm newInstance() {
		AreaCodeQueryForm data = new AreaCodeQueryForm();
		data.pageItemCount = 0;
		data.pageNo = 0;
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		return reqMap;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public AreaCodeQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public AreaCodeQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
