package com.hq.payms.service.alipay.data;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.utils.Utils;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.hq.payms.common.config.PayMSCfg;
import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.zenmind.zmAlipay.normal.ZmNoramlAlipayMgr;

public class AlipayRestDAOTest {

	private static PayMSCfg payMSCfg = new PayMSCfg();

	@BeforeClass
	public static void initEnv() {
		payMSCfg.setAlipayOpenApiDomain("https://openapi.alipay.com/gateway.do"); // 支付宝openapi域名
		payMSCfg.setAlipayMcloudApiDomain("http://mcloudmonitor.com/gateway.do"); // 支付宝mcloudmonitor域名

		payMSCfg.setAlipayPid(""); // 商户partner id
		payMSCfg.setAlipayAppid("2018011901981208"); // 商户应用id

		payMSCfg.setAlipayPrivateKey(""); // RSA私钥，用于对商户请求报文加签
		payMSCfg.setAlipayDevPublicKey(
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxVLs0t3lxxNGhKI8s8/PIkjOSbgWEF6rshzlJFVBm9wL1ILheNMVS8ly0yXP7Msi17KBSeQMrrz/RhImHr52b25yJnsi94SnfGuQvuC4tSsE7sZfnPASszEV3potDpz6Cvixxv61LiATuQuoAUtAQ5B4C5YDIMobLfXTEx3DTZ8i8dmEQpnqUghZuA/HOc3wxxvyNUSwjJ1NZnZZU11Z0LWiLrqoGX+o0vvEoC2RLCq66v6vbF2d48uMvunVaYhwfPpUaaeCMgPsmIjkmtWWkSXKlJNvBnrKhnVZpLx0LFMPq3KitrGPV51retV7MgXGJxdOVQUdUwz3tLTI96/whwIDAQAB"); // RSA公钥，仅用于验证开发者网关
		payMSCfg.setAlipayZfbPublicKey(
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApd8GG+3f9Ev9VI739at5szklghj3TEHven4mDQnoPvai+t9elSJ2iIp00E8y2oTszY3ACJC64OtdEO8HYARuIomGZQ7O9+ZINus38Qj8O9GIplAyylsoVnJAPevXry9qmEzr5iIfSiKGUxU39Ehpvg+aAWzrkHYEZDtIXs6lnKqx+vh8/cIg+lgKQ2z2fYGZAG4CFuVc9l8zbGMLiDWNPMkI6rNGJB6eyzrDeLidtfXfmRddMkCvyIfUCo3f1bRz1m73aki4a+ElPwgNODUKKQGRCyub5ek+G/x2Jpj6ehj8XGrx3pXrDRYQvwK6ld59wjPpp3wMuBNGw8pTLUNd1QIDAQAB"); // 支付宝RSA公钥，用于验签支付宝应答
		payMSCfg.setAlipaySignType("RSA2"); // 签名类型

		payMSCfg.setAlipayMaxQueryRetry(5); // 最大查询次数
		payMSCfg.setAlipayQueryDuration(5000); // 查询间隔（毫秒）

		payMSCfg.setAlipayMaxCancelRetry(3); // 最大撤销次数
		payMSCfg.setAlipayCancelDuration(2000); // 撤销间隔（毫秒）

		payMSCfg.setAlipayHeartbeatDelay(5); // 交易保障线程第一次调度延迟（秒）
		payMSCfg.setAlipayHeartbeatDuration(900); // 交易保障线程调度间隔（秒）

		payMSCfg.setAlipayNotifyUrl(""); // 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
		payMSCfg.setAlipayTimeoutExpress(1440);
		
		payMSCfg.setAlipayHttpConnectTimeoutMs(10000);
		payMSCfg.setAlipayHttpReadTimeoutMs(30000);

		ZmNoramlAlipayMgr.getInstance().init(payMSCfg.getZmAlipayCfg());
		PayMSCfgMgr.setCfg(payMSCfg);
	}

	private void fillCommonReq(CommonReq param) {
		param.setAppId("2018011901981208");
		param.setPrivateKey("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDFUuzS3eXHE0aEojyzz88iSM5JuBYQXquyHOUkVUGb3AvUguF40xVLyXLTJc/syyLXsoFJ5AyuvP9GEiYevnZvbnImeyL3hKd8a5C+4Li1KwTuxl+c8BKzMRXemi0OnPoK+LHG/rUuIBO5C6gBS0BDkHgLlgMgyhst9dMTHcNNnyLx2YRCmepSCFm4D8c5zfDHG/I1RLCMnU1mdllTXVnQtaIuuqgZf6jS+8SgLZEsKrrq/q9sXZ3jy4y+6dVpiHB8+lRpp4IyA+yYiOSa1ZaRJcqUk28GesqGdVmkvHQsUw+rcqK2sY9XnWt61XsyBcYnF05VBR1TDPe0tMj3r/CHAgMBAAECggEBALgiHeTYNGeE0TVLv6F7tiqkDfw00EIOVeIrnJg/Bq5OtKtmRsjdEO5b2c63ZkPte2IUrRkqubsFmqDkprXhvdtWESdfmM2o+DHenn/AbByDPGMksmxcita0Y1gKPyl3ys0aLasoXCHixQTUn741Xo6t7ZfCMMEUWDCXSJR6HZn71Bvn3g9eN6M55cCc4Z0mn71E9nCXkt9/PBrR3ZXcI1I+X46bLnyxvz2vW2yRHtqK1s3dO+VGgS9niXHIRsuw4dWIj4fJSM1JS758wcRacNvctnsqXlcapm7RepunhUX6hbH/3O6hTRgC4SbTyoC9eLPCLzGhx8mMbvyMvRfdayECgYEA+4UkCTSi0UruLbS/sHdfgWEFifBLhgCQh9dVL48dA0gYek92xwDddCvGZcWDkmTRzkfbEW88cKexsAdWGPtbNx5Cl6LNsD0wU7ez13TSPQdLGFSt4s4579WD9yRfOeGABKmZah1z+C22vIiVTy9pimgzfvM9UppAP77F2ffrZzUCgYEAyNaqWrmXObTD5wRSvex5iGGhc4PSdMcDf0aRu1c07iy3Io9muBctB+6gtkq/HKbKMTwYk0/z0s8PhwyHfmTNI+klyqxCBBgUrVLbck+9CU7bvclpgFvY2bbA41NUmcK6v6xp7YH9j/VO4HfUs/P54HxAz5387n1w6J1kUcDIZEsCgYEAu2TLIICy/jRRmIQefZ+y7HYY5li+LCyngk4RDcoW16cTda1W1ZXzaOZkftbV3HB5ULOAqFC0O0r1vCz7HMnJgBtvigKmNTShmP45Y8GKEiYo8QvGMkFsMSBnne7fdARqmQ6zWrhu+u00ROuOMt0kSRaxp1gazDxYuiWsy2VHYjECgYAoKEQHv/Qs5agGGScyMoZabqsJneIG14QrfsDE5LsApJA5qNKxHHE0lNItoOR1piPnlEyVp/bov94q8qZk72LVO1kDHU5V4zq/LUITqT3zDoGBgTRHJhZF8fS/f3n7BKSyjpbflo5MqpAK8erl0HK2ICIVYVGhnhNlb5MPhayL4QKBgQDDyTu38W5jaxbwP93q1FGeaIOPERSi/EgwfEKb/eOQGeQrIkkcsjkSfotXnjw4nujIvKvUqEXGniqngKUBOITtPZFfF7oKd3yVBms/E3OqWovLiVDaXtRr+c16hHhiP6xO3x30BDvRkkLqNqkvPvYPwk1q08giBAv0ZuAPmHSGDw==");
		param.setAlipayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApd8GG+3f9Ev9VI739at5szklghj3TEHven4mDQnoPvai+t9elSJ2iIp00E8y2oTszY3ACJC64OtdEO8HYARuIomGZQ7O9+ZINus38Qj8O9GIplAyylsoVnJAPevXry9qmEzr5iIfSiKGUxU39Ehpvg+aAWzrkHYEZDtIXs6lnKqx+vh8/cIg+lgKQ2z2fYGZAG4CFuVc9l8zbGMLiDWNPMkI6rNGJB6eyzrDeLidtfXfmRddMkCvyIfUCo3f1bRz1m73aki4a+ElPwgNODUKKQGRCyub5ek+G/x2Jpj6ehj8XGrx3pXrDRYQvwK6ld59wjPpp3wMuBNGw8pTLUNd1QIDAQAB");
		param.setSysServiceProviderId("2088821855394690");
	}

	@Test
	public void testDoTradePrecreate() {
		TradePrecreateReq param = TradePrecreateReq.newInstance();
		fillCommonReq(param);
		String outTradeNo = "tradeprecreate15305872129865562426";
		param.setOutTradeNo(outTradeNo);
		param.setSubject("智美通101门店当面付扫码消费");
		param.setTotalAmount("0.01"); // 1分
		
		AlipayF2FPrecreateResult result = AlipayRestDAO.getInstance().doTradePrecreate(param);
//		Assert.assertTrue(result.getTradeStatus() == TradeStatus.SUCCESS);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			log.info("支付宝预下单成功: )");
			AlipayTradePrecreateResponse response = result.getResponse();
			dumpResponse(response);
			// 需要修改为运行机器上的路径
			String filePath = String.format("E:/honkonDoc/honkon/img/qr-%s.png", response.getOutTradeNo());
			log.info("filePath:" + filePath);
			ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
			break;
		case FAILED:
			log.error("支付宝预下单失败!!!");
			break;
		case UNKNOWN:
			log.error("系统异常，预下单状态未知!!!");
			break;
		default:
			log.error("不支持的交易状态，交易返回异常!!!");
			break;
		}

	}

	@Test
	public void testDoTradePay() {
		TradePayReq param = TradePayReq.newInstance();
		fillCommonReq(param);
		String outTradeNo = "tradeprecreate15305872129865562427";
		param.setOutTradeNo(outTradeNo);
		param.setSubject("智美通101门店当面付条码消费");
		param.setTotalAmount("0.01"); // 1分
		param.setAuthCode("");//客户付款码
		
		AlipayF2FPayResult result = AlipayRestDAO.getInstance().doTradePay(param);
		switch (result.getTradeStatus()) {
        case SUCCESS:
            log.info("支付宝支付成功: )");
            break;

        case FAILED:
            log.error("支付宝支付失败!!!");
            break;

        case UNKNOWN:
            log.error("系统异常，订单状态未知!!!");
            break;

        default:
            log.error("不支持的交易状态，交易返回异常!!!");
            break;
		}
	}

	@Test
	public void testDoTradeQuery() {
        TradeQueryReq param = TradeQueryReq.newInstance();
        fillCommonReq(param);
        String outTradeNo = "tradeprecreate15305872129865562426";
        param.setOutTradeNo(outTradeNo);
        
        AlipayF2FQueryResult result = AlipayRestDAO.getInstance().doTradeQuery(param);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");
                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);
                log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                break;
            case FAILED:
                log.error("查询返回该订单支付失败或被关闭!!!");
                break;
            case UNKNOWN:
                log.error("系统异常，订单支付状态未知!!!");
                break;
            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
	}

