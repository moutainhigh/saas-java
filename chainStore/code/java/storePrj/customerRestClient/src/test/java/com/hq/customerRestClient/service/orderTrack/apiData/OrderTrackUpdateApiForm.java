package com.hq.customerRestClient.service.orderTrack.apiData;

public class OrderTrackUpdateApiForm {
	// OrderTrackUpdateType
	private int updateType;

	private OrderTrackUpdateStatusForm trackUpdateStatusForm;
	
	public static OrderTrackUpdateApiForm newInstance() {
		OrderTrackUpdateApiForm data = new OrderTrackUpdateApiForm();
		return data;
	}
	
	public OrderTrackUpdateType getOrderTrackUpdateType() {
		return OrderTrackUpdateType.valueOf(updateType);
	}
	
	public void setOrderTrackUpdateType(OrderTrackUpdateType orderTrackUpdateType) {
		updateType = orderTrackUpdateType.ordinal();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public OrderTrackUpdateStatusForm getTrackUpdateStatusForm() {
		return trackUpdateStatusForm;
	}

	public void setTrackUpdateStatusForm(OrderTrackUpdateStatusForm trackUpdateStatusForm) {
		this.trackUpdateStatusForm = trackUpdateStatusForm;
	}
}
