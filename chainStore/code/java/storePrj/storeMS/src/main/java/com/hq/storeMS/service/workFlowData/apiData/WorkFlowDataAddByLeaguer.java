package com.hq.storeMS.service.workFlowData.apiData;

/**
 * 预约转收银实体
 * 
 * @author Administrator
 *
 */
public class WorkFlowDataAddByLeaguer {
	// 客户ID
	private String leaguerId;
	private String leaguerName;
	// 创建者ID
	private long creatorId;
	// 店铺ID
	private long storeId;
	// 工作流类型ID
	private long workFlowTypeId;

	public static WorkFlowDataAddByLeaguer newInstance() {
		return new WorkFlowDataAddByLeaguer();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getWorkFlowTypeId() {
		return workFlowTypeId;
	}

	public void setWorkFlowTypeId(long workFlowTypeId) {
		this.workFlowTypeId = workFlowTypeId;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public void setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
	}
}
