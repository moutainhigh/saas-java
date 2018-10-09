package com.hq.customerRestClient.service.buser.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.buser.data.BUser;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class BuserMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUser buser = BUserClientMgr.getInstance().get(20L);
		System.out.println(JsonUtil.getInstance().toJson(buser));
	}
}
