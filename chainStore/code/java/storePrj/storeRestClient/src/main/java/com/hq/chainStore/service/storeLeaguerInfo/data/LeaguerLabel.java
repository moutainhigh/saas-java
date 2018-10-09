package com.hq.chainStore.service.storeLeaguerInfo.data;

public class LeaguerLabel {
	private String id;
	private String name;
	private int entityState;
	private long lastUpdateTime;

	public static LeaguerLabel newInstance() {
		return new LeaguerLabel();
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

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
