package com.hq.testClass.robot.message;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.message.data.Message;

public class MessageRobot {

	private MessageRobotData data;

	public static MessageRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static MessageRobot newInstance(int mark) {
		MessageRobot robot = new MessageRobot();
		robot.data = MessageRobotData.newInstance(mark);
		return robot;
	}

	public List<Message> findMessageList(long storeId, long beauticianId) {
		return MessageRobotHelper.getInstance().findMessageList(this, storeId,
				beauticianId);
	}

	public MessageRobotData getData() {
		return data;
	}

	public void setData(MessageRobotData data) {
		this.data = data;
	}
}
