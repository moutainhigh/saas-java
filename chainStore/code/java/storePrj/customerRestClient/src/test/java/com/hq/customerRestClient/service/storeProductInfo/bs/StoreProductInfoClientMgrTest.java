package com.hq.customerRestClient.service.storeProductInfo.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.storeProductInfo.data.StoreProductInfo;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreProductInfoClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testGetStoreProductInfo() {
		long storeId = 16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreProductInfo  data = StoreProductInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(data));
	}
	
}
