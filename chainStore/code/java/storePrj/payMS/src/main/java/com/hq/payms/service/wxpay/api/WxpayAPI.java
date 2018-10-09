package com.hq.payms.service.wxpay.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RestResp;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.hq.payms.service.wxpay.apiData.RefundApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderForMiniProgramApiForm;
import com.hq.payms.service.wxpay.apiData.MiniProgramPayResp;
import com.hq.payms.service.wxpay.bs.WxpayHandler;
import com.hq.payms.service.wxpay.data.CommonResp;
import com.hq.payms.service.wxpay.data.OrderQueryResp;


/**
 * 微信支付 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/wxpay")
public class WxpayAPI {

	/**
	 * 扫码支付 下单
	 * 
	 * @param form
	 * @return 支付二维码
	 */
	@RequestMapping(value = "/doUnifiedOrder", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<QrCodeResp>> doUnifiedOrder(@RequestBody UnifiedOrderApiForm form,
			HttpServletRequest request) {
		ReqResult<QrCodeResp> result = WxpayHandler.getInstance().doUnifiedOrder(form, request.getRemoteAddr());
		ResponseEntity<RestResp<QrCodeResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 小程序支付 下单
	 * 
	 * @param form
	 * @return 支付二维码
	 */
	@RequestMapping(value = "/doUnifiedOrderForMiniProgram", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<MiniProgramPayResp>> doUnifiedOrderForMiniProgram(@RequestBody UnifiedOrderForMiniProgramApiForm form,
			HttpServletRequest request) {
		ReqResult<MiniProgramPayResp> result = WxpayHandler.getInstance().doUnifiedOrderForMiniProgram(form, request.getRemoteAddr());
		ResponseEntity<RestResp<MiniProgramPayResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 刷卡支付
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/doMicroPay", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Void>> doMicroPay(@RequestBody MicroPayApiForm form,
			HttpServletRequest request) {
		ReqResult<Void> result = WxpayHandler.getInstance().doMicroPay(form, request.getRemoteAddr());
		ResponseEntity<RestResp<Void>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/********************************** 共用 **************************************/
	
	/**
	 * 接收微信支付结果通知
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/receiveNotify")
	public void receiveNotify(HttpServletRequest request, HttpServletResponse response) {
		WxpayHandler.getInstance().receiveNotify(request, response);
	}
	
	/**
	 * 撤销订单
	 * 
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping(value = "/doOrderReverse", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<CommonResp>> doOrderReverse(
			@RequestParam(value="storeId",required=false, defaultValue="-1") long storeId,
			@RequestParam(value = "out_trade_no", required = true) String out_trade_no) {
		ReqResult<CommonResp> result = WxpayHandler.getInstance().doOrderReverse(storeId, out_trade_no);
		ResponseEntity<RestResp<CommonResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 关闭订单
	 * 
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping(value = "/doOrderClose", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<CommonResp>> doOrderClose(
			@RequestParam(value="storeId",required=false, defaultValue="-1") long storeId,
			@RequestParam(value = "out_trade_no", required = true) String out_trade_no) {
		ReqResult<CommonResp> result = WxpayHandler.getInstance().doOrderClose(storeId, out_trade_no);
		ResponseEntity<RestResp<CommonResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 查询订单
	 * 
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping(value = "/doOrderQuery", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderQueryResp>> findOrderByOutTradeNo(
			@RequestParam(value="storeId",required=false, defaultValue="-1") long storeId,
			@RequestParam(value = "out_trade_no", required = true) String out_trade_no) {
		ReqResult<OrderQueryResp> result = WxpayHandler.getInstance().doOrderQuery(storeId, out_trade_no);
		ResponseEntity<RestResp<OrderQueryResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 下载对账单
	 * 
	 * @param bill_date
	 *            下载对账单的日期，格式：20140603
	 * @param bill_type
	 *            ALL，返回当日所有订单信息，默认值 SUCCESS，返回当日成功支付的订单 REFUND，返回当日退款订单
	 *            RECHARGE_REFUND，返回当日充值退款订单
	 * @return
	 */
	@RequestMapping(value = "/doDownloadBill", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<String>> doDownloadBill(@RequestParam(value="bill_date",required=true)String bill_date,
			@RequestParam(value="bill_type",required=true)String bill_type) {
		ReqResult<String> result = WxpayHandler.getInstance().doDownloadBill(bill_date, bill_type);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
     * 退款
     * @param form
     * @return
     */
    @RequestMapping(value = "/doRefund", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<CommonResp>> doRefund(@RequestBody RefundApiForm form) {
		ReqResult<CommonResp> result = WxpayHandler.getInstance().doRefund(form);
		ResponseEntity<RestResp<CommonResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

    /**
	 * 接收微信退款结果通知
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/receiveRefundNotify")
	public void receiveRefundNotify(HttpServletRequest request, HttpServletResponse response) {
		WxpayHandler.getInstance().receiveRefundNotify(request, response);
	}
}
