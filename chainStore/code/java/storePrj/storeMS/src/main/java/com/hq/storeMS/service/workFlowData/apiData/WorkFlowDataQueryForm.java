package com.hq.storeMS.service.workFlowData.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class WorkFlowDataQueryForm {
	//店铺ID
	private long storeId;
	//开始时间
	private long minTime;
	//结束时间
	private long maxTime;
	
	//工作流类型
	private long workFlowTypeId;
	//状态，如果需要查询多个，请用,号分割
	private String status;
	private Set<Integer> statusSet = new HashSet<Integer>();
	//客户名称 或 手机号码 编号
	private String leaguerNameOrPhone;
	//跟进人员
	private long buserId;
	
	private int pageItemCount;
	private int pageNo;

	public static WorkFlowDataQueryForm newInstance() {
		return new WorkFlowDataQueryForm();
	}
	
	public String getListId(){
		return AppUtils.joinByUnderline(storeId, minTime, maxTime);
	}
	
	public long getStoreId() {
		return storeId;
	}

	public WorkFlowDataQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public WorkFlowDataQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public WorkFlowDataQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public WorkFlowDataQueryForm setStatus(String status) {
		if (StringUtils.isNoneBlank(status)) {
			String[] ss = status.split(",");
			for (String s : ss) {
				this.statusSet.add(Integer.valueOf(s));
			}
		}
		this.status = status;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public WorkFlowDataQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public WorkFlowDataQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getWorkFlowTypeId() {
		return workFlowTypeId;
	}

	public WorkFlowDataQueryForm setWorkFlowTypeId(long workFlowTypeId) {
		this.workFlowTypeId = workFlowTypeId;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public WorkFlowDataQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public String getLeaguerNameOrPhone() {
		return leaguerNameOrPhone;
	}

	public WorkFlowDataQueryForm setLeaguerNameOrPhone(String leaguerNameOrPhone) {
		this.leaguerNameOrPhone = leaguerNameOrPhone;
		return this;
	}


}
