package com.hq.chainClient.service.chainGoods.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class GoodsDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long chainId;

	// 商品编号 或者 名称
	private String numberOrName;
	// 商品分类
	private String typeId;
	// 状态 对应 GoodsStateEnum
	private Set<Integer> stateArray = new HashSet<Integer>();

	private int pageItemCount;
	private int pageNo;

	public static GoodsDetailQueryForm newInstance() {
		return new GoodsDetailQueryForm();
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

	public long getChainId() {
		return chainId;
	}

	public GoodsDetailQueryForm setChainId(long chainId) {
		this.chainId = chainId;
		return this;
	}

	public Set<Integer> getStateArray() {
		return stateArray;
	}

	public GoodsDetailQueryForm setStateArray(Set<Integer> stateArray) {
		this.stateArray = stateArray;
		return this;
	}

}
