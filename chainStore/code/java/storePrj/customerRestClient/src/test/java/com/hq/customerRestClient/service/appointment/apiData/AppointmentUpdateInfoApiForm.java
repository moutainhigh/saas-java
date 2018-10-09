package com.hq.customerRestClient.service.appointment.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.customerRestClient.service.appointment.data.AppointProduct;

public class AppointmentUpdateInfoApiForm {
	// 预约时间
	private long appointTime;
	// 多个项目
	private List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();

	public static AppointmentUpdateInfoApiForm newInstance() {
		return new AppointmentUpdateInfoApiForm();
	}

	public long getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(long appointTime) {
		this.appointTime = appointTime;
	}

	public List<AppointProduct> getAppointProducts() {
		return appointProducts;
	}

	public void setAppointProducts(List<AppointProduct> appointProducts) {
		this.appointProducts = appointProducts;
	}

}
