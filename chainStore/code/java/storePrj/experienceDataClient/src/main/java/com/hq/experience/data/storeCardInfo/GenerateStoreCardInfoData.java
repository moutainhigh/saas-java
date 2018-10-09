package com.hq.experience.data.storeCardInfo;

import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.experienceData.TMembershipCard;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateStoreCardInfoData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623953L;
		
//		String storeService = "http://192.168.10.169:9110/storems/ws/v1";
//		String orderService = "http://192.168.10.169:9110/storereportms/ws/v1";
		
		String storeService = "https://www.zhimeitimes.com:9129/storems/ws/v1";
		String orderService = "https://www.zhimeitimes.com:9129/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateStoreCardInfoData().genData(phone);
	}
	
	public GenerateStoreCardInfoData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genStoreCardInfoData();
			}
			System.out.println("Generate CardInfo Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreCardInfoData() {
		List<TMembershipCard> membershipCards= TMembershipCard.buildMembershipCardList();
		for (TMembershipCard tCardInfo : membershipCards) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreCardInfo cardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
			AddMembershipCard param = AddMembershipCard.newInstance();
			FastBeanCopyer.getInstance().copy(tCardInfo, param);
			param.setIndex(cardInfo.getMembershipCardIndex()+1);
			StoreCardInfoMgr.getInstance().addMembershipCard(storeId, param);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
		
//		List<TProductCard> productCards = TProductCard.buildProductCardList();
//		for (TProductCard tCardInfo : productCards) {
//			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//			StoreCardInfo cardInfo = StoreCardInfoMgr.getInstance().getStoreCardInfo(storeId);
//			List<ProductInfo> products = StoreProductInfoMgr.getInstance().getProductList(storeId);
//			ProductInfo productInfo = products.get(RandomUtils.nextInt(0,products.size()));
//			AddProductCardForm addProductCardForm = AddProductCardForm.newInstance();
//			FastBeanCopyer.getInstance().copy(tCardInfo, addProductCardForm);
//			addProductCardForm.setIndex(cardInfo.getProductCardIndex()+1);
//			List<PrdInCard> productList = new ArrayList<PrdInCard>();
//			PrdInCard prdInCard = PrdInCard.newInstance();
//			prdInCard.setPrdId(productInfo.getId());
//			prdInCard.setType(RandomUtils.nextInt(0, 2));
//			prdInCard.setCount(RandomUtils.nextInt(1, 20));
//			productList.add(prdInCard);
//			addProductCardForm.setProductList(productList);
//			StoreCardInfoMgr.getInstance().addProductCard(storeId, addProductCardForm);
//			AccessTokenMgr.getInstance().removeOpIdTL();
//		}
	}
}
