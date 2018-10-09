package com.hq.chainClient.service.chainProduct.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class ProductDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long chainId;

	// 项目编号 或者 名称
	private String numberOrName;
	// 项目分类
	private String typeId;
	// 状态 对应 ProductInfoState
	private Set<Integer> stateArray = new HashSet<Integer>();

	private int pageItemCount;
	private int pageNo;

	public static ProductDetailQueryForm newInstance() {
		return new ProductDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("chainId", chainId).add("numberOrName", numberOrName)
				.add("typeId", typeId).add("stateArray", StringUtils4Client.join(stateArray, ","));
		return reqMap;
	}

	public void addStatus(int statusP) {
		stateArray.add(statusP);
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

	public long getChainId() {
		return chainId;
	}

	public ProductDetailQueryForm setChainId(long chainId) {
		this.chainId = chainId;
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

	public Set<Integer> getStateArray() {
		return stateArray;
	}

	public void setStateArray(Set<Integer> stateArray) {
		this.stateArray = stateArray;
	}
}
