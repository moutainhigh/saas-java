package com.hq.customerRestClient.service.storeCardInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PrdCardType {
	private String id;
	private String name;
	private int entityState;
	private long createTime;
	private long lastUpdateTime;

	public static PrdCardType newInstance() {
		PrdCardType data = new PrdCardType();
		return data;
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

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

}
