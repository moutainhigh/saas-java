package com.hq.chainStore.service.appointment.data;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;

public class AppointmentDetails{
	private Appointment appointment;
	private ProductInfo productInfo;
	private BUser beauticianInfo;
//	private Leaguer leaguer;

	public static AppointmentDetails newInstance() {
		return new AppointmentDetails();
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public BUser getBeauticianInfo() {
		return beauticianInfo;
	}

	public void setBeauticianInfo(BUser beauticianInfo) {
		this.beauticianInfo = beauticianInfo;
	}



}
