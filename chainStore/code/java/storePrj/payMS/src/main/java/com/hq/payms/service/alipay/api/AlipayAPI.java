package com.hq.payms.service.alipay.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.hq.payms.service.alipay.apiData.TradePagePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePrecreateApiForm;
import com.hq.payms.service.alipay.apiData.TradeQueryApiForm;
import com.hq.payms.service.alipay.apiData.TradeRefundApiForm;
import com.hq.payms.service.alipay.bs.AlipayHandler;
import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RestResp;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
/**
 * 支付宝支付
 * 
 * @author: wujunwei
 * @version: v1.0
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/alipay" )
public class AlipayAPI {
	
	/**
	 * 扫码支付  下单
	 * @param form
	 * @return 支付二维码
	 */
	@RequestMapping(value = "/doTradePrecreate" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<QrCodeResp>> doTradePrecreate(@RequestBody TradePrecreateApiForm form) {  
		ReqResult<QrCodeResp> result = AlipayHandler.getInstance().doTradePrecreate(form);
		ResponseEntity<RestResp<QrCodeResp>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
    /**
	 * 条码支付
	 * @param form
	 * @return 
	 */
	@RequestMapping(value = "/doTradePay" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<Void>> doTradePay(@RequestBody TradePayApiForm form) {  
		ReqResult<Void> result = AlipayHandler.getInstance().doTradePay(form);
		ResponseEntity<RestResp<Void>> respEntity = result.buildRespEntity();
		return respEntity;
    }
    
	/**
	 * 电脑网站支付
	 * @param form
	 * @return 支付宝支付页面的html
	 */
	@RequestMapping(value = "/doTradePagePay" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<String>> doTradePagePay(@RequestBody TradePagePayApiForm form) {  
		ReqResult<String> result = AlipayHandler.getInstance().doTradePagePay(form);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
    }
    
    /********************************** 共用 **************************************/
	
	/**
	 * 接收支付宝支付结果通知
	 * @param request
	 * @param response
	 */
    @RequestMapping(value = "/receiveNotify")
    public void receiveNotify(HttpServletRequest request, HttpServletResponse response){
    	AlipayHandler.getInstance().receiveNotify(request, response);
    }
    
    /**
     * 查询订单
     * @param out_trade_no
     * @return
     */
    @RequestMapping(value = "/doTradeQuery", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<AlipayF2FQueryResult>> doTradeQuery(
			@RequestParam(value="outTradeNo",required=true)String outTradeNo) {
    	TradeQueryApiForm form = TradeQueryApiForm.newInstance();
    	form.setOutTradeNo(outTradeNo);
		ReqResult<AlipayF2FQueryResult> result = AlipayHandler.getInstance().doTradeQuery(form);
		ResponseEntity<RestResp<AlipayF2FQueryResult>> respEntity = result.buildRespEntity();
		return respEntity;
	}
    
    /**
	 * 退款
	 * @param form
	 * @return 
	 */
	@RequestMapping(value = "/doTradeRefund" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<AlipayF2FRefundResult>> doTradeRefund(@RequestBody TradeRefundApiForm form) {  
		ReqResult<AlipayF2FRefundResult> result = AlipayHandler.getInstance().doTradeRefund(form);
		ResponseEntity<RestResp<AlipayF2FRefundResult>> respEntity = result.buildRespEntity();
		return respEntity;
    }
    
}
