package com.hq.customerMS.service.appointment.data;

import com.hq.storeClient.service.appointment.data.Appointment;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class AppointmentRedisDAO extends RedisDao<Appointment> {

	public static AppointmentRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentRedisDAO.class);
	}

}
