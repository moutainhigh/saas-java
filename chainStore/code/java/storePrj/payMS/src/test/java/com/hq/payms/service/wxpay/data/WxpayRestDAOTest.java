package com.hq.payms.service.wxpay.data;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alipay.demo.trade.utils.ZxingUtils;
import com.hq.payms.common.config.PayMSCfg;
import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.hq.payms.zenmind.zmWxpay.provider.ZmProviderWXPayMgr;

public class WxpayRestDAOTest {
	
	private static PayMSCfg payMSCfg = new PayMSCfg();

	@BeforeClass
	public static void initEnv() {
		payMSCfg.setWxpayAppId("wx3a6b565bd78c5df2");
//		payMSCfg.setWxpayMchId("1505586621"); //智美时代作为服务商时的mchId
		payMSCfg.setWxpayMchId("1499313512"); //智美时代作为普通商户时的mchId
		payMSCfg.setWxpayKey("ZHIMEITIMESzhimeitong2018zhimeit"); //API密钥,用于生成签名
//		payMSCfg.setWxpayCertPath("E:/honkonDoc/honkon/cert/provider/apiclient_cert.p12"); //智美时代作为服务商时的商户证书
		payMSCfg.setWxpayCertPath("E:/honkonDoc/honkon/cert/normal/apiclient_cert.p12"); //智美时代作为普通商户时的商户证书
		
		payMSCfg.setWxpayNotifyUrl("https://www.zhimeitimes.com/payms/wxpay/receiveNotify"); //支付结果通知url
		payMSCfg.setWxpayRefundNotifyUrl(""); //退款结果通知url
		
		payMSCfg.setWxpayUseSandbox(false); //是否沙箱环境
		payMSCfg.setWxpayHttpConnectTimeoutMs(10000);
		payMSCfg.setWxpayHttpReadTimeoutMs(30000);
		payMSCfg.setWxpayOrderQueryNThread(4); //订单支付结果查询线程数
		
		ZmWXPayCfg zmWXPayCfg = payMSCfg.getZmWXPayCfg();
		ZmProviderWXPayMgr.getInstance().init(zmWXPayCfg);
		PayMSCfgMgr.setCfg(payMSCfg);
	}

	@Test
	public void testDoUnifiedOrder() {
		UnifiedOrderReq param = UnifiedOrderReq.newInstance();
		param.setNotify_url(payMSCfg.getWxpayNotifyUrl());
		param.setSpbill_create_ip("61.140.183.134");
		param.setBody("智美通-购买消费");
		param.setTotal_fee("1"); //1分
		param.setOut_trade_no("out_trade_no_10001");
		param.setProduct_id("product_id_20001");
		
		UnifiedOrderResp resp = WxpayRestDAO.getInstance().doUnifiedOrder(param);
		if(resp.isResultSuccess()) {
			String code_url = resp.getCode_url(); //经测试此code_url可以正常识别，不用再转成short_url
			String filePath = String.format("E:/honkonDoc/honkon/img/wx-qr-%s.png", param.getOut_trade_no());
			log.info("filePath:" + filePath);
			ZxingUtils.getQRCodeImge(code_url, 256, filePath);
		}
	}

	@Test
	public void testDoOrderQuery() {
		OrderQueryOrCloseReq param = OrderQueryOrCloseReq.newInstance();
//		param.setOut_trade_no("out_trade_no_10001");
		param.setOut_trade_no("out_trade_no_10002");
		
		OrderQueryResp resp = WxpayRestDAO.getInstance().doOrderQuery(param);
		Assert.assertTrue(resp.isTradeSuccess()); //支付成功
	}

	@Test
	public void testDoMicroPay() {
		MicroPayReq param = MicroPayReq.newInstance();
		param.setSpbill_create_ip("61.140.183.134");
		param.setBody("智美通-会员充值");
		param.setTotal_fee("1"); //1分
		param.setOut_trade_no("out_trade_no_10002");
		param.setAuth_code(""); //客户的付款码
		
		MicroPayResp resp = WxpayRestDAO.getInstance().doMicroPay(param);
		Assert.assertTrue(resp.isResultSuccess() || resp.isResultUnknown());
	}
	
	private static Log log = LogFactory.getLog(WxpayRestDAOTest.class);
	
	
	@Test
	public void test() {
	    long millisecond = 1531361305582L;
	    Date date = new Date(millisecond);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss SSS a");
	    System.out.println("毫秒对应日期时间字符串：" + format.format(date));
	}

}
