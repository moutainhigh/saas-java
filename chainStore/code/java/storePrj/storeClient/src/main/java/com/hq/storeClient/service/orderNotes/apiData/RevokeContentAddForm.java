package com.hq.storeClient.service.orderNotes.apiData;

public class RevokeContentAddForm {
	// 操作人
	private long creatorId;
	private String creatorName;
	// 备注信息
	private String remark;

	public static RevokeContentAddForm newInstance() {
		return new RevokeContentAddForm();
	}
	
	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
