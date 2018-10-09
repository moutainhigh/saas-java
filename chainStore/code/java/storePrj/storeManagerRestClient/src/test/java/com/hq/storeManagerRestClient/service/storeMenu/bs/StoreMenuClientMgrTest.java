package com.hq.storeManagerRestClient.service.storeMenu.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.common.validate.ValidateThreadLocal;
import com.hq.storeManagerRestClient.service.storeMenu.data.StoreMenu;
import com.hq.storeManagerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreMenuClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetStoreMenu() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreMenu data = StoreMenuClientMgr.getInstance().getStoreMenu();
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}

}
