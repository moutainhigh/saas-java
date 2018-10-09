package com.hq.storeClient.service.storeMenu.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreMenuClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetStoreMenu() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreMenu storeMenu = StoreMenuClientMgr.getInstance().getStoreMenu();
		System.out.println(JsonUtil.getInstance().toJson(storeMenu));
	}

}
