package com.hq.chainStore.service.storeLeaguerInfo.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hq.chainStore.service.appointment.bs.AppointmentMgr;
import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.appointment.data.AppointmentQueryParams;
import com.hq.chainStore.service.common.ClientConstants;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.bs.StoreOrderMgr;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.AddAttention;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerDelApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerLabelAddForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerLabelRemoveForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerLabelUpdateForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerUpdateInfoApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.RechargeMemberCardForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.RemoveAttention;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.SaveFollowUserForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.ServiceTypeEnum;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerService;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoMgr {

	public static StoreLeaguerInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoMgr.class);
	}

	public StoreLeaguerInfo findSimpleStoreInfo(long storeId) {
		return StoreLeaguerInfoDAO.getInstance().getSimple(storeId);
	}
	
	@Deprecated
	public StoreLeaguerInfo getStoreLeaguerInfo(long storeId) {
		return StoreLeaguerInfoDAO.getInstance().getDetail(storeId);
	}
	
	public void updateStoreLeaguerInfo(long storeId, StoreLeaguerInfoUpdateApiForm updateForm) {
		StoreLeaguerInfoDAO.getInstance().update(storeId, updateForm);
	}
	
	/***********************Leaguer*************************/
	public List<StoreLeaguerService> findLeaguerServiceList(long minTime, long maxTime, String leaguerId, int pageItemCount, int pageNo) {
		List<StoreLeaguerService> list = new ArrayList<StoreLeaguerService>();
		
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setLeaguerId(leaguerId);
		params.setMaxTime(maxTime);
		params.setMinTime(minTime);
		params.setPageItemCount(pageItemCount);
		params.setPageNo(pageNo);
		List<Order> orders = StoreOrderMgr.getInstance().findOrderList(params);
		for (Order order : orders) {
			StoreLeaguerService serv = StoreLeaguerService.newInstance();
			serv.setType(ServiceTypeEnum.ORDER.ordinal());
			serv.setOrder(order);
			serv.setCreateTime(order.getCreatedTime());
			list.add(serv);
		}
		
		String[] ids = leaguerId.split(ClientConstants.JOIN_SYMBOL);
		AppointmentQueryParams appointmentQueryParams = AppointmentQueryParams.newInstance();
		appointmentQueryParams.setLeaguerId(leaguerId);
		appointmentQueryParams.setMaxTime(maxTime);
		appointmentQueryParams.setMinTime(minTime);
		appointmentQueryParams.setPageItemCount(pageItemCount);
		appointmentQueryParams.setPageNo(pageNo);
		appointmentQueryParams.setStoreId(Long.valueOf(ids[0]));
		List<Appointment> appointments = AppointmentMgr.getInstance().findLeaguerAppointmentList(appointmentQueryParams);
		
		for (Appointment appointment : appointments) {
			StoreLeaguerService serv = StoreLeaguerService.newInstance();
			serv.setType(ServiceTypeEnum.APPOINTMENT.ordinal());
			serv.setAppointment(appointment);
			serv.setCreateTime(appointment.getAppointTime());
			list.add(serv);
		}

		Collections.sort(list, new Comparator<StoreLeaguerService>() {
			public int compare(StoreLeaguerService arg0, StoreLeaguerService arg1) {
				return arg0.getCreateTime().compareTo(arg1.getCreateTime());
			}
		});
		
		return list;
	}
	
	public List<Leaguer> getLeaguerList(long storeId) {
		StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoDAO.getInstance().get(storeId);
		return storeLeaguerInfo.getLeaguerInfoList();
	}

	public Leaguer getLeaguer(String leaguerId) {
		long storeId = Long.valueOf(leaguerId.split("_")[0]);
		return StoreLeaguerInfoDAO.getInstance().get(storeId).getLeaguerInfoMap().get(leaguerId);
	}
	
	public void updateLeaguerInfo(long storeId, LeaguerUpdateInfoApiForm data){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.UpdateLeaguerInfo.ordinal());
		updateForm.setLeaguerUpdateInfoData(data);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void addLeaguerInfo(long storeId, LeaguerAddApiForm data){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddLeaguerInfo.ordinal());
		updateForm.setLeaguerAddInfoData(data);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void delLeaguerInfo(long storeId, LeaguerDelApiForm leaguerDelInfoData){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.DelLeaguer.ordinal());
		updateForm.setLeaguerDelInfoData(leaguerDelInfoData);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void addLeaguerInfoList(long storeId, List<LeaguerAddApiForm> datas){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddLeaguerInfoList.ordinal());
		updateForm.setLeaguerAddInfoDataList(datas);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void addListOfLeaguerIds(long storeId, String leaguerIds){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddListOfLeaguerIds.ordinal());
		updateForm.setLeaguerIds(leaguerIds);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void addAttention(long storeId, AddAttention data){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddAttention.ordinal());
		updateForm.setAddAttention(data);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void removeAttention(long storeId, RemoveAttention data){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.RemoveAttention.ordinal());
		updateForm.setRemoveAttention(data);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	

	/**************客户修改 新增接口 充值、设置会员卡、购买次卡、划卡（消费次卡）***************************/
	public void rechargeMemberCard(long storeId, RechargeMemberCardForm data){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.RechargeMemberCard.ordinal());
		updateForm.setRechargeMemberCardForm(data);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void updateMemberCard(long storeId, UpdateMemberCardForm upMemberCardForm){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.UpdateMemberCard.ordinal());
		updateForm.setUpdateMemberCardForm(upMemberCardForm);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	/***************************客户标签操作***************************/
	public void addLeaguerLabel(long storeId, LeaguerLabelAddForm inputForm){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddLeaguerLabel.ordinal());
		updateForm.setLeaguerLabelAddForm(inputForm);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void removeLeaguerLabel(long storeId, LeaguerLabelRemoveForm inputForm){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.RemoveLeaguerLabel.ordinal());
		updateForm.setLeaguerLabelRemoveForm(inputForm);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void updateLeaguerLabel(long storeId, LeaguerLabelUpdateForm inputForm){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.UpdateLeaguerLabel.ordinal());
		updateForm.setLeaguerLabelUpdateForm(inputForm);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void addLeaguerLabels(long storeId, List<LeaguerLabelAddForm> inputForm){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddLeaguerLabelList.ordinal());
		updateForm.setLeaguerLabelAddForms(inputForm);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
	
	public void saveFollowUser(long storeId, SaveFollowUserForm inputForm){
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.SaveFollowUser.ordinal());
		updateForm.setSaveFollowUserForm(inputForm);
		updateStoreLeaguerInfo(storeId, updateForm);
	}
}
