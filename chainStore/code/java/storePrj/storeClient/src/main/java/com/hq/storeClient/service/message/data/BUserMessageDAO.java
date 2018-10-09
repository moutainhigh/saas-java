package com.hq.storeClient.service.message.data;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.hq.storeClient.service.message.apiData.MessageQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class BUserMessageDAO extends RestDao<BUserMessage> {

	public static BUserMessageDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserMessageDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public PageResp<BUserMessage> findPage(String findPath, MessageQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), BUserMessage.class);
	}
	
	public MsgUnReadCount findUnReadCount(String findPath, MessageQueryForm queryForm) {
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), MsgUnReadCount.class);
	}
	
	
}
