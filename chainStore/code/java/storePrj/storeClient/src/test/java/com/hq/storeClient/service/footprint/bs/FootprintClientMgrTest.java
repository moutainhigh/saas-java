package com.hq.storeClient.service.footprint.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.footprint.apiData.FootprintAddForm;
import com.hq.storeClient.service.footprint.apiData.FootprintQueryForm;
import com.hq.storeClient.service.footprint.data.Footprint;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class FootprintClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		FootprintQueryForm queryForm = FootprintQueryForm.newInstance();
		queryForm.setBuserId(20L);
		PageResp<Footprint> page = FootprintClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Footprint data = FootprintClientMgr.getInstance().get(1L);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testAdd() {
		long cuserId = 66977L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		FootprintAddForm addForm = FootprintAddForm.newInstance();
		addForm.setCuserId(cuserId);
		addForm.setDynamicId(1L);
		Footprint data = FootprintClientMgr.getInstance().add(addForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}

}
