package com.hq.storeClient.service.storeClerkInfo.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.storeClient.service.storeClerkInfo.bs.StoreClerkInfoClientMgr;
import com.hq.storeClient.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreClerkInfoClientMgrTest {

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		long storeId = 16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreClerkInfo storeClerk = StoreClerkInfoClientMgr.getInstance().getByStoreId(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeClerk));

		ApplyClerkInfoData applyClerkInfoData = ApplyClerkInfoData.newInstance();
		applyClerkInfoData.setStoreId(storeId);
		applyClerkInfoData.setbUserId(248891L);
		StoreClerkInfoClientMgr.getInstance().applyClerkInfo(storeId, applyClerkInfoData);
		ValidateThreadLocal.getInstance().remove();
	}
}
