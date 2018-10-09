package com.hq.storeMS.service.appointment.bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.message.trigger.MessageTriggerMgr;
import com.hq.storeMS.common.message.trigger.TriggerForm;
import com.hq.storeMS.common.message.trigger.TriggerTypeEnum;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.appointment.apiData.AppointmentDeleteForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentUpdateInfoApiForm;
import com.hq.storeMS.service.appointment.apiData.AppointmentUpdateStatusApiForm;
import com.hq.storeMS.service.appointment.bs.wxNotice.WxNoticeSenderMgr;
import com.hq.storeMS.service.appointment.data.AppointProduct;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.appointment.data.AppointmentDateGroup;
import com.hq.storeMS.service.appointment.data.AppointmentStatusEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentMgr {

	public static AppointmentMgr getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentMgr.class);
	}
	
	private final DateFormat sdf = new SimpleDateFormat(ServerConstants.df_short);
	private final DataVersionEnum dataVersionEnum = DataVersionEnum.Appointment;

	public void addAndReturnId(Appointment target) {
		attachAppointmentInfo(target);
		AppointmentDataHolder.getInstance().addAndReturnId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
		pushMessage2UMeng(target);
	}
	
	private void attachAppointmentInfo(Appointment target) {
		long storeId = target.getStoreId();
		Leaguer leaguer = StoreLeaguerInfoMgr.getInstance().get(storeId).takeLeaguerById(target.getLeaguerId());
		if(StringUtils.isBlank(target.getName())) {
			target.setName(leaguer.getName());
		}
		if(target.getPhone() == 0) {
			target.setPhone(leaguer.getPhone());
		}
	}

	public void update(Appointment target) {
		AppointmentDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public void delete(Appointment target) {
		AppointmentDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public Appointment get(long storeId, long id) {
		return AppointmentDataHolder.getInstance().get(storeId, id);
	}
	
	public PageResp<Appointment> getAppointmentPageInfo(AppointmentQueryForm queryForm) {
		List<Appointment> list = AppointmentDataHolder.getInstance().findAppointmentList(resetQueryForm(queryForm));
		List<Appointment> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public List<Appointment> findAppointmentList(AppointmentQueryForm queryForm) {
		List<Appointment> list = AppointmentDataHolder.getInstance().findAppointmentList(resetQueryForm(queryForm));
		List<Appointment> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().getPageItemList(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public List<AppointmentDateGroup> findAppointmentDateGroupList(AppointmentQueryForm queryForm) {
		List<Appointment> list = AppointmentDataHolder.getInstance().findAppointmentList(resetQueryForm(queryForm));
		List<Appointment> resultList = filterRecord(queryForm, list);
		return buildAppointmentDateGroupList(resultList);
	}
	
	private List<AppointmentDateGroup> buildAppointmentDateGroupList(List<Appointment> list){
		Map<String, AppointmentDateGroup> result = new HashMap<String, AppointmentDateGroup>();
		for (Appointment appointment : list) {
			String yyyyMMdd = AppUtils.timeStamp2Str(appointment.getAppointTime(), sdf);
			AppointmentDateGroup group = result.get(yyyyMMdd);
			if(group == null) {
				group = AppointmentDateGroup.newInstance();
				group.setDate(yyyyMMdd);
				group.setDateTime(AppUtils.dateStr2TimeStamp(yyyyMMdd, sdf));
				result.put(yyyyMMdd, group);
			}
			group.setCount(group.getCount()+1);
		}
		
		List<AppointmentDateGroup> resultList = new ArrayList<AppointmentDateGroup>(result.values());
		Collections.sort(resultList, new Comparator<AppointmentDateGroup>() {
			@Override
			public int compare(AppointmentDateGroup o1, AppointmentDateGroup o2) {
				return Long.compare(o1.getDateTime(), o2.getDateTime());
			}
		});
		return resultList;
	}
	
	private AppointmentQueryForm resetQueryForm(AppointmentQueryForm queryForm){
		if(queryForm.getStoreId() == 0L && StringUtils.isNoneBlank(queryForm.getLeaguerId())){
			queryForm.setStoreId(Long.valueOf(queryForm.getLeaguerId().split("_")[0]));
		}
		if(queryForm.getCuserId() >0 && StringUtils.isBlank(queryForm.getLeaguerId())){
			queryForm.setLeaguerId(Leaguer.genIdByStoreId(queryForm.getStoreId(), queryForm.getCuserId()));
		}
		return queryForm;
	}
	
	private List<Appointment> filterRecord(AppointmentQueryForm queryForm, List<Appointment> list){
		List<Appointment> result = new ArrayList<Appointment>();
		if(CollectionUtils.isNotEmpty(list)){
			for (Appointment record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<Appointment>() {
			@Override
			public int compare(Appointment o1, Appointment o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(AppointmentQueryForm queryForm, Appointment record){
		if(!checkLeaguer(queryForm.getLeaguerId(), queryForm.getLeaguerName(), record)){
			return false;
		}
		
		if(!checkBUser(queryForm.getBuserId(), record)){
			return false;
		}
		
		if(!checkStatus(queryForm.getStatusSet(), record)){
			return false;
		}
		
		if(!checkOrigin(queryForm.getOrigin(), record)){
			return false;
		}
		
		return true;
	}
	
	private boolean checkLeaguer(String leaguerId, String leaguerName, Appointment record){
		if(StringUtils.isNoneBlank(leaguerId) && !leaguerId.equals(record.getLeaguerId())){
			return false;
		}
		if(StringUtils.isNoneBlank(leaguerName) && StringUtils.isNoneBlank(record.getName()) &&!record.getName().contains(leaguerName)){
			return false;
		}
		return true;
	}
	
	private boolean checkBUser(long buserId, Appointment record){
		if(buserId == 0){
			return true;
		}
		List<AppointProduct> appointProducts = record.getAppointProducts();
		for (AppointProduct appointProduct : appointProducts) {
			if(appointProduct.getBuserIds().contains(buserId)){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkStatus(Set<Integer> statusSet, Appointment record){
		if(CollectionUtils.isNotEmpty(statusSet) && !statusSet.contains(record.getStatus())){
			return false;
		}
		return true;
	}
	
	private boolean checkOrigin(int origin, Appointment record){
		if(origin > 0 && origin != record.getOrigin()){
			return false;
		}
		return true;
	}
	
	public OperateTips updateInfo(long storeId, long appointmentId, AppointmentUpdateInfoApiForm updateInfoData) {
		OperateTips operateTips = OperateTips.newInstance();
		Appointment appointment = get(storeId, appointmentId);
		if(appointment !=null){
			FastBeanCopyer.getInstance().copy(updateInfoData, appointment);
			update(appointment);
		}else{
			operateTips.setSuccess(false);
			operateTips.setTips("预约不存在，更新失败");
		}
		return operateTips;
	}

	public OperateTips updateState(long storeId, long appointmentId, AppointmentUpdateStatusApiForm updateStatusData) {
		OperateTips operateTips = OperateTips.newInstance();
		Appointment appointment = get(storeId, appointmentId);
		if(appointment !=null){
			appointment.setStatus(updateStatusData.getStatus());
			if(updateStatusData.getStatus() == AppointmentStatusEnum.CANCEL.ordinal()) {//取消预约 填写原因
				appointment.setCancelReason(updateStatusData.getCancelReason());
			}
			update(appointment);
			sendNotice2Wx(appointment);
		}else{
			operateTips.setSuccess(false);
			operateTips.setTips("预约不存在，操作失败");
		}
		return operateTips;
	}
	
	public OperateTips deleteAppoint(long storeId, long appointmentId, AppointmentDeleteForm deleteForm) {
		OperateTips operateTips = OperateTips.newInstance();
		Appointment appointment = get(storeId, appointmentId);
		if(appointment !=null){
			appointment.setEntityState(EntityState.Deleted.ordinal());
			update(appointment);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, appointment.getName(), OpLogTypeEnum.Appoint, "预约信息被删除"));
		}else{
			operateTips.setSuccess(false);
			operateTips.setTips("预约不存在，删除失败");
		}
		return operateTips;
	}
	
	/**
	 * 向微信发送通知
	 * @param appointment
	 * @return
	 */
	private void sendNotice2Wx(Appointment appointment) {
		if(isWxEndpoint(appointment)){
			AppointmentStatusEnum statusEnum = AppointmentStatusEnum.valueOf(appointment.getStatus());
			switch(statusEnum) {
			case RECEIVE:
				WxNoticeSenderMgr.getInstance().send(appointment);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 判断是否微信端的预约
	 * @param appointment
	 * @return
	 */
	private boolean isWxEndpoint(Appointment appointment) {
		return appointment != null && 
				!StringUtils.isBlank(appointment.getJsCode()) && 
				!StringUtils.isBlank(appointment.getFormId());
	}
	
	/**
	 * 向友盟推送消息
	 * @param appointment
	 * @return
	 */
	private void pushMessage2UMeng(Appointment appointment) {
		if(appointment == null || appointment.getStoreId() == 0) {
			return;
		}
		
		Set<Long> buserIds = new HashSet<Long>();
		List<AppointProduct> appointProducts = appointment.getAppointProducts();
		for (AppointProduct appointProduct : appointProducts) {
			buserIds.addAll(appointProduct.getBuserIds());
		}
		
		buserIds.remove(appointment.getCreatorId());
		if(CollectionUtils.isEmpty(buserIds)) {
			return;
		}
		
		long storeId=appointment.getStoreId();
		Store store = StoreMgr.getInstance().getSimple(storeId);
		for (Long buserId : buserIds) {
			TriggerForm data = TriggerForm.newInstance(storeId, buserId);
			data.setTriggerType(TriggerTypeEnum.NEW_MY_APPOINTMENT.ordinal());
			data.setStoreName(store.getName());
			data.setId(String.valueOf(appointment.getId()));
			MessageTriggerMgr.getInstance().triggerMessage(data);
		}
	}
}
