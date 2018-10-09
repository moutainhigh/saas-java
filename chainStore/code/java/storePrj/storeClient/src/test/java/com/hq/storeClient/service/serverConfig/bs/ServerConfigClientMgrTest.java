package com.hq.storeClient.service.serverConfig.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.serverConfig.data.ServerConfig;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class ServerConfigClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindOneServerConfig() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		ServerConfig config = ServerConfigClientMgr.getInstance().findOneServerConfig("智美通", "zhimeitong", "2.0.3");
		System.out.println(JsonUtil.getInstance().toJson(config));
		ValidateThreadLocal.getInstance().remove();
	}

}
