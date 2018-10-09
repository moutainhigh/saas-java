package com.hq.chainClient.service.chainUser.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.chainUser.apiData.ChainUserLoginForm;
import com.hq.chainClient.service.chainUser.apiData.RegistForm;
import com.hq.chainClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class ChainUserClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testReg() {
		RegistForm formInfo = RegistForm.newInstance();
		formInfo.setAreaCode("+86");
		formInfo.setName("kevin");
		formInfo.setPassword("123456");
		formInfo.setPhone(13660623953L);
		formInfo.setVerifyCode("7598");
		RestResp restResp = ChainUserClientMgr.getInstance().reg(formInfo);
		
		System.out.println(JsonUtil.getInstance().toJson(restResp));
	}
	
	@Test
	public void testLogin() {
		ChainUserLoginForm loginForm = ChainUserLoginForm.newInstance();
		loginForm.setPassword("123456");
		loginForm.setPhone(13660623953L);
		RestResp restResp = ChainUserClientMgr.getInstance().login(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
	}
}
