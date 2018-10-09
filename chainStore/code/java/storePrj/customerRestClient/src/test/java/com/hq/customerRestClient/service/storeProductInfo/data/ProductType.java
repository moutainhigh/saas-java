package com.hq.customerRestClient.service.storeProductInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ProductType {
	private String id;
	private String name;
	private int entityState;
	private long createTime;
	private long lastUpdateTime;

	public static ProductType newInstance() {
		ProductType type = new ProductType();
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

}
