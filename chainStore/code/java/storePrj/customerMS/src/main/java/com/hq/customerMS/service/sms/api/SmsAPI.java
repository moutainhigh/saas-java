package com.hq.customerMS.service.sms.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.sms.apiData.RandomCodeAPIForm;
import com.hq.customerMS.service.sms.apiData.SmsResp;
import com.hq.customerMS.service.sms.bs.SmsHandler;

@RestController
@RequestMapping(value = "/sms")
public class SmsAPI {
	/**
	 * 发送随机生成的验证码
	 * @param apiForm
	 * @return
	 */
	@RequestMapping(value = "/sendRandomCode", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<SmsResp>> sendRandomCode(
			@RequestBody RandomCodeAPIForm apiForm) {
		ReqResult<SmsResp> result = SmsHandler.getInstance().sendRandomCode(apiForm);
		ResponseEntity<RestResp<SmsResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
