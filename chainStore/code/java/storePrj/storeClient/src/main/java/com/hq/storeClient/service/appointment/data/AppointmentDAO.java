package com.hq.storeClient.service.appointment.data;

import java.util.List;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.appointment.apiData.AppointmentQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class AppointmentDAO extends RestDao<Appointment> {

	public static AppointmentDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AppointmentDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<Appointment> getAppointmentPageInfo(String findPath, AppointmentQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Appointment.class);
	}
	
	public List<AppointmentDateGroup> findAppointmentDateGroupList(String findPath, AppointmentQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil.getInstance().parseList(restResp.gettListJson(), AppointmentDateGroup.class);
	}
}
