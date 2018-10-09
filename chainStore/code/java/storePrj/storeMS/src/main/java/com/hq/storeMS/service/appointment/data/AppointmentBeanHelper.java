package com.hq.storeMS.service.appointment.data;

import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentBeanHelper {
	public static AppointmentBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentBeanHelper.class);
	}
}
