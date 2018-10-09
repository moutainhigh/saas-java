package com.hq.storeManagerMS.service.vipLevel.apiData;

import com.hq.storeManagerMS.common.util.AppUtils;

public class QueryVipLevelForm {
	private int pageItemCount;
	private int pageNo;
	private int state;
	//VipLevelTypeEnum
	private int typeId;
	private String name;

	public static QueryVipLevelForm newInstance() {
		QueryVipLevelForm data = new QueryVipLevelForm();
		data.state = -1;
		data.typeId = -1;
		data.name = "";
		return data;
	}
	
	public String getListId() {
		return AppUtils.joinByUnderline(state, typeId, name);
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryVipLevelForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryVipLevelForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public int getState() {
		return state;
	}

	public QueryVipLevelForm setState(int state) {
		this.state = state;
		return this;
	}

	public int getTypeId() {
		return typeId;
	}

	public QueryVipLevelForm setTypeId(int typeId) {
		this.typeId = typeId;
		return this;
	}

	public String getName() {
		return name;
	}

	public QueryVipLevelForm setName(String name) {
		this.name = name;
		return this;
	}

}
