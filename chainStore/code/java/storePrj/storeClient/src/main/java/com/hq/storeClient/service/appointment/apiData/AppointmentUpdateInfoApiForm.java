package com.hq.storeClient.service.appointment.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.appointment.data.AppointProduct;

public class AppointmentUpdateInfoApiForm {
	// 客户ID
	private String leaguerId;
	// 预约时间
	private long appointTime;
	// 多个项目
	private List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();

	public static AppointmentUpdateInfoApiForm newInstance() {
		return new AppointmentUpdateInfoApiForm();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
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
