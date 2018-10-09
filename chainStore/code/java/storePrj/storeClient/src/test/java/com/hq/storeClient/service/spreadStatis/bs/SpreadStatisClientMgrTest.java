package com.hq.storeClient.service.spreadStatis.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeClient.service.spreadStatis.data.vo.BuserSpreadStatis;
import com.hq.storeClient.service.spreadStatis.data.vo.StoreSpreadStatis;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class SpreadStatisClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindBuserSpreadStatis() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long nowTime = System.currentTimeMillis();
		long minTime = nowTime - 24L * 3600 * 1000;
		long storeId = 16052L;
		long buserId = 20L;
		SpreadStatisQueryForm queryForm = SpreadStatisQueryForm.newInstance();
		queryForm.setBuserId(buserId).setMinTime(minTime).setMaxTime(nowTime).setStoreId(storeId);
		BuserSpreadStatis buserSpreadStatis = SpreadStatisClientMgr.getInstance().findBuserSpreadStatis(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(buserSpreadStatis));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testFindStoreSpreadStatis() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long nowTime = System.currentTimeMillis();
		long minTime = nowTime - 24L * 3600 * 1000;
		long storeId = 16052L;
		SpreadStatisQueryForm queryForm = SpreadStatisQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(nowTime).setStoreId(storeId);
		StoreSpreadStatis storeSpreadStatis = SpreadStatisClientMgr.getInstance().findStoreSpreadStatis(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(storeSpreadStatis));
		ValidateThreadLocal.getInstance().remove();
	}

}
