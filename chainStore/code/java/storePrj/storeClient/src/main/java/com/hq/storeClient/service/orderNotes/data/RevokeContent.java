package com.hq.storeClient.service.orderNotes.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 撤单明细
 */
@SynClass
public class RevokeContent {
	// 创建时间
	private long createTime;
	// 操作人
	private long creatorId;
	private String creatorName;
	// 备注信息
	private String remark;

	public static RevokeContent newInstance() {
		return new RevokeContent();
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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
