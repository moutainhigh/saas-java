package com.hq.chainStore.service.appointment.apiData;

public class AppointmentUpdateApiForm {
	private long storeId;
	private int updateType;

	private AppointmentUpdateInfoApiForm updateInfoData;
	
	private AppointmentUpdateStatusApiForm updateStatusData;
	
	private AppointmentDeleteForm appointmentDeleteForm;

	public static AppointmentUpdateApiForm newInstance() {
		return new AppointmentUpdateApiForm();
	}

	public AppointmentUpdateType getUpdateTypeEnum() {
		return AppointmentUpdateType.valueOf(updateType);
	}

	public void setUpdateTypeEnum(AppointmentUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public int getUpdateType() {
		return updateType;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public AppointmentUpdateInfoApiForm getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(AppointmentUpdateInfoApiForm updateInfoData) {
		this.updateInfoData = updateInfoData;
	}

	public AppointmentUpdateStatusApiForm getUpdateStatusData() {
		return updateStatusData;
	}

	public void setUpdateStatusData(
			AppointmentUpdateStatusApiForm updateStatusData) {
		this.updateStatusData = updateStatusData;
	}

	@Override
	public String toString() {
		return "AppointmentUpdateApiForm [updateType=" + updateType
				+ ", updateInfoData=" + updateInfoData + ", updateStatusData="
				+ updateStatusData + "]";
	}

	public AppointmentDeleteForm getAppointmentDeleteForm() {
		return appointmentDeleteForm;
	}

	public void setAppointmentDeleteForm(AppointmentDeleteForm appointmentDeleteForm) {
		this.appointmentDeleteForm = appointmentDeleteForm;
	}

}
