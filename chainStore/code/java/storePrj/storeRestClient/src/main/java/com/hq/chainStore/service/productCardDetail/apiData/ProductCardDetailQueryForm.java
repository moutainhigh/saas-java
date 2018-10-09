package com.hq.chainStore.service.productCardDetail.apiData;

import com.zenmind.dao.rest.ReqMap;

public class ProductCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 分类
	private String typeId;
	// 状态，如果需要查询多个，请用,号分割
	private String status;
	// 卡名称或者编号
	private String cardNameOrNumber;

	private int pageItemCount;
	private int pageNo;

	public static ProductCardDetailQueryForm newInstance() {
		return new ProductCardDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("status", status).add("typeId", typeId)
				.add("cardNameOrNumber", cardNameOrNumber);
		return reqMap;
	}

	public void addStatus(int statusP) {
		if (this.status == null) {
			this.status = statusP + "";
		} else {
			this.status = this.status + "," + statusP;
		}
	}

	public long getMinTime() {
		return minTime;
	}

	public ProductCardDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public ProductCardDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public ProductCardDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ProductCardDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ProductCardDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardNameOrNumber() {
		return cardNameOrNumber;
	}

	public ProductCardDetailQueryForm setCardNameOrNumber(String cardNameOrNumber) {
		this.cardNameOrNumber = cardNameOrNumber;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public ProductCardDetailQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}
}
