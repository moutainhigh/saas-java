package com.hq.thirdPartyClient.service.sms.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.thirdPartyClient.service.common.OriginTypeEnum;
import com.hq.thirdPartyClient.service.sms.apiData.VerifyCodeForm;
import com.hq.thirdPartyClient.service.sms.data.SmsResult;
import com.hq.thirdPartyClient.service.sms.data.SmsTypeEnum;
import com.hq.thirdPartyClient.testClass.TestEnv;

public class VerifyCodeMgrTest {
	
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testSendRandomCode() {
		VerifyCodeForm codeForm = VerifyCodeForm.newInstance("13660623953",SmsTypeEnum.B_VERIFICATION_CODE.ordinal(), OriginTypeEnum.STORE.ordinal());
		SmsResult resp = VerifyCodeMgr.getInstance().sendSmsByYunPian(codeForm);
		System.out.println(resp);
	}

}
