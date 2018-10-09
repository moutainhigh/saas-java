package com.hq.testClass.holderSynTest;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfoSynDataHolder;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfoSynDataHolder;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.storeCardInfo.StoreCardInfoRobot;

public class HolderSynTest {

	private static Boss boss;
	private static long storeId=21L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(storeId);
		boss.login();
	}
	
	@Test
	public void testGetAll() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreCardInfo cardInfo = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		
		StoreCardInfoSynDataHolder.getInstance().getData(boss.getId(), String.valueOf(storeId));
		StoreLeaguerInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		StoreProductInfoSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), String.valueOf(storeId));
		
		StoreCardInfoRobot robot = StoreCardInfoRobot.newRandom();
		robot.addDiscountCard(storeId,cardInfo.getDiscountCardIdIndex()+1);
		
		StoreCardInfoSynDataHolder.getInstance().getData(boss.getId(), String.valueOf(storeId));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
