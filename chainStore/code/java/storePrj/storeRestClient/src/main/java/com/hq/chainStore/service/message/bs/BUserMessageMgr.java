package com.hq.chainStore.service.message.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.message.apiData.BUserMessageUpdateForm;
import com.hq.chainStore.service.message.apiData.BUserMessageUpdateStatusForm;
import com.hq.chainStore.service.message.apiData.BUserMessageUpdateType;
import com.hq.chainStore.service.message.apiData.MessageQueryForm;
import com.hq.chainStore.service.message.data.BUserMessage;
import com.hq.chainStore.service.message.data.BUserMessageDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageMgr {

	public static BUserMessageMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageMgr.class);
	}

	public PageResp<BUserMessage> findMessagePage(MessageQueryForm queryForm) {
		final String findPath = "findMessagePage";
		return BUserMessageDAO.getInstance().getPageInfo(findPath, queryForm);
	}
	
	public void updateBUserMessage(long buserMessageId, BUserMessageUpdateStatusForm updateStatusForm) {
		BUserMessageUpdateForm inputForm = BUserMessageUpdateForm.newInstance();
		inputForm.setUpdateType(BUserMessageUpdateType.UpdateState.ordinal());
		inputForm.setUpdateStatusData(updateStatusForm);
		updateBUserMessage(buserMessageId, inputForm);
	}
	
	public void updateBUserMessage(long buserMessageId, BUserMessageUpdateForm inputForm) {
		BUserMessageDAO.getInstance().update(buserMessageId, inputForm);
	}
}
