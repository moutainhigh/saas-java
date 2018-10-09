package com.hq.storeManagerMS.service.muser.apiData;

public class MUserQueryForm {
	private int pageItemCount;
	private int pageNo;

	public static MUserQueryForm newInstance() {
		return new MUserQueryForm();
	}
	
	public String getListId() {
		return "";
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public MUserQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public MUserQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

}
