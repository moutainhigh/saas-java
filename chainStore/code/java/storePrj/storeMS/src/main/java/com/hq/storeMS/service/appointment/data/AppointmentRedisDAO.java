package com.hq.storeMS.service.appointment.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class AppointmentRedisDAO extends RedisDao<Appointment> {

	public static AppointmentRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentRedisDAO.class);
	}

	final private String suffix = "appointment";

	public void saveAppointmentList(AppointmentQueryForm queryForm, List<Appointment> list) {
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();

		super.saveList(list, groupName, listId);
	}

	public List<Appointment> getAppointmentList(AppointmentQueryForm queryForm) {
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();

		return super.getList(groupName, listId);
	}

	public void deleteAppointment(Appointment target) {
		super.delete(target.getId());
		super.deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object id) {
		return AppUtils.joinByUnderline(suffix, id);
	}

}
