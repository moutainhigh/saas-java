package com.hq.storeClient.service.orderNotes.apiData;

public class OrderNotesAddForm {
	// 订单ID
	private long orderId;
	// 店铺ID
	private long storeId;
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;
	// 订单备注信息
	private String remark;
	
	public static OrderNotesAddForm newInstance() {
		OrderNotesAddForm data = new OrderNotesAddForm();
		return data;
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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