	@Test
	public void testDoTradePagePay() {
		TradePagePayReq param = TradePagePayReq.newInstance();
		fillCommonReq(param);
		String outTradeNo = "tradeprecreate15305872129865562428";
		param.setOutTradeNo(outTradeNo);
		param.setSubject("智美通101门店电脑网站支付");
		param.setTotalAmount("0.01"); // 1分
		String formHtml = AlipayRestDAO.getInstance().doTradePagePay(param);
		Assert.assertTrue(StringUtils.isNotBlank(formHtml));
		log.info(formHtml);
		/*
		<form name="punchout_form" method="post" 
			action="https://openapi.alipay.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=st2HnTFUP7TwpBCkZUUj2NIZMtMMe4%2FbZTigE6fDYRz8rlAZVEuo2CTVy3%2FXhTH2FWGbdmUlH8X0Gj1%2BEVHKJkvSndg%2FD8ejb77CNyg2pBNxNpx6Z6JY1688l%2ByfaF0mMFBEJfKLXqtyE4BwnRF%2F8dGKh1ucVVH38b2cIaOqxAuOCW7YxBinhCUqHJf95fVibT%2Fmnxa3A3IsHlojRFA7s5%2FeyizM35D97L94zVLlZb10a8aYjBwXl9iBdZ8GiKK%2Blwpg1rh08mtd185V92n3CkC1Gvvi1SOLX0X5BsyL7y0Bx2GwSvRs7eqiRDNS85W08aMqYFkqt8DAzbk0g3xpsw%3D%3D&version=1.0&app_id=2018011901981208&sign_type=RSA2&timestamp=2018-07-03+17%3A09%3A16&alipay_sdk=alipay-sdk-java-3.1.0&format=json">
		<input type="hidden" name="biz_content" value="{&quot;seller_id&quot;:null,&quot;extend_params&quot;:{&quot;sys_service_provider_id&quot;:&quot;2088821855394690&quot;},&quot;out_trade_no&quot;:&quot;tradeprecreate15305872129865562428&quot;,&quot;total_amount&quot;:&quot;0.01&quot;,&quot;subject&quot;:&quot;智美通101门店电脑网站支付&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;,&quot;timeout_express&quot;:&quot;1d&quot;,&quot;qr_pay_mode&quot;:&quot;2&quot;}">
		<input type="submit" value="立即支付" style="display:none" >
		</form>
		<script>document.forms[0].submit();</script>
		*/
	}
	
	private static Log log = LogFactory.getLog(AlipayRestDAOTest.class);

	// 简单打印应答
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
			}
			log.info("body:" + response.getBody());
		}
	}

}
