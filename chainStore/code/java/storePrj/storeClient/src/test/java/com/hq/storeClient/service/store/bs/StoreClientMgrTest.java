package com.hq.storeClient.service.store.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.store.apiData.JoinStoreForm;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.apiData.StoreUpdateChainData;
import com.hq.storeClient.service.store.data.Store;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testJoinStoreForCuser() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		JoinStoreForm joinStoreForm = JoinStoreForm.newInstance();
		joinStoreForm.setCuserId(67319L);
		joinStoreForm.setStoreId(16052L);
		StoreClientMgr.getInstance().joinStoreForCuser(joinStoreForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindByCuser() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long cuserId = 205L;
		List<Store> list = StoreClientMgr.getInstance().findByCuser(cuserId);
		System.out.println(JsonUtil.getInstance().toJson(list));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGet() {
		long storeId=16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Store store = StoreClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(store));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testUpdateChainData() {
		long storeId=16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreUpdateChainData inputForm = StoreUpdateChainData.newInstance();
		inputForm.setChainId(1L);
		inputForm.setStoreId(storeId);
		StoreClientMgr.getInstance().updateChainData(storeId, inputForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindStoreByCond() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreQueryForm queryForm=StoreQueryForm.newInstance();
//		queryForm.setChainId(1L);
//		queryForm.setName("美雅芙");
		queryForm.addStoreIds(219L,313L);
		PageResp<Store> pageInfo = StoreClientMgr.getInstance().findStoreByCond(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(pageInfo));
		ValidateThreadLocal.getInstance().remove();
	}
}
