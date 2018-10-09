package com.hq.payRestClient.service.pay.data;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.hq.payRestClient.PayClientMgr;
import com.hq.payRestClient.common.validate.ValidateThreadLocal;
import com.hq.payRestClient.service.pay.apiData.ApiTypeEnum;
import com.hq.payRestClient.service.pay.apiData.BeScanPayApiForm;
import com.hq.payRestClient.service.pay.apiData.PayQueryApiForm;
import com.hq.payRestClient.service.pay.apiData.PayQueryResp;
import com.hq.payRestClient.service.pay.apiData.ScanPayApiForm;
import com.hq.payRestClient.service.qrcode.apiData.QrCodeResp;
import com.hq.payRestClient.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.payRestClient.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.dao.RestDaoMgr;

public class PayRestDAOTest {
	
	@BeforeClass
	public static void initEnv() {
		RestTemplateMgr.getInstance().init();

		RestDaoMgr.init(RestProxySTImpl.getInstance());
		
		//String serverUrl = "http://192.168.40.220:9131/payms/ws/v1";
		String serverUrl = "http://127.0.0.1:9131/payms/ws/v1";
		
		PayClientMgr.init(serverUrl);
	}
	
	@Before
	public void beforeTest() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
	}
	
	@After
	public void afterTest() {
		ValidateThreadLocal.getInstance().setValidateInfo(null);
	}
	
	@Test
	public void testBeScan() {
		BeScanPayApiForm form = new BeScanPayApiForm();
		form.setApiType(ApiTypeEnum.WXPAY.ordinal());
//		form.setApiType(ApiTypeEnum.ALIPAY.ordinal());
		form.setStoreId(1L);
		form.setOrderId(1001L);
		form.setOutTradeNo("no_20001_12");
		form.setTotalAmount("0.01");
		QrCodeResp beScan = PayRestDAO.getInstance().beScan(form);
		System.out.println(beScan.getImgUrl());
		Assert.isTrue(StringUtils.isNoneBlank(beScan.getImgUrl()), "error");
	}

	@Test
	public void testScan() {
		ScanPayApiForm form = new ScanPayApiForm();
		form.setApiType(ApiTypeEnum.WXPAY.ordinal());
//		form.setApiType(ApiTypeEnum.ALIPAY.ordinal());
		form.setStoreId(1L);
		form.setOrderId(1001L);
		form.setOutTradeNo("no_20001_11");
		form.setTotalAmount("0.01");
		form.setAuthCode("134882036390021610");
		PayRestDAO.getInstance().scan(form);
	}

	@Test
	public void testQuery() {
		PayQueryApiForm form = new PayQueryApiForm();
		form.setApiType(ApiTypeEnum.WXPAY.ordinal());
//		form.setApiType(ApiTypeEnum.ALIPAY.ordinal());
		form.setOutTradeNo("no_20001_04");
		PayQueryResp resp = PayRestDAO.getInstance().query(form);
		System.out.println(resp.getTradeNo());
		Assert.isTrue(StringUtils.isNoneBlank(resp.getTradeNo()), "error");
	}
	
}
