package com.hq.storeClient.service.order.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeClient.service.order.apiData.BuyItemForCuser;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderDeleteForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateType;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeClient.service.order.data.BuyTypeEnum;
import com.hq.storeClient.service.order.data.MallOrder;
import com.hq.storeClient.service.order.data.Order;
import com.hq.storeClient.service.order.data.PayItem;
import com.hq.storeClient.service.order.data.PayTypeEnum;
import com.hq.storeClient.service.orderTrack.data.OrderTrackTypeEnum;
import com.hq.storeClient.service.storeGoods.data.Goods;
import com.hq.storeClient.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeClient.service.storeProductInfo.data.ProductInfo;
import com.hq.storeClient.testClass.Robot;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class OrderClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testDelete() {
		long storeId = 209L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderQueryForm queryForm = OrderQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setOrderType(0);
		PageResp<Order> page = OrderClientMgr.getInstance().findOrderPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		ValidateThreadLocal.getInstance().remove();
		
		Collection<Order> values = page.getList();
		for (Order data : values) {
			update(storeId, data);
			System.out.println(JsonUtil.getInstance().toJson(data));
		}
	}
	
	private void update(long storeId, Order data) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderDeleteForm removeForm = OrderDeleteForm.newInstance();
		removeForm.setOrderId(data.getId());
		OrderUpdateApiForm updateForm = OrderUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(OrderUpdateType.DeleteOrder.ordinal());
		updateForm.setOrderDeleteForm(removeForm);
		OrderClientMgr.getInstance().updateOrder(data.getId(), updateForm);
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testPayOrderByCuser() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long orderId = 2714L;
		long storeId = 16052L;
		Order order = OrderClientMgr.getInstance().get(storeId, orderId);
		
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		PayOrderForCuserForm payForm = PayOrderForCuserForm.newInstance();
		payForm.setCreatorId(order.getCreatorId());
		payForm.setOrderId(order.getId());
		payForm.setStoreId(order.getStoreId());
		payForm.setPayItems(takePayItems(order.getCost()));
		Order order2 = OrderClientMgr.getInstance().payOrderByCuser(payForm);
		System.out.println(JsonUtil.getInstance().toJson(order2));
	}
	
	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long orderId = 1923L;
		long storeId = 16052L;
		Order order = OrderClientMgr.getInstance().get(storeId, orderId);
		System.out.println(JsonUtil.getInstance().toJson(order));
	}

	@Test
	public void testFindOrderPageInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setStoreId(16052L).setOrderType(0).addStatus(1,3,4);
		params.setMinPayTime(1537081163792L);
		params.setMaxPayTime(1537513163792L);
		params.setOrderType(-1);
		PageResp<Order> page = OrderClientMgr.getInstance().findOrderPageInfo(params);
		System.out.println(page.getTotalCount());
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindMallOrderPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		MallOrderQueryForm queryForm = MallOrderQueryForm.newInstance();
		queryForm.setStoreId(16052L);
		PageResp<MallOrder> page = OrderClientMgr.getInstance().findMallOrderPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testAddPreOrderForCuser() {
		long storeId = 16052L;
		Leaguer leaguer = Robot.getInstance().getRandomLeaguer(storeId);
		LeaguerDetail detail = Robot.getInstance().getLeaguerDetail(storeId, leaguer.getId());
		Goods goods = Robot.getInstance().getRandomGoods(storeId);
		ProductInfo product = Robot.getInstance().getRandomProductInfo(storeId);
		System.out.println("==============>>>>>"+detail.getId());
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		PreOrderForCuserAddForm addForm = PreOrderForCuserAddForm.newInstance();
		addForm.setBuyItemForCusers(takeBuyItems(goods, product));
		addForm.setCost(takeCost(addForm.getBuyItemForCusers()));
		addForm.setCreatorId(detail.getCuserId());
		addForm.setLeaguerId(leaguer.getId());
		addForm.setMemberCardId(detail.getLeaguerMemberCard().getCardId());
		addForm.setStoreId(storeId);
		
		//收货信息
		addForm.setReceiver("李贵");
		addForm.setPhone("13365478952");
		addForm.setType(OrderTrackTypeEnum.Express.ordinal());
		addForm.setAddress("广州市天河区");
		
		addForm.setDynamicId(1L);
		
		Order order = OrderClientMgr.getInstance().addPreOrderForCuser(addForm);
		
		System.out.println(JsonUtil.getInstance().toJson(order));
	}
	
	private List<PayItem> takePayItems(float cost){
		List<PayItem> list = new ArrayList<PayItem>();
		PayItem item = PayItem.newInstance(PayTypeEnum.CASH.ordinal(), cost);
		list.add(item);
		return list;
	}
	
	private List<BuyItemForCuser> takeBuyItems(Goods goods, ProductInfo product){
		List<BuyItemForCuser> list = new ArrayList<BuyItemForCuser>();
		list.add(takeBuyGoodsItem(goods));
		list.add(takeBuyProductItem(product));
		return list;
	}
	
	private BuyItemForCuser takeBuyGoodsItem(Goods goods){
		BuyItemForCuser item = BuyItemForCuser.newInstance();
		item.setCount(RandomUtils.nextInt(1, 5));
		item.setDiscount(RandomUtils.nextInt(1, 11)*1.0f);
		item.setOldPrice(goods.getPrice());
		item.setRestCount(item.getCount()-1);
		item.setBuyType(BuyTypeEnum.GOODS.ordinal());
		item.setPgId(goods.getId());
		return item;
	}
	
	private BuyItemForCuser takeBuyProductItem(ProductInfo product){
		BuyItemForCuser item = BuyItemForCuser.newInstance();
		item.setCount(RandomUtils.nextInt(1, 5));
		item.setDiscount(RandomUtils.nextInt(1, 11)*1.0f);
		item.setOldPrice(product.getPrice());
		item.setRestCount(item.getCount()-1);
		item.setBuyType(BuyTypeEnum.PRODUCT.ordinal());
		item.setPgId(product.getId());
		return item;
	}
	
	private float takeCost(List<BuyItemForCuser> list){
		float cost = 0.0f;
		for (BuyItemForCuser buyItemForCuser : list) {
			cost+=buyItemForCuser.getDiscount()*buyItemForCuser.getCount()*buyItemForCuser.getOldPrice();
		}
		return cost;
	}

}
