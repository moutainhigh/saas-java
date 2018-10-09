package com.hq.storeClient.service.spreadStatis.data;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

import javax.persistence.Id;
import javax.persistence.Table;

//推广统计明细
@SynClass
@Table(name = "spreadStatis")
public class SpreadStatis {
	@Id
	private long id;

	// 店铺ID
	@IndexField
	private long storeId;
	// B端用户ID
	private long buserId;
	// 订单ID
	private long orderId;
	// C端用户ID
	private long cuserId;
	// 用户动态Id
	private long dynamicId;

	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static SpreadStatis newInstance() {
		SpreadStatis instance = new SpreadStatis();
		long currentTime = System.currentTimeMillis();
		instance.createdTime = currentTime;
		instance.lastUpdateTime = currentTime;
		return instance;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
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

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(long dynamicId) {
		this.dynamicId = dynamicId;
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
