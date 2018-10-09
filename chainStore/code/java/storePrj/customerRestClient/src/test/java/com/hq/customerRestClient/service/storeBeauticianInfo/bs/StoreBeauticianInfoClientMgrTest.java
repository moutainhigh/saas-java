package com.hq.customerRestClient.service.storeBeauticianInfo.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreBeauticianInfoClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testGetBeauticianInfo() {
		long storeId = 16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreBeauticianInfo data = StoreBeauticianInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(data));
	}
	
}
