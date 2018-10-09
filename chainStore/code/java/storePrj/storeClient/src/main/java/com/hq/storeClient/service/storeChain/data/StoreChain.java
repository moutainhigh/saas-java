package com.hq.storeClient.service.storeChain.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeChain")
public class StoreChain {
	@Id
	private String id;
	
	private long chainId;
	private long storeId;
	
	public static StoreChain newInstance(){
		StoreChain data = new StoreChain();
		return data;
	}
	
	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
