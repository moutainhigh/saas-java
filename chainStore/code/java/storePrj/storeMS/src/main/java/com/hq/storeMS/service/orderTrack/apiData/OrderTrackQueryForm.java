package com.hq.storeMS.service.orderTrack.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class OrderTrackQueryForm {
	private long storeId;
	private long maxTime;
	private long minTime;

	private Set<Integer> status = new HashSet<Integer>();
	private Set<Long> orderIds = new HashSet<Long>();

	public static OrderTrackQueryForm newInstance() {
		return new OrderTrackQueryForm();
	}

	public String getListId() {
		String ids = StringUtils.join(orderIds, ",");
		return AppUtils.joinByUnderline(storeId, minTime, maxTime, ids);
	}

	public long getStoreId() {
		return storeId;
	}

	public OrderTrackQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public OrderTrackQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public OrderTrackQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public OrderTrackQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}

	public Set<Long> getOrderIds() {
		return orderIds;
	}

	public OrderTrackQueryForm setOrderIds(Set<Long> orderIds) {
		this.orderIds = orderIds;
		return this;
	}
}
