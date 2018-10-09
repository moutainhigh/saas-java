package com.hq.customerRestClient.service.order.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class MallOrderQueryForm {
	private long storeId;
	private long minTime;
	private long maxTime;

	private String numberOrName;
	// OrderTrackStatusEnum
	private Set<Integer> status = new HashSet<Integer>();
	private String leaguerId;

	private int pageItemCount;
	private int pageNo;

	public static MallOrderQueryForm newInstance() {
		MallOrderQueryForm data = new MallOrderQueryForm();
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("numberOrName", numberOrName)
				.add("status", StringUtils4Client.join(status, ",")).add("maxTime", maxTime).add("minTime", minTime)
				.add("leaguerId", leaguerId);
		return reqMap;
	}
	
	public void addState(int...st) {
		for (int i : st) {
			status.add(i);
		}
	}

	public long getStoreId() {
		return storeId;
	}

	public MallOrderQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public MallOrderQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public MallOrderQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public String getNumberOrName() {
		return numberOrName;
	}

	public MallOrderQueryForm setNumberOrName(String numberOrName) {
		this.numberOrName = numberOrName;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public MallOrderQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public MallOrderQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public MallOrderQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public MallOrderQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
