package com.hq.storeClient.service.storeGoods.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeGoods.apiData.GoodsCancelTopForm;
import com.hq.storeClient.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeClient.service.storeGoods.apiData.StoreGoodsUpdateForm;
import com.hq.storeClient.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeClient.service.storeGoods.data.GoodsStateEnum;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreGoodsClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		StoreGoods storeGoods = StoreGoodsClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeGoods));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdateGoodsState() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		GoodsUpdateStateForm dataForm = GoodsUpdateStateForm.newInstance();
		dataForm.setGoodsId("11");
		dataForm.setState(GoodsStateEnum.Open.ordinal());
		StoreGoodsClientMgr.getInstance().updateGoodsState(storeId, dataForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testUpdate() {
		long storeId = 16052L;
//		String goodsId = Robot.getInstance().getRandomGoods(storeId).getId();
		String goodsId = "2";
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreGoodsUpdateForm dataForm = StoreGoodsUpdateForm.newInstance();
		
		//更新促销信息
//		GoodsDetail detail = GoodsDetailClientMgr.getInstance().getGoodsDetail(storeId, goodsId);
//		GoodsUpdateForm goodsUpdateForm = GoodsUpdateForm.newInstance();
//		FastBeanCopyer.getInstance().copy(detail, goodsUpdateForm);
//		goodsUpdateForm.setId(detail.getGoodsId());
//		goodsUpdateForm.setNumber("G0000002");
//		goodsUpdateForm.setPromotionFlag(PromotionFlagEnum.Yes.ordinal());
//		goodsUpdateForm.setPromotionPrice(RandomUtils.nextFloat(100f, 500f));
//		dataForm.setStoreGoodsUpdateType(StoreGoodsUpdateType.UpdateGoods);
//		dataForm.setGoodsUpdateForm(goodsUpdateForm);
		
//		GoodsAddToTopForm goodsAddToTopForm = GoodsAddToTopForm.newInstance();
//		goodsAddToTopForm.setGoodsId(goodsId);
//		dataForm.setStoreGoodsUpdateType(StoreGoodsUpdateType.AddGoodsToTop);
//		dataForm.setGoodsAddToTopForm(goodsAddToTopForm);
		
		GoodsCancelTopForm goodsCancelTopForm = GoodsCancelTopForm.newInstance();
		goodsCancelTopForm.setGoodsId(goodsId);
		dataForm.setStoreGoodsUpdateType(StoreGoodsUpdateType.CancelGoodsFromTop);
		dataForm.setGoodsCancelTopForm(goodsCancelTopForm);
		
		StoreGoodsClientMgr.getInstance().update(storeId, dataForm);
		
		ValidateThreadLocal.getInstance().remove();
	}

}
