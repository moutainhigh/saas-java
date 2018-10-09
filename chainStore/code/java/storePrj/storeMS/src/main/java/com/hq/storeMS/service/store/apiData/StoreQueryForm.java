package com.hq.storeMS.service.store.apiData;

import java.util.HashSet;
import java.util.Set;

public class StoreQueryForm {
	private long chainId;
	private String name;
	private Set<Long> storeIds = new HashSet<Long>();

	private int pageItemCount;
	private int pageNo;

	public static StoreQueryForm newInstance() {
		return new StoreQueryForm();
	}
	
	public void addStoreIds(Long... ids) {
		for (Long id : ids) {
			storeIds.add(id);
		}
	}

	public String getName() {
		return name;
	}

	public StoreQueryForm setName(String name) {
		this.name = name;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public StoreQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public StoreQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public long getChainId() {
		return chainId;
	}

	public StoreQueryForm setChainId(long chainId) {
		this.chainId = chainId;
		return this;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public StoreQueryForm setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
		return this;
	}

}