package com.hq.storeMS.service.message.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.message.apiData.BUserMessageAddListForm;
import com.hq.storeMS.service.message.apiData.BUserMessageUpdateForm;
import com.hq.storeMS.service.message.apiData.MessageQueryForm;
import com.hq.storeMS.service.message.bs.BUserMessageHandler;
import com.hq.storeMS.service.message.data.BUserMessage;
import com.hq.storeMS.service.message.data.MsgUnReadCount;

@RestController
@RequestMapping(value = "/buserMessage")
public class BUserMessageAPI {

	@RequestMapping(value = "/findUnReadCount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MsgUnReadCount>> findUnReadCount(
			@RequestParam(value = "buserId", defaultValue = "0") long buserId) {
		MessageQueryForm queryForm = MessageQueryForm.newInstance();
		queryForm.setBuserId(buserId);
		ReqResult<MsgUnReadCount> result = BUserMessageHandler.getInstance().findUnReadCount(queryForm);
		ResponseEntity<RestResp<MsgUnReadCount>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findMessagePage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(@RequestParam(value = "status", defaultValue = "-1") int status,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "messageType", defaultValue = "") Set<Integer> messageType,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		MessageQueryForm queryForm = MessageQueryForm.newInstance();
		queryForm.setStatus(status).setMaxTime(maxTime).setMinTime(minTime).setBuserId(buserId)
		.setMessageType(messageType).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = BUserMessageHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{messageId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BUserMessage>> updateMessage(@PathVariable("messageId") long messageId,
			@RequestBody BUserMessageUpdateForm inputForm) {
		ReqResult<BUserMessage> result = BUserMessageHandler.getInstance().updateMessage(messageId, inputForm);
		ResponseEntity<RestResp<BUserMessage>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUserMessage>> addMessage(@RequestBody BUserMessageAddListForm inputForm) {
		ReqResult<BUserMessage> result = BUserMessageHandler.getInstance().addMessage(inputForm);
		ResponseEntity<RestResp<BUserMessage>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
