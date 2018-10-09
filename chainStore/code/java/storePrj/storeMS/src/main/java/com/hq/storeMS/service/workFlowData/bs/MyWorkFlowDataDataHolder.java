package com.hq.storeMS.service.workFlowData.bs;

import com.hq.storeMS.common.message.IntfMessageHolder;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class MyWorkFlowDataDataHolder implements IntfMessageHolder{
	
	public static MyWorkFlowDataDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MyWorkFlowDataDataHolder.class);
	}
	
	final private MessageTypeEnum messageType = MessageTypeEnum.MY_WORKFLOW;
	
	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		long count = WorkFlowDataDataHolder.getInstance().count(queryForm);
		MessageResp messageResp = MessageResp.newInstance();
	    messageResp.setCount(count);
	    messageResp.setMessageType(getMessageType().ordinal());
		return messageResp;
	}
	
	
}
