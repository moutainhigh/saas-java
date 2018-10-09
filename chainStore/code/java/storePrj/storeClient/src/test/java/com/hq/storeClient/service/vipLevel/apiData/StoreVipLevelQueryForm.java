package com.hq.storeClient.service.vipLevel.apiData;

import com.zenmind.dao.rest.ReqMap;

public class StoreVipLevelQueryForm {
	private int pageItemCount;
	private int pageNo;
	// 当前登录者的vipLevelId
	private long vipType;

	public static StoreVipLevelQueryForm newInstance() {
		return new StoreVipLevelQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("vipType", vipType);
		return reqMap;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public StoreVipLevelQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public StoreVipLevelQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public long getVipType() {
		return vipType;
	}

	public StoreVipLevelQueryForm setVipType(long vipType) {
		this.vipType = vipType;
		return this;
	}
}
