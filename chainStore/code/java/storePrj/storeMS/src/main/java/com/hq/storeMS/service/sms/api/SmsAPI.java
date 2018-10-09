package com.hq.storeMS.service.sms.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.sms.apiData.RandomCodeAPIForm;
import com.hq.storeMS.service.sms.apiData.SmsAPIForm;
import com.hq.storeMS.service.sms.apiData.SmsResp;
import com.hq.storeMS.service.sms.bs.SmsHandler;

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
	public ResponseEntity<RestResp<SmsResp>> sendSms(@RequestBody SmsAPIForm apiForm) {
		ReqResult<SmsResp> result = SmsHandler.getInstance().sendSms(apiForm);
		ResponseEntity<RestResp<SmsResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 发送随机生成的验证码
	 * 
	 * @param apiForm
	 * @return
	 */
	@RequestMapping(value = "/sendRandomCode", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<SmsResp>> sendRandomCode(@RequestBody RandomCodeAPIForm apiForm) {
		ReqResult<SmsResp> result = SmsHandler.getInstance().sendRandomCode(apiForm);
		ResponseEntity<RestResp<SmsResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
