package com.hq.storeManagerMS.service.vipLevelType.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeManagerMS.service.common.EntityState;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "vipLevelType")
public class VipLevelType {

	@Id
	private long id;

	private String name;

	// VipLevelTypeStateEnum
	private int state;

	private int entityState;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static VipLevelType newInstance() {
		VipLevelType data = new VipLevelType();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		data.entityState = EntityState.Normal.ordinal();
		return data;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

}
