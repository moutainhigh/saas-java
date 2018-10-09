package com.hq.storeClient.service.opLog.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.opLog.apiData.OpLogQueryForm;
import com.hq.storeClient.service.opLog.data.OpLog;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OpLogClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		OpLogQueryForm queryForm = OpLogQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<OpLog> data = OpLogClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}

}
