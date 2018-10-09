package com.hq.storeClient.service.buser.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.service.buser.apiData.BUserAddApiForm;
import com.hq.storeClient.service.buser.apiData.BUserLoginApiForm;
import com.hq.storeClient.service.buser.apiData.BUserLoginWithJsCodeForm;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class BUserLoginMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testLoginWithJsCode() {
		BUserLoginWithJsCodeForm loginForm=BUserLoginWithJsCodeForm.newInstance();
		loginForm.setJscode("033s58Xj22FZvG0xD7Wj2JcTWj2s58XR");
		RestResp resp = BUserClientMgr.getInstance().loginWithJsCode(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(resp));
	}
	
	@Test
	public void testLogin() {
		BUserLoginApiForm loginForm=BUserLoginApiForm.newInstance();
		loginForm.setPassword("123456");
		loginForm.setPhone(18888888888L);
		RestResp resp = BUserClientMgr.getInstance().login(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(resp));
	}
	
	@Test
	public void testReg() {
		BUserAddApiForm regForm=BUserAddApiForm.newInstance();
		regForm.setPassword("123456");
		regForm.setPhone(18888888888L);
		regForm.setName("kevin");
		RestResp resp = BUserClientMgr.getInstance().reg(regForm);
		System.out.println(JsonUtil.getInstance().toJson(resp));
	}
}
