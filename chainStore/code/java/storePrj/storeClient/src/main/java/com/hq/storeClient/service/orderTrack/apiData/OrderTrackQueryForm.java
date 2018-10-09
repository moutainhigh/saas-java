package com.hq.storeClient.service.orderTrack.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

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
		String ids = StringUtils4Client.join(orderIds, ",");
		return StringUtils4Client.joinByUnderline(storeId, minTime, maxTime, ids);
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("status", StringUtils4Client.join(status, ","))
			.add("maxTime", maxTime).add("minTime", minTime)
			.add("orderIds", StringUtils4Client.join(orderIds, ","));
		return reqMap;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public void setStatus(Set<Integer> status) {
		this.status = status;
	}

	public Set<Long> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Set<Long> orderIds) {
		this.orderIds = orderIds;
	}
}
