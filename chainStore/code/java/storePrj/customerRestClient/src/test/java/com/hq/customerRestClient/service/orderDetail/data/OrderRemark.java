package com.hq.customerRestClient.service.orderDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class OrderRemark {
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;
	// 订单备注信息
	private String remark;

	public static OrderRemark newInstance() {
		OrderRemark data = new OrderRemark();
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
