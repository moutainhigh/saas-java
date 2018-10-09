package com.hq.chainClient.service.sellProduct.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chain.data.ChainStore;
import com.hq.chainClient.service.chainGoods.data.Goods;
import com.hq.chainClient.service.sellProduct.apiData.SellAllotId;
import com.hq.chainClient.service.sellProduct.apiData.SellProductAllotForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductQueryForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductUpdateStateForm;
import com.hq.chainClient.service.sellProduct.data.SellProduct;
import com.hq.chainClient.service.sellProduct.data.SellProductStateEnum;
import com.hq.chainClient.service.sellProduct.data.SellProductTypeEnum;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class SellProductClientMgrTest {
	private static Boss boss;
	private static long chainId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(18163912097L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}
	
	@Test
	public void testGetPageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		SellProductQueryForm queryForm = SellProductQueryForm.newInstance();
		queryForm.setChainId(chainId);
		PageResp<SellProduct> pageInfo = SellProductClientMgr.getInstance().getPageInfo(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(pageInfo));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAllotSellProduct() {
		Goods goods = boss.getRandomGoods(chainId);
		ChainStore chainStore = boss.getRandomChainStore(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		SellProductAllotForm inputForm = SellProductAllotForm.newInstance();
		inputForm.getApplyStoreIds().add(chainStore.getStoreId());
		inputForm.setSellAllotId(SellAllotId.newInstance(SellProductTypeEnum.GOODS.ordinal(), goods.getId()));
		SellProductClientMgr.getInstance().allotSellProduct(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateSellProductState() {
		Goods goods = boss.getRandomGoods(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		SellProductUpdateStateForm inputForm = SellProductUpdateStateForm.newInstance();
		inputForm.setState(SellProductStateEnum.DOWN.ordinal());
		inputForm.setSellAllotId(SellAllotId.newInstance(SellProductTypeEnum.GOODS.ordinal(), goods.getId()));
		SellProductClientMgr.getInstance().updateSellProductState(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
}
