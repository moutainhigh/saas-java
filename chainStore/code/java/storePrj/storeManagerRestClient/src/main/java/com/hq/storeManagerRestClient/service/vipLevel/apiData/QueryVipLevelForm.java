package com.hq.storeManagerRestClient.service.vipLevel.apiData;

import com.zenmind.dao.rest.ReqMap;

public class QueryVipLevelForm {
	private int pageItemCount;
	private int pageNo;
	// 等级状态 VipLevelStateEnum
	private int state;
	// VipLevelTypeEnum
	private int typeId;

	public static QueryVipLevelForm newInstance() {
		return new QueryVipLevelForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("state", state).add("typeId", typeId);
		return reqMap;
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

}
