package com.hq.chainStore.service.message.bs;

import java.util.List;

import com.hq.chainStore.service.message.apiData.MsgQueryForm;
import com.hq.chainStore.service.message.data.Message;
import com.hq.chainStore.service.message.data.MessageDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class MessageMgr {

	public static MessageMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MessageMgr.class);
	}

	public List<Message> findMessageList(MsgQueryForm queryForm) {
		final String findPath = "findMessageList";
		ReqMap reqMap = queryForm.toReqMap();
		return MessageDAO.getInstance().findWithReqParam(findPath, reqMap, 100, 1);
	}
}
