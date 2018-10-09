package com.hq.storeMS.service.appointment.bs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.message.IntfMessageHolder;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeMS.service.appointment.data.AppointProduct;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.appointment.data.AppointmentCacheDAO;
import com.hq.storeMS.service.appointment.data.AppointmentDAO;
import com.hq.storeMS.service.appointment.data.AppointmentStatusEnum;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointmentDataHolder implements IntfMessageHolder {
	
	public static AppointmentDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(AppointmentDataHolder.class);
	}
	
	final private MessageTypeEnum messageType = MessageTypeEnum.APPOINTMENT_MNG;
	
	public void addAndReturnId(Appointment target) {
		AppointmentDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		AppointmentCacheDAO.getInstance().delete(target);
	}

	public void updpate(Appointment target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		AppointmentDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		AppointmentCacheDAO.getInstance().delete(target);
	}
	
	public void delete(Appointment target) {
		AppointmentDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		AppointmentCacheDAO.getInstance().delete(target);
	}
	
	public Appointment get(long storeId, long id) {
		Appointment target = AppointmentCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = AppointmentDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				AppointmentCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<Appointment> findAppointmentList(AppointmentQueryForm queryForm) {
		List<Appointment> list = AppointmentCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = AppointmentDAO.getInstance().findAppointmentList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				AppointmentCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
	
	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		MessageResp messageResp = MessageResp.newInstance();
		//预约管理 不过滤buserId
	    messageResp.setCount(count(MsgQueryForm.newInstance(queryForm.getStoreId(), 0)));
	    messageResp.setMessageType(getMessageType().ordinal());
		return messageResp;
	}
	
	/**
	 * 待接收的预约个数
	 * 条件：
	 * 1、当前登录者 buserId
	 * 2、预约状态为New
	 * 3、只筛选最近一个月的数据，超过一个月的数据不归入判断
	 * @param queryForm
	 * @return
	 */
	public long count(MsgQueryForm queryForm) {
		AppointmentQueryForm form = AppointmentQueryForm.newInstance();
		form.setStoreId(queryForm.getStoreId());
		long minTime = AppUtils.getNextDate(new Date()) - ServerConstants.ONE_MONTH;
		form.setMinTime(minTime);
		List<Appointment> list = findAppointmentList(form);
		List<Appointment> result = filter(list, queryForm.getBuserId());
		return (long)result.size();
	}
	
	private List<Appointment> filter(List<Appointment> list, long buserId){
		List<Appointment> result = new ArrayList<Appointment>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Appointment data : list) {
				if(checkCond(buserId, data)) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private boolean checkCond(long buserId, Appointment record){
		if(record.getStatus() != AppointmentStatusEnum.NEW.ordinal() && record.getStatus() != AppointmentStatusEnum.RECEIVE.ordinal()) {
			return false;
		}
		if(buserId == 0) {
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
}
