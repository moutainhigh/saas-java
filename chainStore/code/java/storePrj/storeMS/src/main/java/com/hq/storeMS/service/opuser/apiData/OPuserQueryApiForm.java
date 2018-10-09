package com.hq.storeMS.service.opuser.apiData;

import com.zenmind.common.StringFormatUtil;

public class OPuserQueryApiForm {
	
	private long id;
	
	private long phone;
	
	private String name;
	
	private int pageItemCount;
	
	private int pageNo;
	
	public static OPuserQueryApiForm newInstance(){
		return new OPuserQueryApiForm();
	}
	
	public String getListId(){
		String format = "{}_{}_{}_{}_{}";
		return StringFormatUtil.format(format, id, phone, name, pageItemCount, pageNo);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
