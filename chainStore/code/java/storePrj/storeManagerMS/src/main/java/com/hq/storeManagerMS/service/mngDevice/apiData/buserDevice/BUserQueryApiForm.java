package com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice;

public class BUserQueryApiForm {
	//商家账号
	private long buserPhone; 
	//每页条数
	private int pageItemCount;
	//页号
	private int pageNo;
	
	

	public static BUserQueryApiForm newInstance() {
		BUserQueryApiForm params = new BUserQueryApiForm();
		params.pageItemCount = 20;
		params.pageNo = 1;
		return params;
	}
	
	public long getBuserPhone() {
		return buserPhone;
	}
	public void setBuserPhone(long buserPhone) {
		this.buserPhone = buserPhone;
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
