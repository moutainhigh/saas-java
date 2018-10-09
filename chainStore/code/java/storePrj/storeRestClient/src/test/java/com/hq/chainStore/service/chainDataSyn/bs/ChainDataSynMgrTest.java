package com.hq.chainStore.service.chainDataSyn.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.chainStore.service.chainDataSyn.data.GoodsSyn;
import com.hq.chainStore.service.chainDataSyn.data.ProductSyn;
import com.hq.chainStore.service.common.PageResp;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ChainDataSynMgrTest {
	private static Boss boss;
	private static long storeId=0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testFindChainGoods() {
		long chainId=2L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setStoreId(storeId).setSynStatus(-1);
		PageResp<GoodsSyn> goodsPage = ChainDataSynMgr.getInstance().findChainGoods(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(goodsPage));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindChainProduct() {
		long chainId=2L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setStoreId(storeId);
		PageResp<ProductSyn> page = ChainDataSynMgr.getInstance().findChainProduct(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
