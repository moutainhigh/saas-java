package com.hq.storeClient.service.orderNotes.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.orderNotes.apiData.RevokeContentAddForm;
import com.hq.storeClient.service.orderNotes.data.OrderNotes;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OrderNotesClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testRevokeOrder() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long orderId = 2519L;
		long storeId = 16052L;
		RevokeContentAddForm addForm = RevokeContentAddForm.newInstance();
		addForm.setCreatorId(20L);
		addForm.setCreatorName("kevin");
		addForm.setRemark("撤单");
		OrderNotes orderNotes = OrderNotesClientMgr.getInstance().revokeOrder(storeId, orderId, addForm);
		System.out.println(JsonUtil.getInstance().toJson(orderNotes));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGetOrderNotes() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long orderId = 2519L;
		long storeId = 16052L;
		OrderNotes orderNotes = OrderNotesClientMgr.getInstance().getOrderNotes(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(orderNotes));
		ValidateThreadLocal.getInstance().remove();
	}

}
