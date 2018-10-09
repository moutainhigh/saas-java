package com.hq.storeMS.service.orderNotes.apiData;

import com.hq.storeMS.service.orderNotes.data.RevokeContent;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class RevokeContentAddForm {
	// 操作人
	private long creatorId;
	private String creatorName;
	// 备注信息
	private String remark;

	public static RevokeContentAddForm newInstance() {
		return new RevokeContentAddForm();
	}
	
	public RevokeContent toRevokeContent() {
		RevokeContent data = RevokeContent.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setCreateTime(System.currentTimeMillis());
		return data;
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
