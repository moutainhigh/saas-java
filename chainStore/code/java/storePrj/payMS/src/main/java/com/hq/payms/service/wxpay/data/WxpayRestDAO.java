package com.hq.payms.service.wxpay.data;

import java.lang.reflect.Method;
import java.util.Map;

import com.github.wxpay.sdk.WXPay;
import com.hq.payms.common.log.ConsoleLog;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.utils.ObjToMapUtils;
import com.hq.payms.service.bossPayInfo.data.WxpayModelEnum;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayAppParam;
import com.hq.payms.zenmind.zmWxpay.provider.ZmProviderWXPayMgr;
import com.hq.payms.zenmind.zmWxpay.safeNormal.ZmSafeNormalWXPay;
import com.hq.payms.zenmind.zmWxpay.safeNormal.ZmSafeNormalWXPayMgr;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDaoException;

public class WxpayRestDAO {

	public static WxpayRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayRestDAO.class);
	}

	/**
	 * 执行支付相关方法
	 * 
	 * @param methodName
	 * @param param
	 * @param respClazz
	 * @return
	 */
	private <T> T execMethod(String methodName, CommonReq param, Class<T> respClazz) {
		T resp = null;
		WxpayModelEnum wxpayModelEnum = WxpayModelEnum.valueOf(param.getWxpayModel());
		switch(wxpayModelEnum) {
		case NORMAL:
			resp = execMethodForNormal(methodName, param, respClazz);
			break;
		case PROVIDER:
			resp = execMethodForProvider(methodName, param, respClazz);
			break;
		default:
			throw RestDaoException.newInstance("WxpayRestDAO.execMethod()", methodName, "", new RuntimeException("未知的商户模式"));
		}
		return resp;
	}

	/**
	 * 普通商户模式下执行支付相关方法
	 * 
	 * @param methodName
	 * @param param
	 * @param respClazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T execMethodForNormal(String methodName, CommonReq param, Class<T> respClazz) {
		try {
			Map<String, String> paramMap = ObjToMapUtils.objectToMapByApache(param);
			removeTempParamFromMap(paramMap);

			ZmSafeNormalWXPay normalWxpay = ZmSafeNormalWXPayMgr.getInstance().getWXPay();
			Method m = normalWxpay.getClass().getMethod(methodName, ZmWXPayAppParam.class, Map.class);
			m.setAccessible(true);
			Map<String, String> responseMap = (Map<String, String>) m.invoke(normalWxpay, buildAppParam(param), paramMap);

			ConsoleLog.info(LogModule.Wxpay, "WxpayRestDAO[execMethodForNormal]", 
					ConsoleLog.join("methodName:", methodName, " | ", "responseMap:", responseMap.toString()));

			T resp = ObjToMapUtils.mapToObjectByApache(responseMap, respClazz);
			return resp;
		} catch (Exception e) {
			throw (RestDaoException.newInstance("WxpayRestDAO.execMethodForNormal()", methodName, "", e));
		}
	}

	/**
	 * 服务商模式下执行支付相关方法
	 * 
	 * @param methodName
	 * @param param
	 * @param respClazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T execMethodForProvider(String methodName, CommonReq param, Class<T> respClazz) {
		try {
			Map<String, String> paramMap = ObjToMapUtils.objectToMapByApache(param);
			removeTempParamFromMap(paramMap);

			WXPay providerWxpay = ZmProviderWXPayMgr.getInstance().getWXPay();
			Method m = providerWxpay.getClass().getMethod(methodName, Map.class);
			m.setAccessible(true);
			Map<String, String> responseMap = (Map<String, String>) m.invoke(providerWxpay, paramMap);

			ConsoleLog.info(LogModule.Wxpay, "WxpayRestDAO[execMethodForProvider]", 
					ConsoleLog.join("methodName:", methodName, " | ", "responseMap:", responseMap.toString()));

			T resp = ObjToMapUtils.mapToObjectByApache(responseMap, respClazz);
			return resp;
		} catch (Exception e) {
			throw (RestDaoException.newInstance("WxpayRestDAO.execMethodForProvider()", methodName, "", e));
		}
	}

	/**
	 * 清除临时参数
	 * 
	 * @param paramMap
	 */
	private void removeTempParamFromMap(Map<String, String> paramMap) {
		paramMap.remove("key");
		paramMap.remove("cert_path");
		paramMap.remove("wxpayModel");
	}

	/**
	 * 普通商户模式下,切换普通商户appId等信息
	 * 
	 * @param param
	 */
	private ZmWXPayAppParam buildAppParam(CommonReq param) {
		return ZmWXPayAppParam.newInstance(param.getAppid(), param.getMch_id(), param.getKey(), param.getCert_path());
	}

	/**
	 * 扫码支付 下单
	 * 
	 * @param param
	 */
	public UnifiedOrderResp doUnifiedOrder(UnifiedOrderReq param) {
		return execMethod("unifiedOrder", param, UnifiedOrderResp.class);
	}

	/**
	 * 长链接转短链接
	 * 
	 * @param param
	 */
	public ShortUrlResp doShortUrl(ShortUrlReq param) {
		return execMethod("shortUrl", param, ShortUrlResp.class);
	}

	/**
	 * 查询订单
	 * 
	 * @param param
	 */
	public OrderQueryResp doOrderQuery(OrderQueryOrCloseReq param) {
		return execMethod("orderQuery", param, OrderQueryResp.class);
	}

	/**
	 * 刷卡支付
	 * 
	 * @param param
	 */
	public MicroPayResp doMicroPay(MicroPayReq param) {
		return execMethod("microPay", param, MicroPayResp.class);
	}

	/**
	 * 关闭订单
	 * 
	 * @param param
	 */
	public CommonResp doOrderClose(OrderQueryOrCloseReq param) {
		return execMethod("closeOrder", param, CommonResp.class);
	}

	/**
	 * 撤销订单
	 * 
	 * @param param
	 */
	public CommonResp doOrderReverse(OrderQueryOrCloseReq param) {
		return execMethod("reverse", param, CommonResp.class);
	}

	/**
	 * 退款
	 * 
	 * @param param
	 */
	public CommonResp doRefund(RefundReq param) {
		return execMethod("refund", param, CommonResp.class);

	}
	

	/******************************************** 以下方法暂未用到,未实现 ******************************************************/

	/**
	 * 查询退款
	 */
	public Map<String, String> doRefundQuery(RefundQueryReq param) {
		//"refundQuery"
		//返回结果需要特殊处理
		return null;
	}
	
	/**
	 * 对账单下载
	 * 
	 * @param param
	 */
	public Map<String, String> doDownloadBill(DownloadBillReq param) {
		//"downloadBill"
		//返回结果需要特殊处理
		return null;
	}

}
