package com.hq.storeClient.service.storeConfig.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeConfig")
public class StoreConfig {
	@Id
	private long id;
	private long storeId;
	// 预约配置
	private AppointConfig appointConfig;
	// 客户配置
	private LeaguerConfig leaguerConfig;
	private ChainConfig chainConfig;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreConfig newInstance() {
		return new StoreConfig();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public AppointConfig getAppointConfig() {
		return appointConfig;
	}

	public void setAppointConfig(AppointConfig appointConfig) {
		this.appointConfig = appointConfig;
	}

	public LeaguerConfig getLeaguerConfig() {
		return leaguerConfig;
	}

	public void setLeaguerConfig(LeaguerConfig leaguerConfig) {
		this.leaguerConfig = leaguerConfig;
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

	public ChainConfig getChainConfig() {
		return chainConfig;
	}

	public void setChainConfig(ChainConfig chainConfig) {
		this.chainConfig = chainConfig;
	}

}
