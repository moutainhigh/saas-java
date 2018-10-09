package com.hq.storeManagerMS.service.sms.apiData;


public class QuerySmsForm {
	private Long phone;
	private Integer isUse;
	
	private Integer pageItemCount;
	private Integer pageNo;

	public static QuerySmsForm newInstance() {
		QuerySmsForm data = new QuerySmsForm();
		data.pageItemCount=20;
		data.pageNo=1;
		data.phone=0L;
		return data;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(Integer pageItemCount) {
		if(pageItemCount != null && pageItemCount > 0){
			this.pageItemCount = pageItemCount;
		}
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		if(pageNo != null && pageNo > 0){
			this.pageNo = pageNo;
		}
	}

}
