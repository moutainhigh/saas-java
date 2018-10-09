package com.hq.chainStore.service.leaguerRecord.apiData;

import com.zenmind.dao.rest.ReqMap;

public class LeaguerRecordQueryForm {
	private long storeId;
	private long maxTime;
	private long minTime;

	private String leaguerId;
	// 关联流程
	private long workFlowDataId;

	private int pageItemCount;
	private int pageNo;

	public static LeaguerRecordQueryForm newInstance() {
		return new LeaguerRecordQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("leaguerId", leaguerId)
				.add("workFlowDataId", workFlowDataId);
		return reqMap;
	}

	public long getStoreId() {
		return storeId;
	}

	public LeaguerRecordQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public LeaguerRecordQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public LeaguerRecordQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public LeaguerRecordQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public LeaguerRecordQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public LeaguerRecordQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}
}
