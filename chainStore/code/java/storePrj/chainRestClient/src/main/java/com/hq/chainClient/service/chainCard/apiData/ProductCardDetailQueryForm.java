package com.hq.chainClient.service.chainCard.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class ProductCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long chainId;

	// 分类
	private String typeId;
	// 卡状态 对应 CardStatusEnum
	private Set<Integer> statusSet = new HashSet<Integer>();
	// 卡名称或者编号
	private String cardNameOrNumber;

	private int pageItemCount;
	private int pageNo;

	public static ProductCardDetailQueryForm newInstance() {
		return new ProductCardDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("chainId", chainId)
				.add("statusSet", StringUtils4Client.join(statusSet, ",")).add("typeId", typeId)
				.add("cardNameOrNumber", cardNameOrNumber);
		return reqMap;
	}

	public void addStatus(int statusP) {
		statusSet.add(statusP);
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

	public long getChainId() {
		return chainId;
	}

	public ProductCardDetailQueryForm setChainId(long chainId) {
		this.chainId = chainId;
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

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public ProductCardDetailQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
		return this;
	}
}
