package com.hq.storeMS.service.orderTrack.apiData;

import com.hq.storeMS.service.orderTrack.data.OrderTrackStatusEnum;

public class OrderTrackUpdateStatusForm {
	// 物流状态 OrderTrackStatusEnum
	private int status;
	//快递公司
	private String company;
	//快递单号
	private String courierNum;

	public static OrderTrackUpdateStatusForm newInstance() {
		return new OrderTrackUpdateStatusForm();
	}
	
	public static OrderTrackUpdateStatusForm newInstance(OrderTrackStatusEnum statusEnum) {
		OrderTrackUpdateStatusForm data = newInstance();
		data.status = statusEnum.ordinal();
		return data;
	}
	
	public OrderTrackStatusEnum getOrderTrackStatusEnum() {
		return OrderTrackStatusEnum.valueOf(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCourierNum() {
		return courierNum;
	}

	public void setCourierNum(String courierNum) {
		this.courierNum = courierNum;
	}
}
