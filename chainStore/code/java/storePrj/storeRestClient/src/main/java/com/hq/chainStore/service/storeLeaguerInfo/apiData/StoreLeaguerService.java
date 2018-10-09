package com.hq.chainStore.service.storeLeaguerInfo.apiData;

import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.order.data.Order;

public class StoreLeaguerService {
	private int type;
	private Order order;
	private Appointment appointment;
	private Long createTime;

	public static StoreLeaguerService newInstance() {
		return new StoreLeaguerService();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "StoreLeaguerService [type=" + type + ", order=" + order
				+ ", appointment=" + appointment + ", createTime=" + createTime
				+ "]";
	}
}
