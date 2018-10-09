package com.hq.customerRestClient.service.sms.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.sms.apiData.RandomCodeAPIForm;
import com.hq.customerRestClient.service.sms.data.SmsResp;
import com.hq.customerRestClient.testClass.TestEnv;

public class SmsClientMgrTest {
	
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testSendRandomCode() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		RandomCodeAPIForm apiForm = RandomCodeAPIForm.newInstance();
		apiForm.setPhoneNumber("+8613660623953");
		SmsResp resp = SmsClientMgr.getInstance().sendRandomCode(apiForm);
		System.out.println(resp);
	}

}
