package com.hq.storeClient.service.storeClerkInfo.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class StoreAdminRole {
	private int id;
	private String name;
	private long storeId;
	private String descript;
	// role的状态，是否有效等 StoreAdminRoleState
	private int state;
	private Set<Integer> permSet = new HashSet<Integer>();
	private long createdTime;
	private long lastUpdateTime;

	public static StoreAdminRole newInstance() {
		StoreAdminRole role = new StoreAdminRole();
		return role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}

	public String getRole(long storeId) {
		final String roleTemplate = "{}:{}";
		return StringFormatUtil.format(roleTemplate, this.name, storeId);
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

}
