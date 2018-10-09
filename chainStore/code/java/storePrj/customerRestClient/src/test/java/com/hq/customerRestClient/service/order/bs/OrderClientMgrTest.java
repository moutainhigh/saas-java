package com.hq.customerRestClient.service.order.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.restClientResp.PageResp;
import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.order.apiData.OrderQueryForm;
import com.hq.customerRestClient.service.order.data.Order;
import com.hq.customerRestClient.service.order.data.OrderOriginEnum;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OrderClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindOrderPageInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderQueryForm queryForm = OrderQueryForm.newInstance();
		queryForm.setStoreId(16045L).setLeaguerId("16045_67251");
		queryForm.addOrigin(OrderOriginEnum.BUSINESS.ordinal());
		PageResp<Order> page = OrderClientMgr.getInstance().findOrderPageInfo(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGetOrder() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		long orderId = 2589L;
		Order order = OrderClientMgr.getInstance().get(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(order));
	}
}
