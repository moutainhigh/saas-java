package com.hq.chainStore.service.report.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "orderReport")
public class OrderReport implements IntfSynData {
	@Id
	private long id;
	//订单ID
	private long orderId;
	// 店铺id
	private long storeId;
	// 订单消费金额
	private float cost;
	// 消费者id
	private long cuserId;
	// 订单服务者id
	private long buserId;
	// 项目id
	private long prdId;
	//支付渠道
	private int payType;
	// 消单时间
	private long time;
	
	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static OrderReport newInstance() {
		return new OrderReport();
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	@Override
	public String toString() {
		return "OrderReport [orderId=" + orderId + ", storeId=" + storeId
				+ ", cost=" + cost + ", cuserId=" + cuserId + ", buserId="
				+ buserId + ", prdId=" + prdId + ", time=" + time + "]";
	}

}
