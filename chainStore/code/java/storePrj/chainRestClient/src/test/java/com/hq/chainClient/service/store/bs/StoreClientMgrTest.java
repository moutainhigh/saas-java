package com.hq.chainClient.service.store.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.common.validate.ValidateThreadLocal;
import com.hq.chainClient.service.store.apiData.StoreQueryForm;
import com.hq.chainClient.service.store.data.Store;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class StoreClientMgrTest {
	private static Boss boss;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
	}

	@Test
	public void testGetUnAuth() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Store store = StoreClientMgr.getInstance().get(16052L);
		System.out.println(JsonUtil.getInstance().toJson(store));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreClientMgr.getInstance().get(16052L);
		System.out.println(JsonUtil.getInstance().toJson(store));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testFindStoreByCond() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreQueryForm queryForm = StoreQueryForm.newInstance();
		queryForm.addStoreIds(16052L,16055L);
		queryForm.setChainId(15L);
		PageResp<Store> pageInfo = StoreClientMgr.getInstance().findStoreByCond(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(pageInfo));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
