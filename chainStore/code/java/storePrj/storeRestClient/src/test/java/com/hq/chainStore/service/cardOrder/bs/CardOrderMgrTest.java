package com.hq.chainStore.service.cardOrder.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.cardOrder.apiData.CardOrderQueryForm;
import com.hq.chainStore.service.cardOrder.data.CardOrder;
import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.cardOrder.CardOrderRobot;


public class CardOrderMgrTest {
	private static Boss boss;
	private static long storeId=21L;

	private static List<ProductCard> productCards = null;
	private static List<Leaguer> leaguers = null;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(storeId);
		boss.login();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		productCards = new ArrayList<ProductCard>(StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId).getProductCardMap().values());
		leaguers = StoreLeaguerInfoMgr.getInstance().getLeaguerList(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testT(){
		
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCard productCard = productCards.get(RandomUtils.nextInt(0, productCards.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		CardOrderRobot robot = CardOrderRobot.newRandom();
		CardOrder cardOrder = robot.addCardOrder(productCard.getId(), leaguer.getId(), storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertNotNull("应该非空", cardOrder);
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		CardOrderRobot robot = CardOrderRobot.newRandom();
		CardOrder cardOrder = robot.getCardOrder(1L);
		System.out.println(cardOrder);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertNotNull("应该非空", cardOrder);
	}
	
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ProductCard productCard = productCards.get(RandomUtils.nextInt(0, productCards.size()));
		Leaguer leaguer = leaguers.get(RandomUtils.nextInt(0, leaguers.size()));
		CardOrderRobot robot = CardOrderRobot.newRandom();
		CardOrder cardOrder = robot.addCardOrder(productCard.getId(), leaguer.getId(), storeId);
		System.out.println(cardOrder);
		robot.payCardOrder(cardOrder.getId(), storeId);
		CardOrder cardOrder2 = robot.getCardOrder(cardOrder.getId());
		System.out.println(cardOrder2);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertNotNull("应该非空", cardOrder2);
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		CardOrderRobot robot = CardOrderRobot.newRandom();
		long now = System.currentTimeMillis();
		long minTime = now - 30L * 24 * 3600 * 1000;
		long maxTime = now + 3600*1000;
		
		CardOrderQueryForm params = CardOrderQueryForm.newInstance();
		params.setStoreId(storeId);
		params.setMaxTime(maxTime);
		params.setMinTime(minTime);
		params.setPageItemCount(20);
		params.setPageNo(1);
		
		List<CardOrder> list = robot.findCardOrderList(params);
		System.out.println(list);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("订单条数大于0", list.size() > 0);
	}

}
