package com.hq.storeManagerMS.service.vipLevelType.apiData;

import com.hq.storeManagerMS.common.util.AppUtils;

public class QueryVipLevelTypeForm {
	private int pageItemCount;
	private int pageNo;
	private int state;
	private String name;

	public static QueryVipLevelTypeForm newInstance() {
		QueryVipLevelTypeForm data = new QueryVipLevelTypeForm();
		data.state = -1;
		return data;
	}
	
	public String getListId() {
		return AppUtils.joinByUnderline(state,name);
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryVipLevelTypeForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryVipLevelTypeForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public int getState() {
		return state;
	}

	public QueryVipLevelTypeForm setState(int state) {
		this.state = state;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
