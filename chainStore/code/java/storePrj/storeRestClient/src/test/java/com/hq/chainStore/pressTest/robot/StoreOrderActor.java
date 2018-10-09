package com.hq.chainStore.pressTest.robot;

import java.util.List;

import com.hq.chainStore.service.order.apiData.OrderItemAddForm;
import com.hq.chainStore.service.order.bs.StoreOrderMgr;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.testClass.robot.order.OrderRobot;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreOrderActor {

	public static StoreOrderActor getInstance(){
		return HotSwap.getInstance().getSingleton(StoreOrderActor.class);
	}
	
	/**
	 * 添加订单
	 * @param storeId
	 * @param bossId
	 */
	public void addOrder(long storeId,long bossId){
		List<Goods> goods = StoreGoodsActor.getInstance().getGoodsList(storeId); 
		List<ProductInfo> products = StoreProductInfoActor.getInstance().getProductList(storeId); 
		List<ProductCard> procCards = StoreCardInfoActor.getInstance().getProductCardList(storeId); 
		List<ClerkInfo> clerkInfos = StoreClerkInfoActor.getInstance().getClerkList(storeId);  
		List<Leaguer> leaguers = StoreLeaguerInfoActor.getInstance().getLeaguerList(storeId);
		
		if(!leaguers.isEmpty() && (!products.isEmpty() || !procCards.isEmpty() || !clerkInfos.isEmpty())){
			OrderItemAddForm orderItemAddForm = OrderRobot.getInstance().buildOrderItemAddForm(goods, products, procCards, clerkInfos, leaguers);
//			OrderItemAddForm orderItemAddForm = OrderRobot.getInstance().buildOrderItemAddForm(goods, products, null, clerkInfos, leaguers);
//			OrderItemAddForm orderItemAddForm = OrderRobot.getInstance().buildRechargeItemAddForm(goods, products, procCards, clerkInfos, leaguers);
//			System.out.println(JsonUtil.getInstance().toJson(orderItemAddForm));
			orderItemAddForm.getOrderAddApiForm().setStoreId(storeId);
			orderItemAddForm.getOrderAddApiForm().setCreatorId(bossId);
			StoreOrderMgr.getInstance().addOrder(orderItemAddForm);
		}
	}
	
}
