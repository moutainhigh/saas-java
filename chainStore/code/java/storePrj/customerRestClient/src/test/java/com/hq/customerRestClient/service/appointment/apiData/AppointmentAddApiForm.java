package com.hq.customerRestClient.service.appointment.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.customerRestClient.service.appointment.data.AppointProduct;

public class AppointmentAddApiForm {
	// 店铺ID
	private long storeId;
	// 预约时间
	private long appointTime;
	// 多个项目
	private List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();

	public static AppointmentAddApiForm newInstance() {
		return new AppointmentAddApiForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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
