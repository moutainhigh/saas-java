package com.hq.chainStore.service.order.bs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.order.apiData.OrderAddByWorkflowDataIdForm;
import com.hq.chainStore.service.order.apiData.OrderDeleteForm;
import com.hq.chainStore.service.order.apiData.OrderItemAddForm;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.chainStore.service.order.apiData.PayOrderWithNoteApiForm;
import com.hq.chainStore.service.order.apiData.PreOrderAddForm;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.order.data.OrderCount;
import com.hq.chainStore.service.order.data.OrderDateGroup;
import com.hq.chainStore.service.order.data.PayItem;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestConstants;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.order.OrderRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;


public class StoreOrderMgrTest {
	private static Boss boss;
	private static long storeId=21L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testAddPreOrder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7957L;
		PreOrderAddForm addForm = PreOrderAddForm.newInstance();
		addForm.setWorkFlowDataId(workFlowDataId);
		
		Order order = StoreOrderMgr.getInstance().addPreOrder(addForm);
		System.out.println(JsonUtil.getInstance().toJson(order));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddOrderByWorkFlowDataId() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long workFlowDataId = 7803L;
		OrderAddByWorkflowDataIdForm addForm = OrderAddByWorkflowDataIdForm.newInstance();
		addForm.setWorkFlowDataId(workFlowDataId);
		
		List<PayItem> payItems = new ArrayList<PayItem>();
		payItems.add(PayItem.newInstance(4, 588f));
		
		addForm.setPayItems(payItems);
		Order order = StoreOrderMgr.getInstance().addOrderByWorkflowDataId(addForm);
		System.out.println(JsonUtil.getInstance().toJson(order));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddOrder2() {
		List<ProductCard> procCards = new ArrayList<>(); 
		List<Leaguer> leaguers = new ArrayList<>();
		procCards.add(boss.getProductCard(storeId, "_prd_16052_1"));
		leaguers.add(boss.getLeaguer(storeId, "16052_67194"));
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		OrderItemAddForm orderItemAddForm = OrderRobot.getInstance().buildOrderItemAddForm(null, null, procCards, null, leaguers);
		System.out.println(JsonUtil.getInstance().toJson(orderItemAddForm));
		orderItemAddForm.getOrderAddApiForm().setStoreId(storeId);
		orderItemAddForm.getOrderAddApiForm().setCreatorId(boss.getId());
		Order order = StoreOrderMgr.getInstance().addOrder(orderItemAddForm);
		System.out.println(JsonUtil.getInstance().toJson(order));
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertNotNull("应该非空", order);
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Order order = StoreOrderMgr.getInstance().getOrder(storeId, 1850L);
		System.out.println(JsonUtil.getInstance().toJson(order));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete2() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		OrderDeleteForm orderDeleteForm = OrderDeleteForm.newInstance();
		orderDeleteForm.setOrderId(1153L);
		StoreOrderMgr.getInstance().deleteOrder(1153L, 21L, orderDeleteForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindOrderList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMaxTime(System.currentTimeMillis());//System.currentTimeMillis()
		params.setMinTime(0L);
		params.setPageItemCount(11);
		params.setPageNo(1);
		params.setStoreId(storeId);
		
		List<Order> list = StoreOrderMgr.getInstance().findOrderList(params);
		System.out.println(list.size());
		for (Order order : list) {
			System.out.println(JsonUtil.getInstance().toJson(order));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindOrderDateGroupList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMinTime(now - TestConstants.ONE_MONTH);
		params.setPageNo(1);
		params.setStoreId(storeId);
		
		List<OrderDateGroup> list = StoreOrderMgr.getInstance().findOrderDateGroupList(params);
		if(list!=null && !list.isEmpty()) {
			System.out.println(list.size());
			for (OrderDateGroup order : list) {
				System.out.println(JsonUtil.getInstance().toJson(order));
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testPage() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMaxTime(1533139199000L);//System.currentTimeMillis()
		params.setMinTime(1533052800000L);
//		params.setLeaguerId("158_1652");
		params.setStatus("4,3");
		params.setPageItemCount(11);
		params.setPageNo(1);
		params.setStoreId(158L);
		params.setOrderType(-1);
		
		PageResp<Order> page = StoreOrderMgr.getInstance().findOrderPageInfo(params);
		System.out.println(JsonUtil.getInstance().toJson(page));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testCountOrder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		long minTime = now - 20*24 * 3600 * 1000;
		long maxTime = now + 3600*1000;
		
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setMaxTime(maxTime);
		params.setMinTime(minTime);
		params.setPageItemCount(20);
		params.setStatus(1);
		OrderCount orderCount = StoreOrderMgr.getInstance().getOrderCount(params);
		System.out.println(JsonUtil.getInstance().toJson(orderCount));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpadteOrder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		OrderItemAddForm orderItemAddForm = OrderRobot.getInstance().buildOrderItemAddForm(goods, products, procCards, clerkInfos, leaguers);
//		Order order = StoreOrderMgr.getInstance().addOrder(orderItemAddForm);
//		System.out.println(JsonUtil.getInstance().toJson(order));
		
		Order order = StoreOrderMgr.getInstance().getOrder(storeId, 1104L);
		System.out.println(JsonUtil.getInstance().toJson(order));
		
		//更新应付金额
//		OrderUpdateInfoApiForm updateInfo=OrderUpdateInfoApiForm.newInstance();
//		FastBeanCopyer.getInstance().copy(order, updateInfo);
//		updateInfo.setCost(5000f);
//		StoreOrderMgr.getInstance().updateOrderInfo(order.getId(), storeId, updateInfo);
		
		//更新订单状态
//		OrderUpdateStatusApiForm updateStatus = OrderUpdateStatusApiForm.newInstance();
//		FastBeanCopyer.getInstance().copy(order, updateStatus);
//		updateStatus.setStatus(OrderStatusEnum.NOT_PAY.ordinal());
//		StoreOrderMgr.getInstance().updateOrderStatus(order.getId(), storeId, updateStatus);
		
		//支付订单
		OrderUpdatePayItemApiForm updateData = OrderUpdatePayItemApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(order, updateData);
		updateData.setPayItems(OrderRobot.getInstance().buildPayItems(order.getCost()));
		StoreOrderMgr.getInstance().updatePayItem(order.getId(), storeId, updateData);
		
		//获取订单详情
		Order order2 = StoreOrderMgr.getInstance().getOrder(storeId, order.getId());
		System.out.println(JsonUtil.getInstance().toJson(order2));
		
		//删除订单
//		StoreOrderMgr.getInstance().deleteOrder(order.getId());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testPayOrderWithNote() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		long orderId = 2072L;
		Order order = StoreOrderMgr.getInstance().getOrder(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(order));
		
		//支付订单
		PayOrderWithNoteApiForm updateData = PayOrderWithNoteApiForm.newInstance();
		updateData.setPayItems(OrderRobot.getInstance().buildPayItems(order.getCost()));
		StoreOrderMgr.getInstance().payOrderWithNote(orderId, storeId, updateData);
		
		//获取订单详情
		Order order2 = StoreOrderMgr.getInstance().getOrder(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(order2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
