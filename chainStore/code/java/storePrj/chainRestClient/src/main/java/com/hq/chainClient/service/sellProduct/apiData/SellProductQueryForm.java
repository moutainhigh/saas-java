package com.hq.chainClient.service.sellProduct.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class SellProductQueryForm {
	private long chainId;
	// 编号 或者 名称
	private String numberOrName;
	// 分类
	private String typeId;
	// 状态 SellProductStateEnum
	private Set<Integer> stateArray = new HashSet<Integer>();
	// 产品类型 多个用,连接 对应 sellProductTypeEnum
	private Set<Integer> sellProductTypeArray = new HashSet<Integer>();

	private int pageItemCount;
	private int pageNo;

	public static SellProductQueryForm newInstance() {
		return new SellProductQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("chainId", chainId).add("numberOrName", numberOrName).add("typeId", typeId)
				.add("stateArray", StringUtils4Client.join(stateArray, ","))
				.add("sellProductTypeArray", StringUtils4Client.join(sellProductTypeArray, ","));
		return reqMap;
	}

	public long getChainId() {
		return chainId;
	}

	public SellProductQueryForm setChainId(long chainId) {
		this.chainId = chainId;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public SellProductQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public SellProductQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public SellProductQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public SellProductQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Set<Integer> getStateArray() {
		return stateArray;
	}

	public SellProductQueryForm setStateArray(Set<Integer> stateArray) {
		this.stateArray = stateArray;
		return this;
	}

	public Set<Integer> getSellProductTypeArray() {
		return sellProductTypeArray;
	}

	public SellProductQueryForm setSellProductTypeArray(Set<Integer> sellProductTypeArray) {
		this.sellProductTypeArray = sellProductTypeArray;
		return this;
	}
}
