package com.hq.storeMS.service.message.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.message.bs.MessageHandler;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MsgQueryForm;

/**
 * 
 * ClassName: MessageAPI <br/>  
 * Function: TODO 消息API<br/>  
 *  
 * @author kevin 
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value = "/message")
public class MessageAPI {
	
	@RequestMapping(value = "/findMessageList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MessageResp>> findMessageList(
			@RequestParam("storeId") long storeId,
			@RequestParam("beauticianId") long beauticianId) {
		
		MsgQueryForm queryForm = MsgQueryForm.newInstance(storeId, beauticianId);

		ReqResult<MessageResp> result = MessageHandler.getInstance().findMessageList(queryForm);
		ResponseEntity<RestResp<MessageResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
