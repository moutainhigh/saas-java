package com.hq.storeClient.service.storeCardInfo.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeCardInfo.apiData.CancelPrdCardTop;
import com.hq.storeClient.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;
import com.hq.storeClient.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.data.CardStatusEnum;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreCardInfoClientMgrTest {

	private static long storeId = 16052L;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo storeData = StoreCardInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeData));
	}

	@Test
	public void testUpdMemberCardState() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		UpdMemberCardStateData updateMemberCardStateData = UpdMemberCardStateData.newInstance();
		updateMemberCardStateData.setId("_mem_16052_1");
		updateMemberCardStateData.setStoreId(storeId);
		updateMemberCardStateData.setState(CardStatusEnum.OPEN.ordinal());
		StoreCardInfoClientMgr.getInstance().updMemberCardState(storeId, updateMemberCardStateData);
	}

	@Test
	public void testUpdProductCardState() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		UpdProductCardStateData updateProductCardStateData = UpdProductCardStateData.newInstance();
		updateProductCardStateData.setId("_prd_16052_2");
		updateProductCardStateData.setStoreId(storeId);
		updateProductCardStateData.setState(CardStatusEnum.OPEN.ordinal());
		StoreCardInfoClientMgr.getInstance().updProductCardState(storeId, updateProductCardStateData);
	}

	@Test
	public void testProductCard() {
		long storeId = 16052L;
		String id = "_prd_16052_1";
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfoUpdateApiForm apiForm = StoreCardInfoUpdateApiForm.newInstance();
		
//		ProductCardDetail detail = ProductCardDetailClientMgr.getInstance().getProductCardDetail(storeId, id);
//		UpdProductCardForm updProductCard = UpdProductCardForm.newInstance();
//		FastBeanCopyer.getInstance().copy(detail, updProductCard);
//		updProductCard.setPromotionFlag(PromotionFlagEnum.Yes.ordinal());
//		updProductCard.setPromotionPrice(300f);
//		apiForm.setStoreCardInfoUpdateType(StoreCardInfoUpdateType.UpdProductCard);
//		apiForm.setUpdProductCard(updProductCard);
		
//		AddPrdCardTop addPrdCardTop = AddPrdCardTop.newInstance();
//		addPrdCardTop.setId(id);
//		apiForm.setStoreCardInfoUpdateType(StoreCardInfoUpdateType.AddPrdCardTop);
//		apiForm.setAddPrdCardTop(addPrdCardTop);
		
		CancelPrdCardTop cancelPrdCardTop = CancelPrdCardTop.newInstance();
		cancelPrdCardTop.setId(id);
		apiForm.setStoreCardInfoUpdateType(StoreCardInfoUpdateType.CancelPrdCardTop);
		apiForm.setCancelPrdCardTop(cancelPrdCardTop);
		
		StoreCardInfoClientMgr.getInstance().updateStoreCardInfo(storeId, apiForm);
		ValidateThreadLocal.getInstance().remove();
	}

}
