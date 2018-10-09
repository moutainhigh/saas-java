package com.hq.payms.service.pay.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RestResp;
import com.hq.payms.service.pay.apiData.BeScanPayApiForm;
import com.hq.payms.service.pay.apiData.MiniProgramPayApiForm;
import com.hq.payms.service.pay.apiData.PayQueryApiForm;
import com.hq.payms.service.pay.apiData.PayQueryResp;
import com.hq.payms.service.pay.apiData.ScanPayApiForm;
import com.hq.payms.service.pay.bs.PayHandler;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.wxpay.apiData.MiniProgramPayResp;


/**
 * 支付<br>
 * 此模块仅对alipay和wxpay这两个模块要用到的方法进行一层封装
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/pay")
public class PayAPI {

	// @Autowired
	// HttpServletRequest request;

	/**
	 * 商家被扫码，生成支付二维码
	 * 
	 * @param form
	 * @return 支付二维码
	 */
	@RequestMapping(value = "/beScan", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<QrCodeResp>> beScan(@RequestBody BeScanPayApiForm form,
			HttpServletRequest request) {
		ReqResult<QrCodeResp> result = PayHandler.getInstance().beScan(form, request.getRemoteAddr());
		ResponseEntity<RestResp<QrCodeResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 商家主动扫码
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/scan", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Void>> scan(@RequestBody ScanPayApiForm form,
			HttpServletRequest request) {
		ReqResult<Void> result = PayHandler.getInstance().scan(form, request.getRemoteAddr());
		ResponseEntity<RestResp<Void>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 小程序支付-预下单
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/miniProgramPay", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<MiniProgramPayResp>> miniProgramPay(@RequestBody MiniProgramPayApiForm form,
			HttpServletRequest request) {
		ReqResult<MiniProgramPayResp> result = PayHandler.getInstance().miniProgramPay(form, request.getRemoteAddr());
		ResponseEntity<RestResp<MiniProgramPayResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 查询订单是否支付成功
	 * @param outTradeNo
	 * @param apiType
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PayQueryResp>> query(
			@RequestParam(value = "apiType", required = true) int apiType,
			@RequestParam(value = "outTradeNo", required = true) String outTradeNo) {
		PayQueryApiForm form = PayQueryApiForm.newInstance(apiType, outTradeNo);
		ReqResult<PayQueryResp> result = PayHandler.getInstance().query(form);
		ResponseEntity<RestResp<PayQueryResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
