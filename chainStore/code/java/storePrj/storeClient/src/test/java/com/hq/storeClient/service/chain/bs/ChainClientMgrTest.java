package com.hq.storeClient.service.chain.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.chain.data.Chain;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class ChainClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long chainId = 12L;
		Chain chain = ChainClientMgr.getInstance().get(chainId);
		System.out.println(JsonUtil.getInstance().toJson(chain));
		ValidateThreadLocal.getInstance().remove();
	}

}
