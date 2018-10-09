package com.hq.customerRestClient.service.orderTrack.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.customerRestClient.service.orderTrack.apiData.OrderTrackUpdateStatusForm;
import com.hq.customerRestClient.service.orderTrack.apiData.OrderTrackUpdateType;
import com.hq.customerRestClient.service.orderTrack.data.OrderTrack;
import com.hq.customerRestClient.service.orderTrack.data.OrderTrackStatusEnum;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OrderTrackClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetOrderTrack() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		long orderId = 2589L;
		OrderTrack orderTrack = OrderTrackClientMgr.getInstance().getOrderTrack(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(orderTrack));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdate() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		long orderId = 2589L;
		OrderTrackUpdateStatusForm trackUpdateStatusForm = OrderTrackUpdateStatusForm.newInstance();
		trackUpdateStatusForm.setCompany("顺丰");
		trackUpdateStatusForm.setCourierNum("00000000");
		trackUpdateStatusForm.setStatus(OrderTrackStatusEnum.Send.ordinal());
		OrderTrackUpdateApiForm inputForm = OrderTrackUpdateApiForm.newInstance();
		inputForm.setOrderTrackUpdateType(OrderTrackUpdateType.UpdateStatus);
		inputForm.setTrackUpdateStatusForm(trackUpdateStatusForm);
		OrderTrack orderTrack = OrderTrackClientMgr.getInstance().update(storeId, orderId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(orderTrack));
		ValidateThreadLocal.getInstance().remove();
	}

}
