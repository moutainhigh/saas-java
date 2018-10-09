package com.hq.storeMS.service.appointment.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentCacheDAO {

	public static AppointmentCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentCacheDAO.class);
	}

	final private String suffix = "appointment";

	public void saveList(AppointmentQueryForm queryForm, List<Appointment> list) {
		AppointmentRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<Appointment> getList(AppointmentQueryForm queryForm) {
		return AppointmentRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(Appointment target) {
		AppointmentRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public Appointment get(long storeId, long id) {
		return AppointmentRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(Appointment target) {
		AppointmentRedisDAO.getInstance().delete(target.getId());
		AppointmentRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
