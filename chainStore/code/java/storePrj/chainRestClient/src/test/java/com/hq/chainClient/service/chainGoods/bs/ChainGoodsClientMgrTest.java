package com.hq.chainClient.service.chainGoods.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.chainGoods.apiData.GoodsAddForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsRemoveForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsTypeAddForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsUpdateForm;
import com.hq.chainClient.service.chainGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.Goods;
import com.hq.chainClient.service.chainGoods.data.GoodsStateEnum;
import com.hq.chainClient.service.chainGoods.data.GoodsType;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.hq.chainClient.testClass.robot.chainGoods.ChainGoodsRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ChainGoodsClientMgrTest {
	private static Boss boss;
	private static long chainId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}
	
	@Test
	public void testAddGoodsType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainGoods chainData = ChainGoodsClientMgr.getInstance().get(chainId);
		GoodsTypeAddForm inputForm = GoodsTypeAddForm.newInstance();
		inputForm.setIndex(chainData.getGoodsTypeIdIndex()+1);
		inputForm.setName("商品分类"+RandomUtils.nextInt(1, 100));
		ChainGoodsClientMgr.getInstance().addGoodsType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateGoodsType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsTypeUpdateForm inputForm = GoodsTypeUpdateForm.newInstance();
		inputForm.setId("_cgti_1");
		inputForm.setName("修改商品分类");
		ChainGoodsClientMgr.getInstance().updateGoodsType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveGoodsType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsTypeRemoveForm inputForm = GoodsTypeRemoveForm.newInstance();
		inputForm.setId("_cgti_1");
		ChainGoodsClientMgr.getInstance().removeGoodsType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddGoods() {
		GoodsType type = boss.getRandomGoodsType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainGoods chainData = ChainGoodsClientMgr.getInstance().get(chainId);
		GoodsAddForm inputForm = ChainGoodsRobotData.newRandomInstance().toGoodsAddForm(chainData.getGoodsIdIndex()+1, type.getId());
		ChainGoodsClientMgr.getInstance().addGoods(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateGoods() {
		Goods goods = boss.getRandomGoods(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsUpdateForm inputForm = GoodsUpdateForm.newInstance();
		FastBeanCopyer.getInstance().copy(goods, inputForm);
		inputForm.setName("傻姑娘");
		ChainGoodsClientMgr.getInstance().updateGoods(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveGoods() {
		Goods goods = boss.getRandomGoods(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsRemoveForm inputForm = GoodsRemoveForm.newInstance();
		inputForm.setId(goods.getId());
		ChainGoodsClientMgr.getInstance().removeGoods(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateGoodsState() {
		Goods goods = boss.getRandomGoods(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsUpdateStateForm inputForm = GoodsUpdateStateForm.newInstance();
		inputForm.setId(goods.getId());
		inputForm.setState(GoodsStateEnum.Open.ordinal());
		ChainGoodsClientMgr.getInstance().updateGoodsState(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHolder() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		String id = "_cgti_1_5";
		Goods goods = ChainGoodsClientMgr.getInstance().get(chainId).takeGoodsById(id);
		System.out.println(JsonUtil.getInstance().toJson(goods));
		
		GoodsUpdateStateForm inputForm = GoodsUpdateStateForm.newInstance();
		inputForm.setId(goods.getId());
		inputForm.setState(GoodsStateEnum.Open.ordinal());
		ChainGoodsClientMgr.getInstance().updateGoodsState(chainId, inputForm);
		
		Goods goods2 = ChainGoodsClientMgr.getInstance().get(chainId).takeGoodsById(id);
		System.out.println(JsonUtil.getInstance().toJson(goods2));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
}
