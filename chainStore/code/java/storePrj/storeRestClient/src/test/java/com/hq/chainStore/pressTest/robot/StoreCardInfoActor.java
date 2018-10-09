package com.hq.chainStore.pressTest.robot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.PrdInCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.testClass.robot.storeCardInfo.StoreCardInfoRobot;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoActor {

	public static StoreCardInfoActor getInstance(){
		return HotSwap.getInstance().getSingleton(StoreCardInfoActor.class);
	}
	
	/**
	 * 查询
	 * @param storeId
	 * @return
	 */
	public StoreCardInfo get(long storeId){
		return StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
	}
	
	/**
	 * 获取店铺次卡列表
	 * @param storeId
	 * @return
	 */
	public List<ProductCard> getProductCardList(long storeId){
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		List<ProductCard> productCardList = new ArrayList<ProductCard>(storeCardInfo.getProductCardMap().values());
		return productCardList;
	}
	
	/**
	 * 随机获取店铺次卡
	 * @param storeId
	 * @return
	 */
	public ProductCard getRandomProductCard(long storeId){
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		List<ProductCard> productCardList = new ArrayList<ProductCard>(storeCardInfo.getProductCardMap().values());
		if(productCardList.size() > 0){
			return productCardList.get(RandomUtils.nextInt(0, productCardList.size()));
		}else{
			return null;
		}
	}
	
	/**
	 * 添加会员卡
	 * @param storeId
	 */
	public void addMemberCard(long storeId){
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		StoreCardInfo storeCardInfo = get(storeId);
		robot.getData().setNumber("number-"+RandomUtils.nextInt(0,1000));
		robot.addMembershipCard(storeId, storeCardInfo.getMembershipCardIndex()+1);
	}
	
	/**
	 * 添加次卡
	 * @param storeId
	 */
	public void addProductCard(long storeId){
		List<ProductInfo> productList = StoreProductInfoActor.getInstance().getProductList(storeId);
		if(productList.size() > 0){
			ProductInfo productInfo = productList.get(RandomUtils.nextInt(0,productList.size()));
			
			StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
			StoreCardInfo storeCardInfo = get(storeId);
			PrdInCard prdInCard = PrdInCard.newInstance();
	        prdInCard.setPrdId(Long.valueOf(productInfo.getId()));
			prdInCard.setCount(RandomUtils.nextInt(1, 10));
			prdInCard.setType(RandomUtils.nextInt(0, 2));
			robot.getData().getProductList().clear();
			robot.getData().getProductList().add(prdInCard);
			robot.getData().setNumber("number-" + storeCardInfo.getProductCardIndex()+1);
			robot.addProductCard(storeId, storeCardInfo.getProductCardIndex()+1, RandomUtils.nextInt(0, 3));
		}
	}
	
}
