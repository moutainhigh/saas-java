package com.hq.payms.service.alipay.data;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;
import com.hq.payms.common.utils.JsonStyleUtil;
import com.hq.payms.zenmind.zmAlipay.safeNormal.ZmSafeNoramlAlipayMgr;
import com.hq.payms.zenmind.zmAlipay.safeNormal.ZmSafeNormalAlipayTradeServiceImplAdapter;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDaoException;

public class AlipayRestDAO {

	public static AlipayRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayRestDAO.class);
	}

	private ZmSafeNormalAlipayTradeServiceImplAdapter getTradeService() {
		return ZmSafeNoramlAlipayMgr.getInstance().getTradeService();
	}
	
	private ZmAlipayAppParam buildAppParam(CommonReq param) {
		//设置商户信息
		return ZmAlipayAppParam.newInstance(param.getAppId(), param.getPrivateKey(), param.getAlipayPublicKey());
	}
	private ExtendParams buildExtendParams(CommonReq param) {
		// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId(param.getSysServiceProviderId());
		return extendParams;
	}
	
	/**
	 * 当面付2.0生成支付二维码
	 * @param param
	 * @return
	 */
	public AlipayF2FPrecreateResult doTradePrecreate(TradePrecreateReq param) {
		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
				.setNotifyUrl(param.getNotifyUrl())
				.setSubject(param.getSubject()).setTotalAmount(param.getTotalAmount())
				.setOutTradeNo(param.getOutTradeNo()).setUndiscountableAmount(param.getUndiscountableAmount())
				.setSellerId(param.getSellerId()).setBody(param.getBody()).setOperatorId(param.getOperatorId())
				.setStoreId(param.getStoreId()).setExtendParams(buildExtendParams(param))
				.setTimeoutExpress(param.getTimeoutExpress()).setNotifyUrl(param.getNotifyUrl())
				.setGoodsDetailList(param.getGoodsDetailList());

		AlipayF2FPrecreateResult result = getTradeService().tradePrecreate(buildAppParam(param), builder);
		return result;
	}

	/**
	 * 当面付2.0支付
	 * @param param
	 * @return
	 */
	public AlipayF2FPayResult doTradePay(TradePayReq param) {
		// 创建条码支付请求builder，设置请求参数
		AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
				// .setAppAuthToken(param.getAppAuthToken())
				.setNotifyUrl(param.getNotifyUrl())
				.setOutTradeNo(param.getOutTradeNo()).setSubject(param.getSubject()).setAuthCode(param.getAuthCode())
				.setTotalAmount(param.getTotalAmount()).setStoreId(param.getStoreId())
				.setUndiscountableAmount(param.getUndiscountableAmount()).setBody(param.getBody())
				.setOperatorId(param.getOperatorId()).setExtendParams(buildExtendParams(param)).setSellerId(param.getSellerId())
				.setGoodsDetailList(param.getGoodsDetailList()).setTimeoutExpress(param.getTimeoutExpress());

		// 调用tradePay方法获取当面付应答
		AlipayF2FPayResult result = getTradeService().tradePay(buildAppParam(param), builder);
		return result;
	}

	/**
	 * 当面付2.0查询订单
	 * @param param
	 * @return
	 */
	public AlipayF2FQueryResult doTradeQuery(TradeQueryReq param) {
		String outTradeNo = param.getOutTradeNo();

		// 创建查询请求builder，设置请求参数
		AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder().setOutTradeNo(outTradeNo);

		AlipayF2FQueryResult result = getTradeService().queryTradeResult(buildAppParam(param), builder);
		return result;
	}

	/**
	 * 当面付2.0退款
	 * @param param
	 * @return
	 */
	public AlipayF2FRefundResult doTradeRefund(TradeRefundReq param) {

		// 创建退款请求builder，设置请求参数
		AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
				.setOutTradeNo(param.getOutTradeNo()).setRefundAmount(param.getRefundAmount())
				.setRefundReason(param.getRefundReason()).setOutRequestNo(param.getOutRequestNo())
				.setStoreId(param.getStoreId());

		AlipayF2FRefundResult result = getTradeService().tradeRefund(buildAppParam(param), builder);
		return result;
	}

	/**
	 * 电脑网站支付; 统一收单下单并支付页面接口
	 * @param param
	 * @return
	 */
	public String doTradePagePay(TradePagePayReq param) {
		String formHtml = "";
		try {
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
			alipayRequest.setReturnUrl(param.getReturnUrl());
			alipayRequest.setNotifyUrl(param.getNotifyUrl());// 在公共参数中设置回跳和通知地址
			
			param.setExtendParams(buildExtendParams(param));
			
			String paramJson = JsonStyleUtil.write2SnakeCase(param);
			alipayRequest.setBizContent(paramJson); // 填充业务参数
			
			formHtml = getTradeService().tradePagePay(buildAppParam(param), alipayRequest);
		} catch (Exception e) {
			throw (RestDaoException.newInstance("AlipayRestDAO.doTradePagePay()", "", "", e));
		}
		return formHtml;
	}
	
}
