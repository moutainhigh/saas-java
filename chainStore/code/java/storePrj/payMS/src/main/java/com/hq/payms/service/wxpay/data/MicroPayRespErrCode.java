package com.hq.payms.service.wxpay.data;

import java.util.HashMap;
import java.util.Map;

public class MicroPayRespErrCode {
	
	private static Map<String, String> errCodeMap = new HashMap<String, String>(); 
	
	static{
		 /* 结果未知，待查询*/
		 errCodeMap.put("SYSTEMERROR", "接口返回错误 支付结果未知 系统超时 请立即调用被扫订单结果查询API，查询当前订单状态，并根据订单的状态决定下一步的操作。"); 
		 errCodeMap.put("BANKERROR", "银行系统异常 支付结果未知 银行端超时 请立即调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作");
		 errCodeMap.put("USERPAYING", "用户支付中，需要输入密码 支付结果未知 该笔交易因为业务规则要求，需要用户输入支付密码。 等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作");
		 
		 /* 确认失败 */
		 errCodeMap.put("PARAM_ERROR", "参数错误 支付确认失败 请求参数未按指引进行填写 请根据接口返回的详细信息检查您的程序"); 
		 errCodeMap.put("ORDERPAID", "订单已支付 支付确认失败 订单号重复 请确认该订单号是否重复支付，如果是新单，请使用新订单号提交 ");
		 errCodeMap.put("NOAUTH", "商户无权限 支付确认失败 商户没有开通被扫支付权限  请开通商户号权限。请联系产品或商务申请");  
		 errCodeMap.put("AUTHCODEEXPIRE", "二维码已过期，请用户在微信上刷新后再试 支付确认失败 用户的条码已经过期  请收银员提示用户，请用户在微信上刷新条码，然后请收银员重新扫码。 直接将错误展示给收银员");   
		 errCodeMap.put("NOTENOUGH", "余额不足  支付确认失败 用户的零钱余额不足  请收银员提示用户更换当前支付的卡，然后请收银员重新扫码。建议：商户系统返回给收银台的提示为“用户余额不足.提示用户换卡支付”");   
		 errCodeMap.put("NOTSUPORTCARD", "不支持卡类型 支付确认失败 用户使用卡种不支持当前支付形式 请用户重新选择卡种 建议：商户系统返回给收银台的提示为“该卡不支持当前支付，提示用户换卡支付或绑新卡支付”");   
		 errCodeMap.put("ORDERCLOSED",  "订单已关闭 支付确认失败 该订单已关 商户订单号异常，请重新下单支付");   
		 errCodeMap.put("ORDERREVERSED", "订单已撤销 支付确认失败 当前订单已经被撤销 当前订单状态为“订单已撤销”，请提示用户重新支付");  
		 errCodeMap.put("AUTH_CODE_ERROR", "授权码参数错误 支付确认失败 请求参数未按指引进行填写 每个二维码仅限使用一次，请刷新再试");   
		 errCodeMap.put("AUTH_CODE_INVALID", "授权码检验错误 支付确认失败 收银员扫描的不是微信支付的条码  请扫描微信支付被扫条码/二维码");  
		 errCodeMap.put("XML_FORMAT_ERROR", "XML格式错误 支付确认失败 XML格式错误 请检查XML参数格式是否正确");  
		 errCodeMap.put("REQUIRE_POST_METHOD", "请使用post方法 支付确认失败 未使用post传递参数 请检查请求参数是否通过post方法提交");  
		 errCodeMap.put("SIGNERROR", "签名错误 支付确认失败 参数签名结果不正确  请检查签名参数和方法是否都符合签名算法要求");  
		 errCodeMap.put("LACK_PARAMS", "缺少参数 支付确认失败 缺少必要的请求参数 请检查参数是否齐全");  
		 errCodeMap.put("NOT_UTF8", "编码格式错误 支付确认失败 未使用指定编码格式  请使用UTF-8编码格式");   
		 errCodeMap.put("BUYER_MISMATCH", "支付帐号错误 支付确认失败 暂不支持同一笔订单更换支付方 请确认支付方是否相同");  
		 errCodeMap.put("APPID_NOT_EXIST", "APPID不存在 支付确认失败 参数中缺少APPID 请检查APPID是否正确");  
		 errCodeMap.put("MCHID_NOT_EXIST", "MCHID不存在 支付确认失败 参数中缺少MCHID 请检查MCHID是否正确");  
		 errCodeMap.put("OUT_TRADE_NO_USED", "商户订单号重复 支付确认失败 同一笔交易不能多次提交 请核实商户订单号是否重复提交");  
		 errCodeMap.put("APPID_MCHID_NOT_MATCH", "appid和mch_id不匹配 支付确认失败 appid和mch_id不匹配 请确认appid和mch_id是否匹配");  
		 errCodeMap.put("INVALID_REQUEST", "无效请求 支付确认失败 商户系统异常导致，商户权限异常、重复请求支付、证书错误、频率限制等 请确认商户系统是否正常，是否具有相应支付权限，确认证书是否正确，控制频率");   
		 errCodeMap.put("TRADE_ERROR", "交易错误 支付确认失败 业务错误导致交易失败、用户账号异常、风控、规则限制等 请确认帐号是否存在异常");  
	}
	
	public static String getDesc(String key) {
		return errCodeMap.get(key);
	}

}
