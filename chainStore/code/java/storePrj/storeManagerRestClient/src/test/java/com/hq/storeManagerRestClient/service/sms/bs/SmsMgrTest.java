package com.hq.storeManagerRestClient.service.sms.bs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.storeManagerRestClient.service.sms.apiData.SmsResp;
import com.hq.storeManagerRestClient.testClass.TestEnv;

public class SmsMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testSendRandomCode() {
		RandomCodeAPIForm apiForm = RandomCodeAPIForm.newInstance();
		apiForm.setPhoneNumber("13660623953");
		SmsResp resp = SmsMgr.getInstance().sendRandomCode(apiForm);
		System.out.println(resp);
		Assert.assertTrue("短信发送成功", "OK".equals(resp.getMessage()));
	}


}
