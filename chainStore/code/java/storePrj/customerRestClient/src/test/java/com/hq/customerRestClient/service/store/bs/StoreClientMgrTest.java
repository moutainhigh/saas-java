package com.hq.customerRestClient.service.store.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.store.data.Store;
import com.hq.customerRestClient.testClass.AccessTokenMgr;
import com.hq.customerRestClient.testClass.Customer;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreClientMgrTest {
			
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void TestGetStore() {
		long storeId=16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Store store = StoreClientMgr.getInstance().getStore(storeId);
		System.out.println(JsonUtil.getInstance().toJson(store));
	}
	
	@Test
	public void TestFindByCuser() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long cuserId = 205L;
		List<Store> list = StoreClientMgr.getInstance().findByCuser(cuserId);
		System.out.println(JsonUtil.getInstance().toJson(list));
	}
	
	@Test
	public void testGetStoreWithLogin() {
		Customer customer = Customer.newCustomer(13620809235L, "123456");
		customer.login();
		long storeId = customer.getStoreId();
		
		System.out.println(customer.getStoreIds());
		
		AccessTokenMgr.getInstance().setOpIdTL(customer.getId());
		Store store = StoreClientMgr.getInstance().getStore(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		System.out.println(JsonUtil.getInstance().toJson(store));
	}
}
