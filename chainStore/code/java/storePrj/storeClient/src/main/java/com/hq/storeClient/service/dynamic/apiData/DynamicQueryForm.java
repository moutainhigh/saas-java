package com.hq.storeClient.service.dynamic.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dao.rest.ReqMap;

public class DynamicQueryForm {
	private long maxTime;
	private long minTime;
	private long buserId;

	// DynamicStatusEnum
	private Set<Integer> status = new HashSet<Integer>();
	// 店铺Id
	private long storeId;
	// 文案内容
	private String content;

	private int pageItemCount;
	private int pageNo;

	public static DynamicQueryForm newInstance() {
		DynamicQueryForm data = new DynamicQueryForm();
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId)
				.add("status", StringUtils4Client.join(status, ",")).add("buserId", buserId).add("content", content);
		return reqMap;
	}

	public String getListId() {
		String format = "{}_{}_{}";
		return StringFormatUtil.format(format, buserId, maxTime, minTime);
	}

	public DynamicQueryForm addStatus(int... state) {
		for (int i : state) {
			status.add(i);
		}
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public DynamicQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public DynamicQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public DynamicQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public DynamicQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public DynamicQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public DynamicQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public DynamicQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}

	public String getContent() {
		return content;
	}

	public DynamicQueryForm setContent(String content) {
		this.content = content;
		return this;
	}
}
