package com.hq.chainStore.service.report.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "materialReport")
public class MaterialReport implements IntfSynData {

	@Id
	private long id;

	// 订单ID
	private long orderId;

	// 店铺id
	private long storeId;

	// 耗材id
	private String materialId;

	// 医美师id
	private long buserId;

	// 项目id
	private long prdId;
	
	private int count;
	private float referencePrice;

	// 订单消费金额
	private float cost;

	// 消单时间
	private long time;

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static MaterialReport newInstance() {
		MaterialReport data = new MaterialReport();
		return data;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getPrdId() {
		return prdId;
	}

	public void setPrdId(long prdId) {
		this.prdId = prdId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(float referencePrice) {
		this.referencePrice = referencePrice;
	}

	@Override
	public String toString() {
		return "MaterialReport [id=" + id + ", orderId=" + orderId
				+ ", storeId=" + storeId + ", materialId=" + materialId
				+ ", buserId=" + buserId + ", prdId=" + prdId + ", cost="
				+ cost + ", time=" + time + "]";
	}

}
