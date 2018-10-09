package com.hq.payms.service.alipay.bs.handle;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.hq.payms.common.log.ConsoleLog;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePrecreateApiForm;
import com.hq.payms.service.alipay.data.TradePayReq;
import com.hq.payms.service.alipay.data.TradePrecreateReq;
import com.hq.payms.service.alipay.data.TradeRefundReq;
import com.hq.payms.service.alipayRecord.bs.AlipayRecordMgr;
import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoMgr;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.common.OperateTips;
import com.hq.payms.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.qrcode.bs.QrCodeMgr;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayConstants;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayRespHandle {

	public static AlipayRespHandle getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayRespHandle.class);
	}

	private final LogModule logModule = LogModule.Alipay;

	/**
	 * 处理扫码支付预下单的同步返回结果
	 * @param result
	 * @param reqParam
	 * @param form 
	 * @return
	 * @throws Exception
	 */
	public QrCodeResp handleAlipayF2FPrecreateResult(AlipayF2FPrecreateResult result, TradePrecreateReq reqParam, 
			TradePrecreateApiForm form) throws Exception {
		AlipayTradePrecreateResponse response = result.getResponse();
		dumpResponse(response);
		
		if (result.isTradeSuccess()) {
			//记录payRecord
			AlipayRecord payRecord = AlipayRecord.fromTradePrecreate(result,reqParam,form);
			AlipayRecordMgr.getInstance().add(payRecord);
			//生成支付二维码
			QrCodeAPIForm qrCodeForm = QrCodeAPIForm.newInstance();
			qrCodeForm.setContent(response.getQrCode());
			String qrCodeImgPath = QrCodeMgr.getInstance().genQrCode(qrCodeForm);
			if (StringUtils.isNotBlank(qrCodeImgPath)) {
				QrCodeResp qrCodeResp = QrCodeResp.newInstance();
				qrCodeResp.setImgUrl(qrCodeImgPath);
				return qrCodeResp;
			}
		}
		return null;
	}
	
	/**
	 * 处理条码支付的同步返回结果
	 * @param result
	 * @param reqParam
	 * @param form 
	 * @return
	 */
	public OperateTips handleAlipayF2FPayResult(AlipayF2FPayResult result, TradePayReq reqParam, TradePayApiForm form) {
		AlipayTradePayResponse response = result.getResponse();
		dumpResponse(response);
		OperateTips tips = OperateTips.newInstance(false);
		
		if (result.isTradeSuccess()) {
			//记录payRecord
			AlipayRecord payRecord = AlipayRecord.fromTradePay(result,reqParam,form);
			
			//同步返回了支付成功时，记录支付结果，并通知storeMS
			payRecord.setTradeStatus(ZmAlipayConstants.TS_TRADE_SUCCESS);
			payRecord.setPaySuccessTime(System.currentTimeMillis());
			payRecord.setTradeNo(response.getTradeNo());
			
			AlipayRecordMgr.getInstance().add(payRecord);
			
			//回调通知调用方微服务(异步执行，防止拖慢此接口)
			AlipayPayCallbackHelper.getInstance().asyncCallback(payRecord);
			
			tips.setSuccess(true);
		}
		return tips;
	}

	/**
	 * 打印response
	 * @param response
	 */
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			MainLog.info(logModule, "AlipayRespHandle[dumpResponse]",
					String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				MainLog.info(logModule, "AlipayRespHandle[dumpResponse]",
						String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
			}
			MainLog.info(logModule, "AlipayRespHandle[dumpResponse]", "body:" + response.getBody());
		}
	}

	public void handleNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		ConsoleLog.info(logModule, "AlipayRespHandle[handleNotify]", "支付宝支付通知结果：" + params);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		
		//是否返回的通知是未记录的订单号
		AlipayRecord payRecord = AlipayRecordMgr.getInstance().findByOutTradeNo(out_trade_no);
		if(payRecord == null) {
			MainLog.warn(logModule, "AlipayRespHandle[handleNotify]", 
					"订单号" + out_trade_no + "金额" + total_amount + "元, 返回的订单号在商户侧未查询到！");
			respSuccess(response);
			return;
		}
		
		//判断该笔订单是否在商户网站中已经做过处理
		//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
		//如果有做过处理，不执行商户的业务程序
		if (payRecord.getPaySuccessTime() > 0) {
			respSuccess(response);
			return;
		}	
		
		//签名验证
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(payRecord.getStoreId());
		boolean signVerified = AlipaySignature.rsaCheckV1(params, bossPayInfo.getAlipayZfbPublicKey(),"UTF-8", "RSA2");
		if (!signVerified) {
			MainLog.warn(logModule, "AlipayRespHandle[handleNotify]","支付宝返回的签名验证失败!");
			respFail(response);
			return;
		}
		//是否返回支付成功
		//注意：TRADE_FINISHED
		//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
		//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
		
		//注意：TRADE_SUCCESS
		//如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
		if (! (ZmAlipayConstants.TS_TRADE_FINISHED.equals(trade_status) || ZmAlipayConstants.TS_TRADE_SUCCESS.equals(trade_status)) ) {
			respFail(response);
			return;
		}
		
		//请务必判断请求时的total_amount与通知时获取的total_amount为一致的
		if (! total_amount.equals(payRecord.getTotalAmount()) ) {
			MainLog.warn(logModule, "AlipayRespHandle[handleNotify]", 
					"订单号" + out_trade_no + "金额" + total_amount + "元, 与商户侧的信息不一致！");
			respFail(response);
			return;
		}
			
		//更新payRecord
		payRecord.setTradeStatus(ZmAlipayConstants.TS_TRADE_SUCCESS);
		payRecord.setPaySuccessTime(System.currentTimeMillis());
		payRecord.setTradeNo(trade_no);
		AlipayRecordMgr.getInstance().update(payRecord);
		
		//回调通知调用方微服务
		AlipayPayCallbackHelper.getInstance().callback(payRecord);

		//处理成功
		respSuccess(response);

	}

	
	private void respSuccess(HttpServletResponse response) throws Exception {
		flushResp(response, "success");
	}
	
	private void respFail(HttpServletResponse response) throws Exception {
		flushResp(response, "fail");
	}

	/**
	 * 返回应答给支付宝后台
	 * 
	 * @param response
	 * @param myResp
	 * @throws Exception
	 */
	private void flushResp(HttpServletResponse response, String myResp) throws Exception {
		if (response == null || StringUtils.isBlank(myResp))
			return;
		PrintWriter writer = response.getWriter();
		writer.write(myResp);
		writer.flush();
	}


	public OperateTips handleAlipayF2FRefundResult(AlipayF2FRefundResult alipayF2FRefundResult, TradeRefundReq tradeRefundReq) {
		OperateTips tips = OperateTips.newInstance(false);
		if (alipayF2FRefundResult == null)
			return tips;
		if(alipayF2FRefundResult.isTradeSuccess()) {
			AlipayRecord payRecord = AlipayRecordMgr.getInstance().findByOutTradeNo(tradeRefundReq.getOutTradeNo());
			payRecord.setRefundAmount(tradeRefundReq.getOutTradeNo());
			payRecord.setOutRequestNo(tradeRefundReq.getOutRequestNo());
			AlipayRecordMgr.getInstance().update(payRecord);
			
			tips = OperateTips.newInstance(true);
		}
		return tips;
	}
}
