package com.hq.chainMS.service.chain.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainMS.common.util.AppUtils;

public class ChainQueryForm {
	private String number;
	private Set<Long> chainIds = new HashSet<Long>();

	private int pageItemCount;
	private int pageNo;

	public static ChainQueryForm newInstance() {
		ChainQueryForm data = new ChainQueryForm();
		return data;
	}
	
	public String getListId() {
		return AppUtils.joinByUnderline(number, chainIds.toString());
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
