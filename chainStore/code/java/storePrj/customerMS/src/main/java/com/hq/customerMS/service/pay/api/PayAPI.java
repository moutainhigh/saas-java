package com.hq.customerMS.service.pay.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.pay.bs.PayHandler;
import com.hq.storeClient.service.pay.apiData.BeScanApiForm;
import com.hq.storeClient.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeClient.service.pay.apiData.ScanApiForm;
import com.hq.storeClient.service.pay.data.MiniProgramPayResp;
import com.hq.storeClient.service.pay.data.PayResp;


@RestController
@RequestMapping(value="pay")
public class PayAPI {
	
	/**
	 * 商家被扫码，生成支付二维码
	 * 
	 * @param form
	 * @return 支付二维码
	 */
	@RequestMapping(value = "/beScan", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<PayResp>> beScan(@RequestBody BeScanApiForm form) {
		ReqResult<PayResp> result = PayHandler.getInstance().beScan(form);
		ResponseEntity<RestResp<PayResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 商家主动扫码
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/scan", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<PayResp>> scan(@RequestBody ScanApiForm form) {
		ReqResult<PayResp> result = PayHandler.getInstance().scan(form);
		ResponseEntity<RestResp<PayResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 小程序支付-预下单
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/miniProgramPay", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<MiniProgramPayResp>> miniProgramPay(@RequestBody MiniProgramApiForm form) {
		ReqResult<MiniProgramPayResp> result = PayHandler.getInstance().miniProgramPay(form);
		ResponseEntity<RestResp<MiniProgramPayResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
