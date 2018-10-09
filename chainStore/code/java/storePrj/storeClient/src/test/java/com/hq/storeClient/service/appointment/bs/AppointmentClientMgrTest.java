package com.hq.storeClient.service.appointment.bs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeClient.service.appointment.data.Appointment;
import com.hq.storeClient.service.appointment.data.AppointmentDateGroup;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class AppointmentClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetAppointmentPageInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setLeaguerId("16052_67194");
		PageResp<Appointment> page = AppointmentClientMgr.getInstance().getAppointmentPageInfo(queryForm);
		List<Appointment> list = page.getList();
		for (Appointment appointment : list) {
			System.out.println(JsonUtil.getInstance().toJson(appointment));
		}
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testFindAppointmentDateGroupList() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		AppointmentQueryForm queryForm = AppointmentQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setLeaguerId("16052_67194");
		List<AppointmentDateGroup> dataGroups = AppointmentClientMgr.getInstance().findAppointmentDateGroupList(queryForm);
		for (AppointmentDateGroup appointment : dataGroups) {
			System.out.println(JsonUtil.getInstance().toJson(appointment));
		}
	}

	@Test
	public void testGetAppointment() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		long appointmentId = 5218L;
		Appointment appointment = AppointmentClientMgr.getInstance().getAppointment(storeId, appointmentId);
		System.out.println(JsonUtil.getInstance().toJson(appointment));
	}

	@Test
	public void testDeleteAppointment() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		long appointmentId = 5171L;
		AppointmentClientMgr.getInstance().deleteAppointment(storeId, appointmentId);
	}

	@Test
	public void testUpdateAppointment() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAppointment() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAppointmentForCuser() {
		fail("Not yet implemented");
	}

}
