package com.hq.storeMS.common.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.hq.storeMS.service.appointment.bs.AppointmentDataHolder;
import com.hq.storeMS.service.appointment.bs.BeauticianAppointmentDataHolder;
import com.hq.storeMS.service.message.bs.BUserMessageDataHolder;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoDataHolder;
import com.hq.storeMS.service.workFlowData.bs.MyWorkFlowDataDataHolder;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class MsMesaageMgr {
	
	public static MsMesaageMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MsMesaageMgr.class);
	}
	
	private Map<MessageTypeEnum, IntfMessageHolder> holderMap = new HashMap<MessageTypeEnum, IntfMessageHolder>();
	
	public MsMesaageMgr(){
		
		holderMap.put(AppointmentDataHolder.getInstance().getMessageType(), new IntfMessageHolder(){
			@Override
			public MessageResp getMessageItem(MsgQueryForm queryForm) {
				return AppointmentDataHolder.getInstance().getMessageItem(queryForm);
			}
		});
		
		holderMap.put(BeauticianAppointmentDataHolder.getInstance().getMessageType(), new IntfMessageHolder(){
			@Override
			public MessageResp getMessageItem(MsgQueryForm queryForm) {
				return BeauticianAppointmentDataHolder.getInstance().getMessageItem(queryForm);
			}
		});
		
		holderMap.put(StoreClerkInfoDataHolder.getInstance().getMessageType(), new IntfMessageHolder(){
			@Override
			public MessageResp getMessageItem(MsgQueryForm queryForm) {
				return StoreClerkInfoDataHolder.getInstance().getMessageItem(queryForm);
			}
		});
		
		holderMap.put(WorkFlowDataDataHolder.getInstance().getMessageType(), new IntfMessageHolder(){
			@Override
			public MessageResp getMessageItem(MsgQueryForm queryForm) {
				return WorkFlowDataDataHolder.getInstance().getMessageItem(queryForm);
			}
		});
		
		holderMap.put(MyWorkFlowDataDataHolder.getInstance().getMessageType(), new IntfMessageHolder(){
			@Override
			public MessageResp getMessageItem(MsgQueryForm queryForm) {
				return MyWorkFlowDataDataHolder.getInstance().getMessageItem(queryForm);
			}
		});
		
		holderMap.put(BUserMessageDataHolder.getInstance().getMessageType(), new IntfMessageHolder(){
			@Override
			public MessageResp getMessageItem(MsgQueryForm queryForm) {
				return BUserMessageDataHolder.getInstance().getMessageItem(queryForm);
			}
		});
	} 
	
	public MessageResp getMessageResp(MessageTypeEnum messageTypeEnum, MsgQueryForm queryForm){
		IntfMessageHolder messageHolder = holderMap.get(messageTypeEnum);
		MessageResp messageResp = null;
		if(messageHolder != null){
			messageResp = messageHolder.getMessageItem(queryForm);
		}
		return messageResp;
	}
	
	public List<MessageResp> getMessageRespList(List<MessageTypeEnum> messageTypeList, MsgQueryForm queryForm){
		List<MessageResp> list = new ArrayList<MessageResp>();
		for (MessageTypeEnum messageTypeEnum : messageTypeList) {
			MessageResp messageResp = getMessageResp(messageTypeEnum, queryForm);
			if(messageResp != null){
				list.add(messageResp);
			}
		}
		return list;
	}
	
	public List<MessageResp> getAllMessageResp(MsgQueryForm queryForm){
		return getMessageRespList(Arrays.asList(MessageTypeEnum.values()), queryForm);
	}
	

}
