package com.hq.storeMS.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.PayItem;

public class OrderAddByWorkflowDataIdForm {
	private long workFlowDataId;
	// 支付信息
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 备注
	private String remark;

	public static OrderAddByWorkflowDataIdForm newInstance() {
		return new OrderAddByWorkflowDataIdForm();
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
