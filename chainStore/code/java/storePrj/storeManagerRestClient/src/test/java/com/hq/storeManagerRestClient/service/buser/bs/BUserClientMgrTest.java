package com.hq.storeManagerRestClient.service.buser.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.common.validate.ValidateThreadLocal;
import com.hq.storeManagerRestClient.service.buser.data.BUser;
import com.hq.storeManagerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class BUserClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetBUser() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUser buser = BUserClientMgr.getInstance().getBUser(20L);
		System.out.println(JsonUtil.getInstance().toJson(buser));
		ValidateThreadLocal.getInstance().remove();
	}

}
