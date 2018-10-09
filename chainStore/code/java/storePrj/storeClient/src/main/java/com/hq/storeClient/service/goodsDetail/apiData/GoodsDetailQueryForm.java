package com.hq.storeClient.service.goodsDetail.apiData;

import com.zenmind.dao.rest.ReqMap;

public class GoodsDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 商品编号 或者 名称
	private String numberOrName;
	// 商品分类
	private String typeId;
	// 状态 对应 GoodsStateEnum
	private int state=-1;

	private int pageItemCount;
	private int pageNo;

	public static GoodsDetailQueryForm newInstance() {
		return new GoodsDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId)
			.add("numberOrName", numberOrName).add("typeId", typeId).add("state", state);
		return reqMap;
	}

	public long getMinTime() {
		return minTime;
	}

	public GoodsDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public GoodsDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public GoodsDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public GoodsDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public GoodsDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public GoodsDetailQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public GoodsDetailQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public int getState() {
		return state;
	}

	public GoodsDetailQueryForm setState(int state) {
		this.state = state;
		return this;
	}
}
