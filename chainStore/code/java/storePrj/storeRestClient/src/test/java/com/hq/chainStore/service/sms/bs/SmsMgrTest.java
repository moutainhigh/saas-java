package com.hq.chainStore.service.sms.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.sms.apiData.RandomCodeAPIForm;
import com.hq.chainStore.service.sms.apiData.SmsResp;
import com.hq.testClass.TestEnv;

public class SmsMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testSendRandomCode() {
		RandomCodeAPIForm apiForm = RandomCodeAPIForm.newInstance();
//		apiForm.setPhoneNumber("+85366661376");
		apiForm.setPhoneNumber("+8613660623953");
//		apiForm.setPhoneNumber("+16467758990");
		SmsResp resp = SmsMgr.getInstance().sendRandomCode(apiForm);
		System.out.println(resp);
	}


}
