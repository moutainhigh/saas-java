package com.hq.chainStore.service.workFlowData.apiData;

/**
 * 预约转收银实体
 * 
 * @author Administrator
 *
 */
public class WorkFlowDataAddByAppoint {
	private long storeId;
	private long appointmentId;
	private long creatorId;

	public static WorkFlowDataAddByAppoint newInstance() {
		return new WorkFlowDataAddByAppoint();
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
}
