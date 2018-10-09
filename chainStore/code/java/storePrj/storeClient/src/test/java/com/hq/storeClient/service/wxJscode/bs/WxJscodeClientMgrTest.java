package com.hq.storeClient.service.wxJscode.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeClient.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeClient.service.wxJscode.data.UserInfo;
import com.hq.storeClient.service.wxJscode.data.WxJscode;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class WxJscodeClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testJsCode2Session() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		WxJsCodeForm inputForm = WxJsCodeForm.newInstance();
		inputForm.setAppId("wx95bd20f70a99f627");
		inputForm.setJscode("0234ELJk0Slc1m1SyjKk0ttCJk04ELJD");
		WxJscode resp = WxJscodeClientMgr.getInstance().jsCode2Session(inputForm);
		System.out.println(JsonUtil.getInstance().toJson(resp));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testDecryptWXAppletInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		DecryptWxAppletForm inputForm = DecryptWxAppletForm.newInstance();
		inputForm.setJscode("033F7bUo0cn7Wq13RERo0Lr4Uo0F7bUy");
		inputForm.setEncryptedData("CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==");
		inputForm.setIv("r7BXXKkLb8qrSNn05n0qiA==");
		UserInfo userInfo = WxJscodeClientMgr.getInstance().decryptWXAppletInfo(inputForm);
		System.out.println(JsonUtil.getInstance().toJson(userInfo));
		ValidateThreadLocal.getInstance().remove();
	}

}
