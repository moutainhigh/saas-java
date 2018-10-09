package com.hq.storeClient.service.opLog.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class OpLogQueryForm {
	private long storeId;
	// 操作员
	private String buserName;
	// 主题名 [员工名称、客户名称、产品名称]
	private String name;
	// 日志分类 OpLogTypeEnum
	private Set<Integer> type = new HashSet<Integer>();

	// 时间段条件
	private long minTime;
	private long maxTime;

	private int pageItemCount;
	private int pageNo;

	public static OpLogQueryForm newInstance() {
		OpLogQueryForm data = new OpLogQueryForm();
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("type", StringUtils4Client.join(type, ","))
		.add("buserName", buserName).add("name", name);
		return reqMap;
	}

	public long getStoreId() {
		return storeId;
	}

	public OpLogQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getBuserName() {
		return buserName;
	}

	public OpLogQueryForm setBuserName(String buserName) {
		this.buserName = buserName;
		return this;
	}

	public String getName() {
		return name;
	}

	public OpLogQueryForm setName(String name) {
		this.name = name;
		return this;
	}

	public Set<Integer> getType() {
		return type;
	}

	public OpLogQueryForm setType(Set<Integer> type) {
		this.type = type;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public OpLogQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public OpLogQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public OpLogQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public OpLogQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
