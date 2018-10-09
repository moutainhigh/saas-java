package com.hq.thirdPartyServer.service.sms.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.thirdPartyServer.service.common.ReqResult;
import com.hq.thirdPartyServer.service.common.RestResp;
import com.hq.thirdPartyServer.service.sms.apiData.SmsAPIForm;
import com.hq.thirdPartyServer.service.sms.bs.SmsHandler;
import com.hq.thirdPartyServer.service.sms.data.SmsResp;

/**
 * 
 * ClassName: SmsAPI <br/>
 * Function: TODO 发短信API <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */

@RestController
@RequestMapping(value = "/sms")
public class SmsAPI {
	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<SmsResp>> sendSms(
			@RequestBody SmsAPIForm apiForm) {
		ReqResult<SmsResp> result = SmsHandler.getInstance().sendSms(apiForm);
		ResponseEntity<RestResp<SmsResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/sendSmsByYunPian", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<SmsResp>> sendSmsByYunPian(
			@RequestBody SmsAPIForm apiForm) {
		ReqResult<SmsResp> result = SmsHandler.getInstance().sendSmsByYunPian(apiForm);
		ResponseEntity<RestResp<SmsResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
