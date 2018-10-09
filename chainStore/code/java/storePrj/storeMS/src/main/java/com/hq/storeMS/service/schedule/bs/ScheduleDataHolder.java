package com.hq.storeMS.service.schedule.bs;

import java.util.List;

import com.hq.storeMS.common.message.IntfMessageHolder;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.hq.storeMS.service.schedule.apiData.ScheduleQueryForm;
import com.hq.storeMS.service.schedule.data.Schedule;
import com.hq.storeMS.service.schedule.data.ScheduleDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ScheduleDataHolder implements IntfMessageHolder {
	
	public static ScheduleDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ScheduleDataHolder.class);
	}
	
	final private MessageTypeEnum messageType = MessageTypeEnum.SCHEDULE;
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(Schedule target) {
		ScheduleDAO.getInstance().addAndReturnId(target);
	}

	public void updpate(Schedule target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ScheduleDAO.getInstance().updpate(target);
	}
	
	public void delete(long id) {
		ScheduleDAO.getInstance().delete(id);
	}
	
	public Schedule get(long id) {
		return ScheduleDAO.getInstance().get(id);
	}
	
	public List<Schedule> findScheduleList(ScheduleQueryForm queryForm) {
		return ScheduleDAO.getInstance().findScheduleList(queryForm);
	}
	
	
	
	/******************消息**********************/
	public long count(MsgQueryForm queryForm) {
		return ScheduleDAO.getInstance().count(queryForm);
	}
	
	public MessageTypeEnum getMessageType() {
		return messageType;
	}
	

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		MessageResp messageResp = MessageResp.newInstance();
	    messageResp.setCount(count(queryForm));
	    messageResp.setMessageType(getMessageType().ordinal());
		return messageResp;
	}
}
