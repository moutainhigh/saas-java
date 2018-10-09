package com.hq.storeClient.service.vipLevel.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.vipLevel.apiData.StoreVipLevelQueryForm;
import com.hq.storeClient.service.vipLevel.data.VipLevel;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class VipLevelClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		VipLevel vipLevel = VipLevelClientMgr.getInstance().get(1L);
		System.out.println(JsonUtil.getInstance().toJson(vipLevel));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testFindPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreVipLevelQueryForm queryForm = StoreVipLevelQueryForm.newInstance();
		queryForm.setVipType(5L);
		PageResp<VipLevel> page = VipLevelClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

}
