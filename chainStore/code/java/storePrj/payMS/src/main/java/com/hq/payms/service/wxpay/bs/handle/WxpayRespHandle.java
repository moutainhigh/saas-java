package com.hq.payms.service.wxpay.bs.handle;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;
import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.common.log.ConsoleLog;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.common.utils.AESUtil;
import com.hq.payms.common.utils.XMLUtil;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoMgr;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.WxpayModelEnum;
import com.hq.payms.service.common.OperateTips;
import com.hq.payms.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.qrcode.bs.QrCodeMgr;
import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.hq.payms.service.wxpay.apiData.MiniProgramPayResp;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderForMiniProgramApiForm;
import com.hq.payms.service.wxpay.bs.WxpayMgr;
import com.hq.payms.service.wxpay.data.CommonResp;
import com.hq.payms.service.wxpay.data.MicroPayReq;
import com.hq.payms.service.wxpay.data.MicroPayResp;
import com.hq.payms.service.wxpay.data.ShortUrlReq;
import com.hq.payms.service.wxpay.data.ShortUrlResp;
import com.hq.payms.service.wxpay.data.UnifiedOrderReq;
import com.hq.payms.service.wxpay.data.UnifiedOrderResp;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConstants;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayRespHandle {

	public static WxpayRespHandle getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayRespHandle.class);
	}

	private final LogModule logModule = LogModule.Wxpay;

	/**
	 * 处理扫码支付预下单的同步返回结果
	 * @param resp
	 * @param reqParam
	 * @param form 
	 * @return
	 * @throws Exception
	 */
	public QrCodeResp handleUnifiedOrderResp(UnifiedOrderResp resp, UnifiedOrderReq reqParam, UnifiedOrderApiForm form) throws Exception {
		dumpResponse(resp);
		if (!resp.isResultSuccess()) {
			MainLog.info(logModule, "WxpayRespHandle[handleUnifiedOrderResp]", resp.toString());
			return null;
		}
		String code_url = resp.getCode_url();
		if (StringUtils.isNotBlank(code_url)) {
			//记录WxpayRecord
			WxpayRecord payRecord = WxpayRecord.fromUnifiedOrder(resp, reqParam, form);
			WxpayRecordMgr.getInstance().add(payRecord);
			
			//如果原二维码不好识别，需要将长url转换为短url
//			code_url = codeUrlToShortUrl(code_url, reqParam);
			//生成支付二维码
//			if (StringUtils.isNotBlank(code_url)) {
				QrCodeAPIForm qrCodeForm = QrCodeAPIForm.newInstance();
				qrCodeForm.setContent(code_url);
				String qrCodeImgPath = QrCodeMgr.getInstance().genQrCode(qrCodeForm);
				if (StringUtils.isNotBlank(qrCodeImgPath)) {
					QrCodeResp qrCodeResp = QrCodeResp.newInstance();
					qrCodeResp.setImgUrl(qrCodeImgPath);
					return qrCodeResp;
				}
//			}
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	private String codeUrlToShortUrl(String codeUrl, UnifiedOrderReq reqParam) {
		String shortUrl = "";
		ShortUrlReq shortUrlReq = ShortUrlReq.newInstance();
		shortUrlReq.setLong_url(codeUrl);
		shortUrlReq.setSub_mch_id(reqParam.getSub_mch_id());
		ShortUrlResp shortUrlResp = WxpayMgr.getInstance().doShortUrl(shortUrlReq);
		if (shortUrlResp.isResultSuccess()) {
			shortUrl = shortUrlResp.getShort_url();
		}
		return shortUrl;
	}
	
	public MiniProgramPayResp handleUnifiedOrderRespForMiniProgram(UnifiedOrderResp resp, UnifiedOrderReq reqParam,
			UnifiedOrderForMiniProgramApiForm form)  throws Exception {
		dumpResponse(resp);
		if (!resp.isResultSuccess()) {
			MainLog.info(logModule, "WxpayRespHandle[handleUnifiedOrderRespForMiniProgram]", resp.toString());
			return null;
		}
		if (StringUtils.isNotBlank(resp.getPrepay_id())) {
			//记录WxpayRecord
			WxpayRecord payRecord = WxpayRecord.fromUnifiedOrderForMiniProgram(resp, reqParam, form);
			WxpayRecordMgr.getInstance().add(payRecord);
			
			//生成小程序调起支付界面需要的签名数据
			String appId = form.getAppid();
			String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
		    String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  
		    String _package = "prepay_id=" + resp.getPrepay_id();
		    String signType = WXPayConstants.MD5;
		    String key = getKey(payRecord.getStoreId());
		    
		    Map<String,String> dataMap = new HashMap<String,String>();
		    dataMap.put("appId", appId);
		    dataMap.put("nonceStr", nonceStr);
		    dataMap.put("timeStamp", timeStamp);
		    dataMap.put("package", _package);
		    dataMap.put("signType", signType);
		    String paySign = WXPayUtil.generateSignature(dataMap, key, SignType.MD5);
		    
		    MiniProgramPayResp miniProgramPayResp = MiniProgramPayResp.newInstance();
		    miniProgramPayResp.setNonceStr(nonceStr);
		    miniProgramPayResp.setTimeStamp(timeStamp);
		    miniProgramPayResp.set_package(_package);
		    miniProgramPayResp.setSignType(signType);
		    miniProgramPayResp.setPaySign(paySign);
			return miniProgramPayResp;
		}
		return null;
	}
	
	/**
	 * 处理刷卡支付的同步返回结果
	 * @param resp
	 * @param reqParam
	 * @param form 
	 * @return
	 * @throws Exception
	 */
	public OperateTips handleMicroPayResp(MicroPayResp resp, MicroPayReq reqParam, MicroPayApiForm form) throws Exception {
		dumpResponse(resp);
		OperateTips tips = OperateTips.newInstance(false);
		if(resp == null) return tips;
		
		//返回成功或者支付结果未知时，才记录payRecord，否则记录无意义
		if (resp.isResultSuccess() || resp.isResultUnknown()) {
			WxpayRecord payRecord = WxpayRecord.fromMicroPay(resp, reqParam, form);
			//如果不用输入支付密码，同步返回支付成功时,记录支付结果，并通知storeMS
			if(resp.isResultSuccess()) {
				payRecord.setTrade_state(ZmWXPayConstants.TS_SUCCESS);
				payRecord.setTransaction_id(resp.getTransaction_id());
				payRecord.setPaySuccessTime(System.currentTimeMillis());
			}
			//确保添加在更新之前
			WxpayRecordMgr.getInstance().add(payRecord);
			//回调通知调用方微服务(异步执行，防止拖慢此接口)
			if(resp.isResultSuccess()) {
				WxpayPayCallbackHelper.getInstance().asyncCallback(payRecord);
			}
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	/**
	 * 打印response
	 * @param response
	 */
	private void dumpResponse(CommonResp response) {
		if (response != null) {
			MainLog.info(logModule, "WxpayRespHandle[dumpResponse]", response.toString());
		}
	}

	/**
	 * 处理微信支付结果的回调通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void handleNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultXml = getStringFromRequest(request);
		ConsoleLog.info(logModule,"WxpayRespHandle[handleNotify]", "微信支付通知结果：" + resultXml);
		Map<String, String> map = WXPayUtil.xmlToMap(resultXml);
		String return_code = map.get("return_code");
		String result_code = map.get("result_code");
		String out_trade_no = map.get("out_trade_no");
		String total_fee = map.get("total_fee");
		String transaction_id = map.get("transaction_id");
		if(StringUtils.isBlank(out_trade_no)) {
			respFail(response);
			return;
		}
		//是否返回的通知是未记录的订单号
		WxpayRecord payRecord = WxpayRecordMgr.getInstance().findByOutTradeNo(out_trade_no);
		if(payRecord == null) {
			MainLog.warn(logModule, "WxpayRespHandle[handleNotify]", "订单号" + out_trade_no + "金额" + total_fee + "分, 返回的订单号在商户侧未查询到！");
			respSuccess(response);
			return;
		}
		//签名验证
		boolean signatureValid = WXPayUtil.isSignatureValid(map, getKey(payRecord.getStoreId()));
		if (!signatureValid) {
			MainLog.warn(logModule, "WxpayRespHandle[handleNotify]","微信返回的签名验证失败!");
			respFail(response);
			return;
		}
		//是否返回支付成功
		if (!(WXPayConstants.SUCCESS.equals(return_code) && WXPayConstants.SUCCESS.equals(result_code))) {
			respFail(response);
			return;
		}
		
		//判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。
		if (payRecord.getPaySuccessTime() > 0) {
			respSuccess(response);
			return;
		}
		//校验返回的订单金额是否与商户侧的订单金额一致
		if (!total_fee.equals(payRecord.getTotal_fee())) {
			MainLog.warn(logModule, "WxpayRespHandle[handleNotify]","订单号" + out_trade_no + "金额" + total_fee + "分, 返回的订单金额与商户侧的订单金额不一致！");
			respFail(response);
			return;
		}
		
		//更新WxpayRecord
		payRecord.setTrade_state(ZmWXPayConstants.TS_SUCCESS);
		payRecord.setPaySuccessTime(System.currentTimeMillis());
		payRecord.setTransaction_id(transaction_id);
		WxpayRecordMgr.getInstance().update(payRecord);
		
		//通知storeMS
		WxpayPayCallbackHelper.getInstance().callback(payRecord);
		
		//处理成功
		respSuccess(response);
	}

	/**
	 * 获取API密钥
	 * @param storeId
	 * @return
	 */
	private String getKey(long storeId) throws Exception {
		String key = "";
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(storeId);
		WxpayModelEnum wxpayModelEnum = WxpayModelEnum.valueOf(bossPayInfo.getWxpayModel());
		switch(wxpayModelEnum) {
		case NORMAL:
			key = bossPayInfo.getWxpayKey(); //各普通商户的API密钥
			break;
		case PROVIDER:
			key = PayMSCfgMgr.getProp().getWxpayKey(); //服务商的API密钥
			break;
		default:
			break;
		}
		return key;
	}
	
	/**
	 * 从httpRequest中获取xmlString
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String getStringFromRequest(HttpServletRequest request) throws Exception {
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
		} finally {
			outSteam.close();
			inStream.close();
		}
		return new String(outSteam.toByteArray(), "utf-8");
	}

	private void respSuccess(HttpServletResponse response) throws Exception {
		String myResp = XMLUtil.createRespXML("SUCCESS", "");
		flushResp(response, myResp);
	}
	
	private void respFail(HttpServletResponse response) throws Exception {
		String myResp = XMLUtil.createRespXML("FAIL", "");
		flushResp(response, myResp);
	}
	
	/**
	 * 返回应答给微信后台
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

	public void handleDownloadBillResp(Map<String, String> respMap) {
		if(WXPayConstants.SUCCESS.equals(respMap.get("return_code"))) {
			//提取有用的数据
			String data = respMap.get("data");
//			String listStr = "";
//	        String objStr = "";
//			if(data.indexOf("总交易单数")>=0){
//				listStr =  data.substring(0, data.indexOf("总交易单数"));
//				objStr =  data.substring(data.indexOf("总交易单数"));
//			}
			String newStr = data.replaceAll(",", " "); // 去空格
	        String[] tempStr = newStr.split("`"); // 数据分组
	        String[] t = tempStr[0].split(" ");// 分组标题
	        int k = 1; // 记录数组下标
	        int j = tempStr.length / t.length; // 计算循环次数
	        for (int i = 0; i < j; i++) {
	            for (int l = 0; l < t.length; l++) {
	                //如果是最后列且是最后一行数据时，去除数据里的汉字
	                if((i == j - 1) && (l == t.length -1)){
	                    String repickStr = clearChineseChar(tempStr[l + k]);
	                    System.out.println(t[l] + ":" + repickStr);
	                }else{
	                    System.out.println(t[l] + ":" + tempStr[l + k]);
	                }
	            }
	            k = k + t.length;
	        }
		}
	}
	
	/**
	 * 清除汉字
	 * @param sourceStr
	 * @return
	 */
	private String clearChineseChar(String sourceStr) {
		String reg = "[\u4e00-\u9fa5]";//汉字的正则表达式
        Pattern pat = Pattern.compile(reg);  
        Matcher mat = pat.matcher(sourceStr); 
        return mat.replaceAll("");
	}

	public void handleRefundNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultXml = getStringFromRequest(request);
		ConsoleLog.info(logModule, "WxpayRespHandle[handleRefundNotify]", "微信退款通知结果：" + resultXml);
		Map<String, String> map = WXPayUtil.xmlToMap(resultXml);
		// 签名验证
//		boolean signatureValid = WXPayUtil.isSignatureValid(map, PayMSCfgMgr.getProp().getWxpayKey());
//		if (!signatureValid) {
//			respFail(response);
//			return;
//		}
		//是否返回状态成功
		if ( !(WXPayConstants.SUCCESS.equals(map.get("return_code")) && WXPayConstants.SUCCESS.equals(map.get("result_code")))) {
			respFail(response);
			return;
		}
		
		String reqInfo = map.get("req_info");//获取加密数据 
		//解密数据(多商户多API密钥时，无法解密)
		String decryptReqInfo = AESUtil.decryptData(reqInfo, PayMSCfgMgr.getProp().getWxpayKey());
		Map<String, String> reqInfoMap = WXPayUtil.xmlToMap(decryptReqInfo);
		
		//是否返回退款成功
		if(!WXPayConstants.SUCCESS.equals(reqInfoMap.get("refund_status"))) {
			respFail(response);
			return;
		}
		
		//是否返回的通知是未记录的订单号
		WxpayRecord payRecord = WxpayRecordMgr.getInstance().findByOutTradeNo(reqInfoMap.get("out_trade_no"));
		if(payRecord == null) {
			MainLog.warn(logModule, "WxpayRespHandle[handleRefundNotify]", 
					"订单号" + reqInfoMap.get("out_trade_no") + "金额" + reqInfoMap.get("total_fee") + "分, 退款结果通知返回的订单号在商户侧未查询到！");
			respSuccess(response);
			return;
		}
		//判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。
		if (payRecord.getRefundSuccessTime() > 0) {
			respSuccess(response);
			return;
		}
		//校验返回的订单金额和退款金额是否与商户侧的订单金额一致
		if ( !reqInfoMap.get("total_fee").equals(payRecord.getTotal_fee()) 
				|| !reqInfoMap.get("refund_fee").equals(payRecord.getRefund_fee()) ) {
			MainLog.warn(logModule, "WxpayRespHandle[handleNotify]",
					"订单号" + reqInfoMap.get("out_trade_no") + "金额" + reqInfoMap.get("total_fee") + "分, 退款金额" + reqInfoMap.get("refund_fee") + "分, 返回的信息与商户侧的订单金额不一致！");
			respFail(response);
			return;
		}
		
		//更新WxpayRecord
		payRecord.setRefundSuccessTime(System.currentTimeMillis());
		WxpayRecordMgr.getInstance().update(payRecord);
		// TODO 通知storeMS

		//处理成功
		MainLog.info(logModule, "WxpayRespHandle[handleNotify]",
				"订单号" + map.get("out_trade_no") + "金额" + map.get("total_fee") + "分, 退款金额" + map.get("refund_fee") + "分, 退款成功！");
		respSuccess(response);
		
	}

}
