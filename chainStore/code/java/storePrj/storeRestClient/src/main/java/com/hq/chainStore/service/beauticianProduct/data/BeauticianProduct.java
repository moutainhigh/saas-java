package com.hq.chainStore.service.beauticianProduct.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "beauticianProduct")
public class BeauticianProduct implements IntfSynData {
	@Id
	private String id;

	private long storeId;
	private long buserId;
	//有序的项目ID   只是对置顶的项目才有。
	private List<Long> productIds = new ArrayList<Long>();

	private long lastUpdateTime;
	private long createdTime;
	private long ver;
	
	public static BeauticianProduct newInstance() {
		BeauticianProduct beauticianProduct = new BeauticianProduct();
		return beauticianProduct;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	@Override
	public String toString() {
		return "BeauticianProduct [id=" + id + ", storeId=" + storeId
				+ ", buserId=" + buserId + ", productIds=" + productIds
				+ ", lastUpdateTime=" + lastUpdateTime + ", createdTime="
				+ createdTime + ", ver=" + ver + "]";
	}

}
