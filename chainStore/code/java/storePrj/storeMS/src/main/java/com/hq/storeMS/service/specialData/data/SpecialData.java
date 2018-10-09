package com.hq.storeMS.service.specialData.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "specialData")
public class SpecialData {
	@Id
	private String id;// ID是由 storeId_buserId组成的字符串

	private long storeId;
	private long buserId;

	// 产品ID
	private Set<Long> productIds = new HashSet<Long>();
	// 医美师ID
	private Set<Long> beauticianIds = new HashSet<Long>();
	// 消费者ID
	private Set<Long> cuserIds = new HashSet<Long>();

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static SpecialData newInstance() {
		SpecialData data = new SpecialData();

		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;

		return data;
	}
	
	public String genSpecialFocusOrderId(){
		return this.storeId+"_"+this.buserId;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
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

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Set<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(Set<Long> productIds) {
		this.productIds = productIds;
	}

	public Set<Long> getBeauticianIds() {
		return beauticianIds;
	}

	public void setBeauticianIds(Set<Long> beauticianIds) {
		this.beauticianIds = beauticianIds;
	}

	public Set<Long> getCuserIds() {
		return cuserIds;
	}

	public void setCuserIds(Set<Long> cuserIds) {
		this.cuserIds = cuserIds;
	}

	@Override
	public String toString() {
		return "SpecialFocusOrder [id=" + id + ", storeId=" + storeId
				+ ", buserId=" + buserId + ", productIds=" + productIds
				+ ", beauticianIds=" + beauticianIds + ", cuserIds=" + cuserIds
				+ ", createdTime=" + createdTime + ", lastUpdateTime="
				+ lastUpdateTime + ", ver=" + ver + "]";
	}
}
