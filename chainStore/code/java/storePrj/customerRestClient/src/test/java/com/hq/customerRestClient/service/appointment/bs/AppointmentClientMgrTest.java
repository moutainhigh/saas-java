package com.hq.customerRestClient.service.appointment.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.appointment.data.Appointment;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class AppointmentClientMgrTest {
			
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		long appointmentId = 5218L;
		Appointment appointment = AppointmentClientMgr.getInstance().getAppointment(storeId, appointmentId);
		System.out.println(JsonUtil.getInstance().toJson(appointment));
	}
}
