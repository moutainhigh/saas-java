package com.hq.testClass.robot.message;

import java.util.List;

import com.hq.chainStore.service.message.apiData.MsgQueryForm;
import com.hq.chainStore.service.message.bs.MessageMgr;
import com.hq.chainStore.service.message.data.Message;

public class MessageRobotHelper {

	private static MessageRobotHelper instance = new MessageRobotHelper();

	public static MessageRobotHelper getInstance() {
		return instance;
	}

	public List<Message> findMessageList(MessageRobot robot, long storeId, long beauticianId) {
		MsgQueryForm queryForm = MsgQueryForm.newInstance();
		queryForm.setStoreId(storeId).setBeauticianId(beauticianId);
		return MessageMgr.getInstance().findMessageList(queryForm);
	}
}
