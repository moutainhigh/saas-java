package com.hq.chainClient.service.sms.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.chainClient.service.sms.apiData.SmsResp;
import com.hq.chainClient.testClass.TestEnv;

public class SmsClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testSendRandomCode() {
		RandomCodeAPIForm apiForm = RandomCodeAPIForm.newInstance();
		apiForm.setPhoneNumber("+8613660623953");
		SmsResp resp = SmsClientMgr.getInstance().sendRandomCode(apiForm);
		System.out.println(resp);
	}
}
