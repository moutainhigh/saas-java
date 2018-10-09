package com.hq.customerRestClient.service.cuser.bs;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.customerRestClient.service.cuser.apiData.CUserChangePasswordApiData;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginApiForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginByCodeApiForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginWithJscodeForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserLoginWithWxInfoForm;
import com.hq.customerRestClient.service.cuser.apiData.LoginResp;
import com.hq.customerRestClient.service.cuser.apiData.WxLoginResp;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.customerRestClient.testClass.AccessTokenMgr;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class CUserLoginMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testLoginWithJscode() {
		CUserLoginWithJscodeForm loginForm = CUserLoginWithJscodeForm.newInstance();
		loginForm.setJscode("033cTtod1SB9mt0QI4od1fPxod1cTtoc");
		RestResp restResp = CUserClientMgr.getInstance().loginWithJscode(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
		WxLoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), WxLoginResp.class);
		CUser user = loginResp.getCuser();
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);
		get(user);
	}

	@Ignore
	@Test
	public void testLogin() {
		long phone = 13660623953L;
		String password = "654321";
		CUserLoginApiForm loginForm = CUserLoginApiForm.newInstance();
		loginForm.setPhone(phone);
		loginForm.setPassword(password);
		RestResp restResp = CUserClientMgr.getInstance().login(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		CUser user = loginResp.getCuser();
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);

		changePassword(user);

		get(user);

	}

	@Test
	public void testLoginByWxInfo() {
		CUserLoginWithWxInfoForm loginForm = CUserLoginWithWxInfoForm.newInstance();
		RestResp restResp = CUserClientMgr.getInstance().loginByWxInfo(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		CUser user = loginResp.getCuser();
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);

		get(user);
	}

	@Test
	public void testLoginByCode() {
		CUserLoginByCodeApiForm loginForm = CUserLoginByCodeApiForm.newInstance();
		loginForm.setPhone(13660623999L);
		loginForm.setVerifyCode("1234");
		RestResp restResp = CUserClientMgr.getInstance().loginByCode(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		CUser user = loginResp.getCuser();
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);

		get(user);
	}

	private void get(CUser user) {
		AccessTokenMgr.getInstance().setOpIdTL(user.getId());
		CUser data = CUserClientMgr.getInstance().get(98L);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	private void changePassword(CUser user) {
		AccessTokenMgr.getInstance().setOpIdTL(user.getId());
		CUserChangePasswordApiData changePasswordData = CUserChangePasswordApiData.newInstance();
		changePasswordData.setOldPassword("654321");
		changePasswordData.setPassword("123456");
		CUserClientMgr.getInstance().changePassword(user.getId(), changePasswordData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
