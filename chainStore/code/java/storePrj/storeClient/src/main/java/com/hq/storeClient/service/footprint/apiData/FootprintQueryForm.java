package com.hq.storeClient.service.footprint.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class FootprintQueryForm {
	private long maxTime;
	private long minTime;
	private long buserId;

	// 动态的类型
	private Set<Integer> dynamicType = new HashSet<Integer>();

	private int pageItemCount;
	private int pageNo;

	public static FootprintQueryForm newInstance() {
		FootprintQueryForm data = new FootprintQueryForm();
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("buserId", buserId).add("dynamicType",
				StringUtils4Client.join(dynamicType, ","));
		return reqMap;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public FootprintQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public FootprintQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public FootprintQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public FootprintQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public FootprintQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Set<Integer> getDynamicType() {
		return dynamicType;
	}

	public FootprintQueryForm setDynamicType(Set<Integer> dynamicType) {
		this.dynamicType = dynamicType;
		return this;
	}
}
