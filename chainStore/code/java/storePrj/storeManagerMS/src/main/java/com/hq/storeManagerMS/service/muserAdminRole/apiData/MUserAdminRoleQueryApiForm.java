package com.hq.storeManagerMS.service.muserAdminRole.apiData;

import com.hq.storeManagerMS.common.util.AppUtils;

public class MUserAdminRoleQueryApiForm {
	private String name;
	private int state;
	
	private int pageItemCount;
	private int pageNo;

	public static MUserAdminRoleQueryApiForm newInstance(){
		MUserAdminRoleQueryApiForm data = new MUserAdminRoleQueryApiForm();
		return data;
	}

	public String getListId(){
		return AppUtils.joinByUnderline(name, state);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
