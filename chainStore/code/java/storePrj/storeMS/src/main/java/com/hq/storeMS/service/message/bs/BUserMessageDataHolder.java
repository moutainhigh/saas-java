package com.hq.storeMS.service.message.bs;

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
import com.hq.storeMS.service.message.apiData.MessageQueryForm;
import com.hq.storeMS.service.message.data.BUserMessage;
import com.hq.storeMS.service.message.data.BUserMessageDAO;
import com.hq.storeMS.service.message.data.MessageStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageDataHolder implements IntfMessageHolder {
	
	public static BUserMessageDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BUserMessageDataHolder.class);
	}
	
	final private MessageTypeEnum messageType = MessageTypeEnum.MY_MESSAGE_CENTER;
	
	public void addAndReturnId(BUserMessage target) {
		BUserMessageDAO.getInstance().addAndReturnId(target);
	}

	public void updpate(BUserMessage target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BUserMessageDAO.getInstance().updpate(target);
	}
	
	public void delete(BUserMessage target) {
		BUserMessageDAO.getInstance().delete(target.getId());
	}
	
	public BUserMessage get(long id) {
		return BUserMessageDAO.getInstance().get(id);
	}
	
	public List<BUserMessage> findBUserMessageList(MessageQueryForm queryForm) {
		return BUserMessageDAO.getInstance().findList(queryForm);
	}
	
	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		MessageResp messageResp = MessageResp.newInstance();
	    messageResp.setCount(count(MsgQueryForm.newInstance(queryForm.getStoreId(), 0)));
	    messageResp.setMessageType(getMessageType().ordinal());
		return messageResp;
	}
	
	/**
	 * 未读的消息个数
	 * 条件：
	 * 1、当前登录者 buserId
	 * 2、状态为未读
	 * 3、只筛选最近一个月的数据，超过一个月的数据不归入判断
	 * @param queryForm
	 * @return
	 */
	public long count(MsgQueryForm queryForm) {
		MessageQueryForm form = MessageQueryForm.newInstance();
		form.setBuserId(queryForm.getBuserId());
		long minTime = AppUtils.getNextDate(new Date()) - ServerConstants.ONE_MONTH;
		form.setMinTime(minTime);
		List<BUserMessage> list = findBUserMessageList(form);
		List<BUserMessage> result = filter(list);
		return (long)result.size();
	}
	
	private List<BUserMessage> filter(List<BUserMessage> list){
		List<BUserMessage> result = new ArrayList<BUserMessage>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (BUserMessage data : list) {
				if(data.getStatus() == MessageStatusEnum.Unread.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
}
