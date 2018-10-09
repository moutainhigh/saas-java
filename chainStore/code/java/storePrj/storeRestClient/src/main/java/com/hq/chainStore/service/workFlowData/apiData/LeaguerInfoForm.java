package com.hq.chainStore.service.workFlowData.apiData;

public class LeaguerInfoForm {
	// 客户ID
	private String leaguerId;
	// 客户名称
	private String leaguerName;
	// 客户 服务前拍照
	private String picBefore;
	// 客户 服务后拍照
	private String picAfter;
	// 客户档案ID 备注信息
	private long cuserDocId;
	// 客户关怀跟进ID 闹钟信息
	private long cuserCareId;
	// 跟进人员
	private long followUserId;

	public static LeaguerInfoForm newInstance() {
		return new LeaguerInfoForm();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getPicBefore() {
		return picBefore;
	}

	public void setPicBefore(String picBefore) {
		this.picBefore = picBefore;
	}

	public String getPicAfter() {
		return picAfter;
	}

	public void setPicAfter(String picAfter) {
		this.picAfter = picAfter;
	}

	public long getCuserDocId() {
		return cuserDocId;
	}

	public void setCuserDocId(long cuserDocId) {
		this.cuserDocId = cuserDocId;
	}

	public long getCuserCareId() {
		return cuserCareId;
	}

	public void setCuserCareId(long cuserCareId) {
		this.cuserCareId = cuserCareId;
	}

	public long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(long followUserId) {
		this.followUserId = followUserId;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public void setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
	}
}
