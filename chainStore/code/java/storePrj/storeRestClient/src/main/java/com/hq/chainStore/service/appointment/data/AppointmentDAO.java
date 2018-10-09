package com.hq.chainStore.service.appointment.data;

import java.util.List;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class AppointmentDAO extends RestDao<Appointment> {

	public static AppointmentDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AppointmentDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<Appointment> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
	}

	public PageResp<Appointment> getAppointmentPageInfo(String findPath, AppointmentQueryParams params) {
		try {
			RestResp restResp = super.rawGetReq(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Appointment.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("AppointmentDAO.getAppointmentPageInfo()", "appointment", "", e));
		}
	}
	
	public List<AppointmentDateGroup> findAppointmentDateGroupList(String findPath, AppointmentQueryParams queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil.getInstance().parseList(restResp.gettListJson(), AppointmentDateGroup.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("AppointmentDAO.findAppointmentDateGroupList()", "appointment", "", e));
		}
	}
}
