package com.hq.testClass.robot.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.hq.chainStore.service.bonus.data.BonusTypeEnum;
import com.hq.chainStore.service.order.apiData.OrderAddApiForm;
import com.hq.chainStore.service.order.apiData.OrderItemAddForm;
import com.hq.chainStore.service.order.data.BuyItem;
import com.hq.chainStore.service.order.data.BuyTypeEnum;
import com.hq.chainStore.service.order.data.OrderTypeEnum;
import com.hq.chainStore.service.order.data.PayItem;
import com.hq.chainStore.service.order.data.PayTypeEnum;
import com.hq.chainStore.service.order.data.RechargeItem;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.LimitUnitEnum;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.chainStore.service.workFlowData.data.PrdCardPayEnum;
import com.hq.chainStore.service.workFlowData.data.UserBonus;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderRobot {
	public static OrderRobot getInstance() {
		return HotSwap.getInstance().getSingleton(OrderRobot.class);
	}
	
	//购买消费
	public OrderItemAddForm buildOrderItemAddForm(List<Goods> goods, List<ProductInfo> products, List<ProductCard> procCards, List<ClerkInfo> clerkInfos, List<Leaguer> leaguers){
		OrderItemAddForm addForm = OrderItemAddForm.newInstance();
		//选择客户
		Leaguer leaguer =  leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		
		OrderAddApiForm formInfo = OrderAddApiForm.newInstance();
		formInfo.setOrderType(OrderTypeEnum.PURCHASE.ordinal());
		formInfo.setLeaguerId(leaguer.getId());
		formInfo.setCost(RandomUtils.nextFloat(1000f, 2000f));
		formInfo.setBuyItems(buildBuyItems(goods, products, procCards,clerkInfos));
		addForm.setOrderAddApiForm(formInfo);
		
		if(clerkInfos != null && clerkInfos.size() > 0) {
			List<BonusInfoAddForm> bonusInfoAddForms = buildBonusInfoAddForms(formInfo.getBuyItems(), clerkInfos);
			addForm.setBonusInfoAddForms(bonusInfoAddForms);
		}
		
		List<PayItem> payItems = buildPayItems(formInfo.getCost());
		addForm.setPayItems(payItems);
		return addForm;
	}
	
	//会员充值
	public OrderItemAddForm buildRechargeItemAddForm(List<Goods> goods, List<ProductInfo> products, List<ProductCard> procCards, List<ClerkInfo> clerkInfos, List<Leaguer> leaguers){
		OrderItemAddForm addForm = OrderItemAddForm.newInstance();
		//选择客户
		Leaguer leaguer =  leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		
		OrderAddApiForm formInfo = OrderAddApiForm.newInstance();
		formInfo.setOrderType(OrderTypeEnum.RECHARGE.ordinal());
		formInfo.setLeaguerId(leaguer.getId());
		formInfo.setRechargeItems(buildRechargeItems(goods, products, procCards));
		formInfo.setCost(RandomUtils.nextFloat(1000f, 2000f));
		addForm.setOrderAddApiForm(formInfo);
		List<BonusInfoAddForm> bonusInfoAddForms = buildBonusInfoAddForms(formInfo.getBuyItems(), clerkInfos);
		addForm.setBonusInfoAddForms(bonusInfoAddForms);
		
		List<PayItem> payItems = buildPayItems(formInfo.getCost());
		addForm.setPayItems(payItems);
		return addForm;
	}
	
	public List<BonusInfoAddForm> buildBonusInfoAddForms(List<BuyItem> buyItems, List<ClerkInfo> clerkInfos){
		List<BonusInfoAddForm> bonusInfoAddForms = new ArrayList<BonusInfoAddForm>();
		for (BuyItem item : buyItems) {
			ClerkInfo clerkInfo = clerkInfos.get(RandomUtils.nextInt(0, clerkInfos.size()));
			ClerkInfo clerkInfo2 = clerkInfos.get(RandomUtils.nextInt(0, clerkInfos.size()));
			
			BonusInfoAddForm addForm = BonusInfoAddForm.newInstance();
			addForm.setBuyType(item.getBuyType());
			addForm.setPgId(item.getPgId());
			int prdCardPayType = PrdCardPayEnum.CashPay.ordinal();
			if(StringUtils.isNoneBlank(item.getPrdCardId())){
				prdCardPayType=PrdCardPayEnum.PrdCard.ordinal();
			}
			addForm.setPrdCardPayType(prdCardPayType);
			addForm.setProductCardId(item.getPrdCardId());
			addForm.setPgName(item.getPgName());
			
			Map<Long, UserBonus> userBonusMap = new HashMap<Long, UserBonus>();
			UserBonus userBonus = UserBonus.newInstance();
			userBonus.setAmount(RandomUtils.nextFloat(1000f, 2000f));
			userBonus.setBonusType(RandomUtils.nextInt(0, BonusTypeEnum.values().length));
			userBonus.setBuserId(clerkInfo.getBuserId());
			userBonus.setCost(RandomUtils.nextFloat(1000f, 2000f));
			userBonus.setPercentage(RandomUtils.nextFloat(0.1f, 0.5f));
			userBonusMap.put(userBonus.getBuserId(), userBonus);
			
			UserBonus userBonus2 = UserBonus.newInstance();
			userBonus2.setAmount(RandomUtils.nextFloat(1000f, 2000f));
			userBonus2.setBonusType(RandomUtils.nextInt(0, BonusTypeEnum.values().length));
			userBonus2.setBuserId(clerkInfo2.getBuserId());
			userBonus2.setCost(RandomUtils.nextFloat(1000f, 2000f));
			userBonus2.setPercentage(RandomUtils.nextFloat(0.1f, 0.5f));
			userBonusMap.put(userBonus2.getBuserId(), userBonus2);
			
			addForm.setUserBonusMap(userBonusMap);
			
			bonusInfoAddForms.add(addForm);
		}
		return bonusInfoAddForms;
	}
	
	public List<BuyItem> buildBuyItems(List<Goods> goods, List<ProductInfo> products, List<ProductCard> procCards, List<ClerkInfo> clerkInfos){
		List<BuyItem> buyItems = new ArrayList<BuyItem>();
		
		Set<Long> buserIds = new HashSet<Long>();
		if(!CollectionUtils.isEmpty(clerkInfos)){
			buserIds.add(clerkInfos.get(RandomUtils.nextInt(0, clerkInfos.size())).getBuserId());
		}
		
		if(!CollectionUtils.isEmpty(goods)){
			Goods good = goods.get(RandomUtils.nextInt(0, goods.size())); 
			buyItems.add(buildBuyItem(BuyTypeEnum.GOODS.ordinal(), good.getName(), String.valueOf(good.getId()), "现结", "", buserIds));
		}
		
		
		if(!CollectionUtils.isEmpty(products)){
			ProductInfo productInfo = products.get(RandomUtils.nextInt(0, products.size())); 
			buyItems.add(buildBuyItem(BuyTypeEnum.PRODUCT.ordinal(), productInfo.getName(), String.valueOf(productInfo.getId()), "现结", "", buserIds));
		}
		
		if(!CollectionUtils.isEmpty(procCards)){
			ProductCard productCard = procCards.get(RandomUtils.nextInt(0, procCards.size())); 
			buyItems.add(buildBuyItem(BuyTypeEnum.PRDCARD.ordinal(), productCard.getName(), productCard.getId(), "现结", "", buserIds));
		}
		
//		if(!CollectionUtils.isEmpty(products) && !CollectionUtils.isEmpty(procCards)){
//			Map<Long, ProductInfo> map = new HashMap<Long, ProductInfo>();
//			for (ProductInfo info : products) {
//				map.put(info.getId(), info);
//			}
//			ProductCard productCard2 = procCards.get(RandomUtils.nextInt(0, procCards.size())); 
//			List<Long> productIds = new ArrayList<Long>(productCard2.getProductMap().keySet());
//			ProductInfo productInfo2 = null;
//			for (Long id : productIds) {
//				if(map.get(id) != null){
//					productInfo2 = map.get(id);
//					break;
//				}
//			}
//			buyItems.add(buildBuyItem(BuyTypeEnum.PRODUCT.ordinal(), productInfo2.getName(), String.valueOf(productInfo2.getId()), productCard2.getName(), productCard2.getId(), buserIds));
//		}
		
		return buyItems;
	}
	
	public List<RechargeItem> buildRechargeItems(List<Goods> goods, List<ProductInfo> products, List<ProductCard> procCards){
		List<RechargeItem> rechargeItems = new ArrayList<RechargeItem>();
		RechargeItem item = RechargeItem.newInstance();
		item.setAmount(RandomUtils.nextFloat(1000f, 2000f));
		item.setLargess(RandomUtils.nextFloat(1000f, 2000f));
		item.setLimitTime(RandomUtils.nextInt(1, 10));
		item.setLimitUnit(LimitUnitEnum.YEAR.ordinal());
		item.setMembershipCardId("_mem_21_22");
		item.setMembershipCardName("name746");
		item.setNumber(RandomStringUtils.random(10, "0123456789"));
		item.setPay(RandomUtils.nextFloat(1000f, 2000f));
		rechargeItems.add(item);
		return rechargeItems;
	}
	
	public BuyItem buildBuyItem(int butType, String pgName, String pgId, String payName, String prdCardId, Set<Long> buserIds){
		if(StringUtils.isNoneBlank(prdCardId)){
			payName=payName+"[划卡]";
		}
		BuyItem buyItem = BuyItem.newInstance();
		buyItem.setBuyType(butType);
		buyItem.setCost(RandomUtils.nextFloat(1000f, 2000f));
		buyItem.setCount(RandomUtils.nextInt(1, 5));
		buyItem.setDiscount(RandomUtils.nextFloat(0.1f, 0.5f));
		buyItem.setPay(RandomUtils.nextFloat(1000f, 2000f));
		buyItem.setPayName(payName);
		buyItem.setPgId(pgId);
		buyItem.setPgName(pgName);
		buyItem.setPrdCardId(prdCardId);
		buyItem.setPrice(RandomUtils.nextFloat(1000f, 2000f));
		buyItem.setBuserIds(buserIds);
		return buyItem;
	}
	
	public List<PayItem> buildPayItems(float cost){
		List<PayItem> payItems = new ArrayList<PayItem>();
		PayItem payItem = PayItem.newInstance();
//		payItem.setCost(RandomUtils.nextFloat(100f, 200f));
		payItem.setCost(cost);
//		payItem.setPayType(RandomUtils.nextInt(0, PayTypeEnum.values().length));
		payItem.setPayType(PayTypeEnum.CASH.ordinal());
		payItems.add(payItem);
		return payItems;
	}
	
}
