package com.hq.experience.data.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.order.apiData.OrderAddApiForm;
import com.hq.chainStore.service.order.apiData.OrderItemAddForm;
import com.hq.chainStore.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.chainStore.service.order.bs.StoreOrderMgr;
import com.hq.chainStore.service.order.data.BuyItem;
import com.hq.chainStore.service.order.data.BuyTypeEnum;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.order.data.OrderTypeEnum;
import com.hq.chainStore.service.order.data.PayItem;
import com.hq.chainStore.service.order.data.PayTypeEnum;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

public class GenerateOrderData extends BaseGenerate{
	private List<ProductInfo> productInfos;
	private List<Leaguer> leaguers;
	private List<BUser> busers;
	
	public static void main(String[] args) {
		long phone = 13660623953L;
		String storeService = "http://192.168.40.221/storems/ws/v1";
		String reportService = "http://192.168.40.221/orderms/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, reportService);
		new GenerateOrderData().genData(phone);
	}
	
	public GenerateOrderData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
//			storeIds.clear();
//			storeIds.add(21L);
			for (Long id : storeIds) {
				this.storeId = id;
				initOrderData();
				addOrderNotPay();
				addOrderHasPay();
			}
			System.out.println("Generate Order Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initOrderData(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		productInfos = new ArrayList<ProductInfo>(StoreProductInfoMgr.getInstance().get(storeId).getProductInfoMap().values());
		leaguers = StoreLeaguerInfoMgr.getInstance().getStoreLeaguerInfo(storeId).getLeaguerInfoList();
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		Collection<ClerkInfo> clerks = storeClerkInfo.getClerkInfoMap().values();
		Set<Long> ids = new TreeSet<Long>();
		for (ClerkInfo clerkInfo : clerks) {
			ids.add(clerkInfo.getBuserId());
		}
		busers = BUserMgr.getInstance().findByMultitId(ids);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addOrderNotPay() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		addOrder();
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void addOrderHasPay() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Order order = addOrder();
		OrderUpdatePayItemApiForm updateData = OrderUpdatePayItemApiForm.newInstance();
		List<PayItem> payItems = new ArrayList<PayItem>();
		PayItem item = PayItem.newInstance();
		item.setCost(order.getCost());
		item.setPayType(RandomUtils.nextInt(0, PayTypeEnum.values().length));
		updateData.setPayItems(payItems);
		StoreOrderMgr.getInstance().updatePayItem(order.getId(), storeId, updateData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private Order addOrder(){
		ProductInfo info = productInfos.get(RandomUtils.nextInt(0, productInfos.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		BUser buser = busers.get(RandomUtils.nextInt(0, busers.size()));
		
		OrderItemAddForm formInfo = OrderItemAddForm.newInstance();
		
		OrderAddApiForm orderAddApiForm = OrderAddApiForm.newInstance();
		
		orderAddApiForm.setOrderType(OrderTypeEnum.PURCHASE.ordinal());
		orderAddApiForm.setLeaguerId(leaguer.getId());
		orderAddApiForm.setCost(RandomUtils.nextFloat(500f, 1000f));
		orderAddApiForm.setStoreId(storeId);
		orderAddApiForm.setCreatorId(boss.getId());
		List<BuyItem> buyItems = new ArrayList<BuyItem>();
		BuyItem item = BuyItem.newInstance();
		Set<Long> buserIds = new TreeSet<Long>();
		buserIds.add(buser.getId());
		item.setBuserIds(buserIds);
		item.setBuyType(BuyTypeEnum.PRODUCT.ordinal());
		item.setCost(orderAddApiForm.getCost());
		item.setCount(RandomUtils.nextInt(1, 3));
		item.setDiscount(0);
		item.setPay(orderAddApiForm.getCost());
		item.setPayName("现结");
		item.setPgId(info.getId()+"");
		item.setPgName(info.getName());
		item.setPrdCardId("");
		item.setPrice(orderAddApiForm.getCost());
		buyItems.add(item);
		orderAddApiForm.setBuyItems(buyItems);
		formInfo.setOrderAddApiForm(orderAddApiForm);
		
		Order order = StoreOrderMgr.getInstance().addOrder(formInfo);
		return order;
	}
}
