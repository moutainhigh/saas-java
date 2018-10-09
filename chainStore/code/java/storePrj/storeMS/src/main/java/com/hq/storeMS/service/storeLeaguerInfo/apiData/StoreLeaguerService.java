package com.hq.storeMS.service.storeLeaguerInfo.apiData;

import com.hq.storeMS.service.appointment.data.Appointment;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class StoreLeaguerService {
	private int type;
	// private OrderItem order;
	private Appointment appointment;

	// 创建时间 用于排序
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
}
