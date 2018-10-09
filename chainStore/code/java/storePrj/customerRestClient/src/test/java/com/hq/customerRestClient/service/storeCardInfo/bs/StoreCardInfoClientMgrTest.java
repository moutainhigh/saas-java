package com.hq.customerRestClient.service.storeCardInfo.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.storeCardInfo.data.StoreCardInfo;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreCardInfoClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		long storeId = 16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo data = StoreCardInfoClientMgr.getInstance().get(storeId);
		System.out.println(data.getVer());
		System.out.println(JsonUtil.getInstance().toJson(data));
	}

}
