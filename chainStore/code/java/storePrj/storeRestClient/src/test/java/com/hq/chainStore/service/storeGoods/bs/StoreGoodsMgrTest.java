package com.hq.chainStore.service.storeGoods.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.hq.chainStore.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.chainStore.service.goodsDetail.data.GoodsDetail;
import com.hq.chainStore.service.storeGoods.apiData.GoodsAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsRemoveForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsUpdateForm;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.storeGoods.data.GoodsType;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.chainStore.service.storeGoods.data.StoreGoodsSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.hq.testClass.robot.storeGoods.StoreGoodsRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class StoreGoodsMgrTest {
	private static Boss boss;
	private static long storeId=0L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	/**********************************商品分类操作**********************************/
	@Test
	public void testAddGoodsType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreGoods storeGoods = StoreGoodsSynDataHolder.getInstance().getStoreGoods(boss.getId(), storeId);
		GoodsTypeAddForm dataForm = GoodsTypeAddForm.newInstance();
		dataForm.setIndex(storeGoods.getGoodsTypeIdIndex()+1);
		dataForm.setName("测试分类");
		StoreGoodsMgr.getInstance().addGoodsType(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelGoodsType() {
		GoodsType goodsType = boss.getRandomGoodsType(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsTypeRemoveForm dataForm = GoodsTypeRemoveForm.newInstance();
		dataForm.setGoodsTypeId(goodsType.getId());
		System.out.println(goodsType.getId());
		StoreGoodsMgr.getInstance().removeGoodsType(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdGoodsType() {
		GoodsType goodsType = boss.getRandomGoodsType(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsTypeUpdateForm dataForm = GoodsTypeUpdateForm.newInstance();
		dataForm.setId(goodsType.getId());
		System.out.println(goodsType.getId());
		dataForm.setName("测试修改分类");
		StoreGoodsMgr.getInstance().updateGoodsType(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	/**********************************商品操作**********************************/
	@Test
	public void testAddGoods() {
		GoodsType type = boss.getRandomGoodsType(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
		GoodsAddForm dataForm = GoodsAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(StoreGoodsRobotData.newInstance(RandomUtils.nextInt(1, 10)), dataForm);
		dataForm.setIndex(storeGoods.getGoodsIdIndex()+1);
		dataForm.setTypeId(type.getId());
		StoreGoodsMgr.getInstance().addGoods(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelGoods() {
		Goods goods = boss.getRandomGoods(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsRemoveForm dataForm = GoodsRemoveForm.newInstance();
		dataForm.setGoodsId(goods.getId());
		System.out.println(goods.getId());
		StoreGoodsMgr.getInstance().removeGoods(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateGoods() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		GoodsDetail detail = GoodsDetailMgr.getInstance().getGoodsDetail(storeId, "1");
		GoodsUpdateForm dataForm = GoodsUpdateForm.newInstance();
		BeanUtils.copyProperties(detail, dataForm, "id");
		dataForm.setId(detail.getGoodsId());
		dataForm.setName("商品修改名称11111");
		dataForm.setDescript("rrrrrrrrr");
		StoreGoodsMgr.getInstance().updateGoods(storeId, dataForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	

}
