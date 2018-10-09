package com.hq.customerRestClient.service.appointment.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class AppointmentDAO extends RestDao<Appointment> {

	public static AppointmentDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AppointmentDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
}
