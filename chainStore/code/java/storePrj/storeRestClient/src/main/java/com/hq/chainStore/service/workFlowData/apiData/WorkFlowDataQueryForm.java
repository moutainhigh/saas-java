package com.hq.chainStore.service.workFlowData.apiData;

import com.zenmind.dao.rest.ReqMap;

public class WorkFlowDataQueryForm {
	// 店铺ID
	private long storeId;
	// 状态，如果需要查询多个，请用,号分割
	private String status;
	// 开始时间
	private long minTime;
	// 结束时间
	private long maxTime;
	// 工作流类型
	private long workFlowTypeId;
	// 客户名称 或 手机号码 编号
	private String leaguerNameOrPhone;
	// 跟进人员
	private long buserId;

	private int pageItemCount;
	private int pageNo;

	public static WorkFlowDataQueryForm newInstance() {
		WorkFlowDataQueryForm data = new WorkFlowDataQueryForm();
		data.storeId = 0;
		data.pageItemCount = 0;
		data.pageNo = 1;
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("status", status).add("minTime", minTime).add("maxTime", maxTime)
				.add("leaguerNameOrPhone", leaguerNameOrPhone).add("buserId", buserId);
		return reqMap;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getWorkFlowTypeId() {
		return workFlowTypeId;
	}

	public void setWorkFlowTypeId(long workFlowTypeId) {
		this.workFlowTypeId = workFlowTypeId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public String getLeaguerNameOrPhone() {
		return leaguerNameOrPhone;
	}

	public void setLeaguerNameOrPhone(String leaguerNameOrPhone) {
		this.leaguerNameOrPhone = leaguerNameOrPhone;
	}

}
