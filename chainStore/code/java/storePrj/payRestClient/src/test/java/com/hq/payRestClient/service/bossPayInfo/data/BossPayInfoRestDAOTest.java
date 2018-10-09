package com.hq.payRestClient.service.bossPayInfo.data;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.hq.payRestClient.PayClientMgr;
import com.hq.payRestClient.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payRestClient.zenmind.dao.rest.restSTImpl.RespStatus;
import com.hq.payRestClient.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.payRestClient.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.dao.RestDaoMgr;
import com.zenmind.dao.rest.RestProxyException;

public class BossPayInfoRestDAOTest {

	@BeforeClass
	public static void initEnv() {
		RestTemplateMgr.getInstance().init();

		RestDaoMgr.init(RestProxySTImpl.getInstance());
		
		String storeServerUrl = "http://127.0.0.1:9131/payms/ws/v1";
		
		PayClientMgr.init(storeServerUrl);
	}
	
	@Test
	public void testAdd() {
		BossPayInfoAddApiForm form = new BossPayInfoAddApiForm();
		form.setStoreId(2L);
		form.setWxpayAppId("appid11111");
		form.setWxpayMchId("mchid111111");
		form.setWxpayKey("key111111");
		form.setWxpayCertPath("appid11111.p12");
		BossPayInfo target = BossPayInfoRestDAO.getInstance().add(form);
		System.out.println(target.toString());
		Assert.isTrue(target!=null, "error");
	}

	@Test
	public void testUpdate() {
		BossPayInfoAddApiForm form = new BossPayInfoAddApiForm();
		form.setStoreId(2L);
		form.setWxpayAppId("appid11113");
		form.setWxpayMchId("mchid111113");
		form.setWxpayKey("key111113");
		form.setWxpayCertPath("appid11113.p12");
		BossPayInfo target = BossPayInfoRestDAO.getInstance().update(2,form);
		System.out.println(target.toString());
		Assert.isTrue(target!=null, "error");
	}

	@Test
	public void testGet() {
		BossPayInfo target = BossPayInfoRestDAO.getInstance().get(2);
		System.out.println(target.toString());
		Assert.isTrue(target!=null, "error");
	}

	@Test
	public void testFindByStoreId() {
		BossPayInfo target = BossPayInfoRestDAO.getInstance().findByStoreId(2);
		System.out.println(target.toString());
		Assert.isTrue(target!=null, "error");
	}
	
	private void handleRestProxyException(Exception e) {
		if (e instanceof RestProxyException) {
			RestProxyException errorTmp = (RestProxyException) e;
			RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
			String tips = errorTmp.getTips();
			System.out.println(respStatus.getCode() + ":" + tips);
		}
		e.printStackTrace();
	}

	
}
