package com.hq.orderRestClient.service.order.bs;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hq.orderRestClient.common.validate.ValidateThreadLocal;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PayTypeEnum;
import com.hq.orderRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.common.page.PageBean;

public class OrderClientMgrTest {
	@Before
	public void init() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testUpdateOrder() {
		long storeId = 332L;
		long orderId = 2031L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderUpdatePayItemApiForm updatePayItemApiForm = OrderUpdatePayItemApiForm.newInstance();
		PayItem item = PayItem.newInstance();
		item.setCost(5f);
		item.setPayType(PayTypeEnum.CASH.ordinal());
		updatePayItemApiForm.getPayItems().add(item);
		OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
		updateApiForm.setUpdateType(OrderUpdateType.UpdatePayItem.ordinal());
		updateApiForm.setUpdatePayItemApiForm(updatePayItemApiForm);
		updateApiForm.setStoreId(storeId);
		OrderClientMgr.getInstance().updateOrder(orderId, updateApiForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGetOrder() {
		long storeId = 332L;
		long orderId = 2031L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Order order = OrderClientMgr.getInstance().get(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(order));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindOrderPageInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setStoreId(16052L);
		params.setMaxTime(1534780799999L);
		params.setMinTime(1533052800000L);
		PageBean<Order> page = OrderClientMgr.getInstance().findOrderPageInfo(params);
		List<Order> list = page.getList();
		System.out.println(list.size());
		float sum = 0.0f;
		for (Order order : list) {
			List<BuyItem> buyItems = order.getBuyItems();
			for (BuyItem item : buyItems) {
				sum+=item.getPay();
			}
		}
		System.out.println(sum);
		ValidateThreadLocal.getInstance().setValidateInfo(null);
	}
	
	@Test
	public void testGetOrderCount() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setStoreId(16052L);
		params.setMaxTime(1534780799999L);
		params.setMinTime(1533052800000L);
		OrderCount orderCount = OrderClientMgr.getInstance().getOrderCount(params);
		System.out.println(JsonUtil.getInstance().toJson(orderCount));
		ValidateThreadLocal.getInstance().setValidateInfo(null);
	}
	
	@Test
	public void testFindOrderList() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setStoreId(16052L).setOrderType(0).addStatus(1,3,4);
		params.setMinPayTime(1537081163792L);
		params.setMaxPayTime(1537513163792L);
		params.setOrderType(-1);
		List<Order> list = OrderClientMgr.getInstance().findOrderList(params);
		System.out.println(list.size());
		ValidateThreadLocal.getInstance().setValidateInfo(null);
	}
}
