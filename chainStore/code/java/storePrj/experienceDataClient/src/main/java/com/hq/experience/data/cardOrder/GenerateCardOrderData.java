package com.hq.experience.data.cardOrder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.cardOrder.apiData.AddCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.CancelCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.PayCardOrderForm;
import com.hq.chainStore.service.cardOrder.bs.CardOrderMgr;
import com.hq.chainStore.service.cardOrder.data.CardOrder;
import com.hq.chainStore.service.cardOrder.data.StoreCardTypeEnum;
import com.hq.chainStore.service.order.data.OrderStatusEnum;
import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

/**
 * 已经取消单独的耗卡订单
 * @author Administrator
 *
 */
@Deprecated
public class GenerateCardOrderData extends BaseGenerate{
	
	public static void main(String[] args) throws Exception {
		long phone = 13660623958L;
		String service = "http://192.168.10.170:9114/ws/v1";
		String reportService = "http://192.168.10.170:9117/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateCardOrderData().genData(phone);
	}
	
	public GenerateCardOrderData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				initData();
				genCardOrderData();
				genPayOrderData();
				genCancelOrderData();
			}
			System.out.println("Generate CardOrder Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<ProductCard> productCards;
	private List<Leaguer> leaguers;
	
	public void initData(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		productCards = new ArrayList<ProductCard>(StoreCardInfoMgr.getInstance().getStoreCardInfo(storeId).getProductCardMap().values());
		leaguers = StoreLeaguerInfoMgr.getInstance().getLeaguerList(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void genCardOrderData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCard productCard = productCards.get(RandomUtils.nextInt(0, productCards.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		
		AddCardOrderForm addForm = AddCardOrderForm.newInstance();
		addForm.setCardId(productCard.getId());
		addForm.setCardType(StoreCardTypeEnum.PRODUCTCARD.ordinal());
		addForm.setLeaguerId(leaguer.getId());
		addForm.setStoreId(storeId);
		addForm.setPrice(RandomUtils.nextFloat(1000f, 5000f));
		CardOrderMgr.getInstance().addCardOrder(addForm);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void genPayOrderData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCard productCard = productCards.get(RandomUtils.nextInt(0, productCards.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		
		AddCardOrderForm addForm = AddCardOrderForm.newInstance();
		addForm.setCardId(productCard.getId());
		addForm.setCardType(StoreCardTypeEnum.PRODUCTCARD.ordinal());
		addForm.setLeaguerId(leaguer.getId());
		addForm.setStoreId(storeId);
		addForm.setPrice(RandomUtils.nextFloat(1000f, 5000f));
		CardOrder cardOrder = CardOrderMgr.getInstance().addCardOrder(addForm);
		
		PayCardOrderForm formData = PayCardOrderForm.newInstance();
		formData.setCost(RandomUtils.nextFloat(1000f, 5000f));
		formData.setPayType(OrderStatusEnum.HAS_PAY.ordinal());
		formData.setPayType(RandomUtils.nextInt(1, 5));
		CardOrderMgr.getInstance().payCardOrder(cardOrder.getId(), storeId, formData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void genCancelOrderData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCard productCard = productCards.get(RandomUtils.nextInt(0, productCards.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		
		AddCardOrderForm addForm = AddCardOrderForm.newInstance();
		addForm.setCardId(productCard.getId());
		addForm.setCardType(StoreCardTypeEnum.PRODUCTCARD.ordinal());
		addForm.setLeaguerId(leaguer.getId());
		addForm.setStoreId(storeId);
		addForm.setPrice(RandomUtils.nextFloat(1000f, 5000f));
		CardOrder cardOrder = CardOrderMgr.getInstance().addCardOrder(addForm);
		
		CancelCardOrderForm formData = CancelCardOrderForm.newInstance();
		formData.setStatus(OrderStatusEnum.CANCEL.ordinal());
		CardOrderMgr.getInstance().cancelCardOrder(cardOrder.getId(), storeId, formData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
	
