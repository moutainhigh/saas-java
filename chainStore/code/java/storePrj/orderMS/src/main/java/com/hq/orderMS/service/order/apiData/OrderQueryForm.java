package com.hq.orderMS.service.order.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.orderMS.common.util.AppUtils;

public class OrderQueryForm {
	private long storeId;
	// 创建时间段
	private long minTime;
	private long maxTime;
	// 支付时间段
	private long minPayTime;
	private long maxPayTime;

	private String numberOrName;
	private int orderType;
	// OrderStatusEnum
	private Set<Integer> status = new HashSet<Integer>();
	// OrderOriginEnum
	private Set<Integer> origin = new HashSet<Integer>();
	private String leaguerId;
	private long cuserId;
	private long buserId;

	private int pageItemCount;
	private int pageNo;

	public static OrderQueryForm newInstance() {
		OrderQueryForm data = new OrderQueryForm();
		return data;
	}

	public String getListId() {
		return AppUtils.joinByUnderline(storeId, minTime, maxTime, minPayTime, maxPayTime);
	}

	public long getStoreId() {
		return storeId;
	}

	public OrderQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public OrderQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public int getOrderType() {
		return orderType;
	}

	public OrderQueryForm setOrderType(int orderType) {
		this.orderType = orderType;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public OrderQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public OrderQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public OrderQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public OrderQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public OrderQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public long getCuserId() {
		return cuserId;
	}

	public OrderQueryForm setCuserId(long cuserId) {
		this.cuserId = cuserId;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public OrderQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public OrderQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}

	public Set<Integer> getOrigin() {
		return origin;
	}

	public OrderQueryForm setOrigin(Set<Integer> origin) {
		this.origin = origin;
		return this;
	}

	public long getMinPayTime() {
		return minPayTime;
	}

	public OrderQueryForm setMinPayTime(long minPayTime) {
		this.minPayTime = minPayTime;
		return this;
	}

	public long getMaxPayTime() {
		return maxPayTime;
	}

	public OrderQueryForm setMaxPayTime(long maxPayTime) {
		this.maxPayTime = maxPayTime;
		return this;
	}
}
