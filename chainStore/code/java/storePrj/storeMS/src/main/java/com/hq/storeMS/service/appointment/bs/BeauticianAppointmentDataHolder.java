package com.hq.storeMS.service.appointment.bs;

import com.hq.storeMS.common.message.IntfMessageHolder;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class BeauticianAppointmentDataHolder implements IntfMessageHolder {
	
	public static BeauticianAppointmentDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianAppointmentDataHolder.class);
	}
	
	final private MessageTypeEnum messageType = MessageTypeEnum.BEAUTICIAN_APPOINTMENT;
	
	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		long count = AppointmentDataHolder.getInstance().count(queryForm);
		MessageResp messageResp = MessageResp.newInstance();
	    messageResp.setCount(count);
	    messageResp.setMessageType(getMessageType().ordinal());
		return messageResp;
	}
}
