package com.hq.chainStore.service.chainDataSyn.apiData;

import com.zenmind.dao.rest.ReqMap;

public class ChainDataQueryForm {
	private long chainId;
	private long storeId;

	private String numberOrName;
	private String typeId;
	private int synStatus = -1;// ChainDataStatusEnum

	private int pageItemCount;
	private int pageNo;

	public static ChainDataQueryForm newInstance() {
		return new ChainDataQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap data = ReqMap.newInstance();
		data.add("chainId", chainId).add("storeId", storeId).add("numberOrName", numberOrName).add("typeId", typeId)
				.add("synStatus", synStatus);
		return data;
	}

	public long getChainId() {
		return chainId;
	}

	public ChainDataQueryForm setChainId(long chainId) {
		this.chainId = chainId;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public ChainDataQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public ChainDataQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public ChainDataQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ChainDataQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ChainDataQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public int getSynStatus() {
		return synStatus;
	}

	public ChainDataQueryForm setSynStatus(int synStatus) {
		this.synStatus = synStatus;
		return this;
	}
}
