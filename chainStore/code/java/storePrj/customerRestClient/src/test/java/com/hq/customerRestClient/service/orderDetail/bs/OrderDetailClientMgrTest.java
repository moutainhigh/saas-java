package com.hq.customerRestClient.service.orderDetail.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.orderDetail.data.OrderDetail;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OrderDetailClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetOrderDetail() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long orderId = 2593L;
		long storeId = 16045L;
		OrderDetail detail = OrderDetailClientMgr.getInstance().getOrderDetail(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(detail));
		ValidateThreadLocal.getInstance().remove();
	}

}
