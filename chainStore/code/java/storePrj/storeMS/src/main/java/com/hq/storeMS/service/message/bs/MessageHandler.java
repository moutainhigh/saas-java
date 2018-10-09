package com.hq.storeMS.service.message.bs;

import java.util.List;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.message.MsMesaageMgr;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class MessageHandler {

	public static MessageHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MessageHandler.class);
	}

	public ReqResult<MessageResp> findMessageList(MsgQueryForm queryForm) {
		ReqResult<MessageResp> result = ReqResult.newInstance(false, MessageResp.class);

		try {
			List<MessageResp> message = MsMesaageMgr.getInstance().getAllMessageResp(queryForm);
			if (message != null) {
				result.setTargetList(message);
				result.setSuccess(true);
			} else {
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Message, "MessageHandler[findMessageList]", reason, e);
		}

		return result;
	}
}
