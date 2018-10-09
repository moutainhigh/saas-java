package com.hq.storeClient.service.orderDetail.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.orderDetail.data.OrderDetail;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OrderDetailClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetOrderDetail() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long orderId = 2121L;
		long storeId = 16052L;
		OrderDetail order = OrderDetailClientMgr.getInstance().getOrderDetail(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(order));
	}

}
