package com.hq.customerMS.service.appointment.data;

import com.hq.customerMS.common.util.AppUtils;
import com.hq.storeClient.service.appointment.data.Appointment;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentCacheDAO {

	public static AppointmentCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentCacheDAO.class);
	}

	final private String suffix = "appointment";

	public Appointment get(long storeId, long id) {
		return AppointmentRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
