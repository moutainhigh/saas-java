package com.hq.storeMS.service.orderNotes.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class OrderPayRemark {
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;
	// 订单备注信息
	private String remark;

	public static OrderPayRemark newInstance() {
		OrderPayRemark data = new OrderPayRemark();
		return data;
	}
	
	public static OrderPayRemark newInstance(long creatorIdP, String creatorNameP, String remarkP) {
		OrderPayRemark data = new OrderPayRemark();
		data.creatorId=creatorIdP;
		data.creatorName=creatorNameP;
		data.remark=remarkP;
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
