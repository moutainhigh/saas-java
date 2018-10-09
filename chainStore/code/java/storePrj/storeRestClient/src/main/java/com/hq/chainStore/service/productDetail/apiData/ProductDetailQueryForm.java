package com.hq.chainStore.service.productDetail.apiData;

import com.zenmind.dao.rest.ReqMap;

public class ProductDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 项目编号 或者 名称
	private String numberOrName;
	// 项目分类
	private String typeId;
	// 状态 对应ProductInfoState
	private int state=-1;

	private int pageItemCount;
	private int pageNo;

	public static ProductDetailQueryForm newInstance() {
		return new ProductDetailQueryForm();
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

	public ProductDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public ProductDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public ProductDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ProductDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ProductDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public ProductDetailQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public ProductDetailQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public int getState() {
		return state;
	}

	public ProductDetailQueryForm setState(int state) {
		this.state = state;
		return this;
	}
}
