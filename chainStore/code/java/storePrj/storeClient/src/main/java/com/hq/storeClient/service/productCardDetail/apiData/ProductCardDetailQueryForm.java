package com.hq.storeClient.service.productCardDetail.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class ProductCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 分类
	private String typeId;
	// 卡状态
	private Set<Integer> status = new HashSet<Integer>();
	// 卡名称或者编号
	private String cardNameOrNumber;

	private int pageItemCount;
	private int pageNo;

	public static ProductCardDetailQueryForm newInstance() {
		return new ProductCardDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId)
				.add("status", StringUtils4Client.join(status, ",")).add("typeId", typeId)
				.add("cardNameOrNumber", cardNameOrNumber);
		return reqMap;
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

	public Set<Integer> getStatus() {
		return status;
	}

	public ProductCardDetailQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}
}
