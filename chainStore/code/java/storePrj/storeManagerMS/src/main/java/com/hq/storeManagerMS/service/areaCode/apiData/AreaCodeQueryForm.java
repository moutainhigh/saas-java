package com.hq.storeManagerMS.service.areaCode.apiData;

public class AreaCodeQueryForm {
	private int pageItemCount;
	private int pageNo;

	public static AreaCodeQueryForm newInstance() {
		AreaCodeQueryForm data = new AreaCodeQueryForm();
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
