package com.hq.storeClient.service.storeChain.apiData;

import com.hq.storeClient.service.storeChain.data.StoreChainItemType;

public class StoreChainUpdateStatusForm {
	// 店铺ID
	private long storeId;
	// 项目、商品、套餐、会员卡、次卡等 对应 StoreChainItemType
	private int itemType;
	// 项目、商品、套餐、会员卡、次卡等ID
	private String id;
	// 上下架 对应 StoreChainStatus
	private int status;

	public static StoreChainUpdateStatusForm newInstance() {
		return new StoreChainUpdateStatusForm();
	}

	public StoreChainItemType getStoreChainItemType() {
		return StoreChainItemType.valueOf(itemType);
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
}
