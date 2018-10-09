package com.hq.chainStore.service.chain.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.common.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class ChainQueryForm {
	private String number;
	private Set<Long> chainIds = new HashSet<Long>();

	private int pageItemCount;
	private int pageNo;

	public static ChainQueryForm newInstance() {
		return new ChainQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap data = ReqMap.newInstance();
		data.add("number", number).add("chainIds", StringUtils4Client.join(chainIds, ","));
		return data;
	}

	public String getNumber() {
		return number;
	}

	public ChainQueryForm setNumber(String number) {
		this.number = number;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ChainQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ChainQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Set<Long> getChainIds() {
		return chainIds;
	}

	public ChainQueryForm setChainIds(Set<Long> chainIds) {
		this.chainIds = chainIds;
		return this;
	}
}
