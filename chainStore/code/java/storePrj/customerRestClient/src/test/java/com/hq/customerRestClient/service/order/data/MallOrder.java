package com.hq.customerRestClient.service.order.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MallOrder {
	private Order order;
	//OrderTrackStatusEnum
	private int trackStatus;

	public static MallOrder newInstance() {
		MallOrder data = new MallOrder();
		return data;
	}
	
	public static MallOrder newInstance(Order order, int trackStatus) {
		MallOrder data = newInstance();
		data.order = order;
		data.trackStatus = trackStatus;
		return data;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getTrackStatus() {
		return trackStatus;
	}

	public void setTrackStatus(int trackStatus) {
		this.trackStatus = trackStatus;
	}

}
