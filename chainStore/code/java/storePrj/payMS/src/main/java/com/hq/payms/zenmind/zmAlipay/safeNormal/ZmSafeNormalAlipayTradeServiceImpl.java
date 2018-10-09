package com.hq.payms.zenmind.zmAlipay.safeNormal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Constants;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;

/**
 * 支持向多个商户付款<br>
 * 需配置多个商户appid和私钥和支付宝公钥(可放到数据库)，然后动态更换去调用支付接口，即可实现多账号收款<br>
 *
 * 关于安全性：<br>
 * 每次调用支付方法，都将不同的client作为参数传入，而不是切换共享的client的属性，是线程安全的;<br>
 * 
 * @see https://openclub.alipay.com/read.php?tid=2123&fid=56<br>
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmSafeNormalAlipayTradeServiceImpl extends ZmSafeNormalAbsAlipayTradeService{
	
	/**
	 * 当面付<br>
	 * 可以直接使用的pay方法
	 * @param client
	 * @param builder
	 * @return
	 */
    public AlipayF2FPayResult tradePay(AlipayClient client, AlipayTradePayRequestBuilder builder) {
        validateBuilder(builder);

        final String outTradeNo = builder.getOutTradeNo();

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        // 设置平台参数
        request.setNotifyUrl(builder.getNotifyUrl());
        String appAuthToken = builder.getAppAuthToken();
        // todo 等支付宝sdk升级公共参数后使用如下方法
        // request.setAppAuthToken(appAuthToken);
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());

        // 设置业务参数
        request.setBizContent(builder.toJsonString());
        log.info("trade.pay bizContent:" + request.getBizContent());

        // 首先调用支付api
        AlipayTradePayResponse response = (AlipayTradePayResponse) getResponse(client, request);

        AlipayF2FPayResult result = new AlipayF2FPayResult(response);
        if (response != null && Constants.SUCCESS.equals(response.getCode())) {
            // 支付交易明确成功
            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (response != null && Constants.PAYING.equals(response.getCode())) {
            // 返回用户处理中，则轮询查询交易是否成功，如果查询超时，则调用撤销
            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                                                            .setAppAuthToken(appAuthToken)
                                                            .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse loopQueryResponse = loopQueryResult(client, queryBuiler);
            return checkQueryAndCancel(client, outTradeNo, appAuthToken, result, loopQueryResponse);

        } else if (tradeError(response)) {
            // 系统错误，则查询一次交易，如果交易没有支付成功，则调用撤销
            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                                                            .setAppAuthToken(appAuthToken)
                                                            .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse queryResponse = tradeQuery(client, queryBuiler);
            return checkQueryAndCancel(client, outTradeNo, appAuthToken, result, queryResponse);

        } else {
            // 其他情况表明该订单支付明确失败
            result.setTradeStatus(TradeStatus.FAILED);
        }

        return result;
    }
    
    /**
     * 电脑网站付<br>
     * 可以直接使用的pay方法
     * @param alipayRequest
     * @return
     */
    public String tradePagePay(AlipayClient client, AlipayTradePagePayRequest alipayRequest) {
        try {
        	String formHtml = client.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        	return formHtml;
        } catch (AlipayApiException e) {
        	throw new RuntimeException(e);
        }
    }

}
