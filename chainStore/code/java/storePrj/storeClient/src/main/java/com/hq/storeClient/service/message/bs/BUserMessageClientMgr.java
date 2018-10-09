package com.hq.storeClient.service.message.bs;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.message.apiData.BUserMessageAddListForm;
import com.hq.storeClient.service.message.apiData.BUserMessageBatchUpdateStatusForm;
import com.hq.storeClient.service.message.apiData.BUserMessageUpdateForm;
import com.hq.storeClient.service.message.apiData.BUserMessageUpdateStatusForm;
import com.hq.storeClient.service.message.apiData.BUserMessageUpdateType;
import com.hq.storeClient.service.message.apiData.MessageQueryForm;
import com.hq.storeClient.service.message.data.BUserMessage;
import com.hq.storeClient.service.message.data.BUserMessageDAO;
import com.hq.storeClient.service.message.data.MsgUnReadCount;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageClientMgr {

	public static BUserMessageClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageClientMgr.class);
	}

	public BUserMessage update(long id, BUserMessageUpdateForm updateForm) {
		return BUserMessageDAO.getInstance().updateWithResp(id, updateForm);
	}
	
	public BUserMessage updateState(long id, BUserMessageUpdateStatusForm inputForm) {
		BUserMessageUpdateForm updateForm = BUserMessageUpdateForm.newInstance();
		updateForm.setUpdateType(BUserMessageUpdateType.UpdateState.ordinal());
		updateForm.setUpdateStatusData(inputForm);
		return update(id, updateForm);
	}
	
	public BUserMessage batchUpdateState(long id, BUserMessageBatchUpdateStatusForm inputForm) {
		BUserMessageUpdateForm updateForm = BUserMessageUpdateForm.newInstance();
		updateForm.setUpdateType(BUserMessageUpdateType.BatchUpdateState.ordinal());
		updateForm.setBatchUpdateStatusForm(inputForm);
		return update(id, updateForm);
	}
	
	public BUserMessage addBUserMessage(BUserMessageAddListForm inputForm) {
		return BUserMessageDAO.getInstance().add(inputForm);
	}
	
	public PageResp<BUserMessage> findPage(MessageQueryForm queryForm) {
		final String findPath="findMessagePage"; 
		return BUserMessageDAO.getInstance().findPage(findPath, queryForm);
	}
	
	public MsgUnReadCount findUnReadCount(MessageQueryForm queryForm) {
		final String findPath="findUnReadCount"; 
		return BUserMessageDAO.getInstance().findUnReadCount(findPath, queryForm);
	}
}
