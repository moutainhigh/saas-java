package com.hq.chainStore.service.cardRecord.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.cardRecord.apiData.PlusCardRecordForm;
import com.hq.chainStore.service.cardRecord.apiData.QueryCardRecordForm;
import com.hq.chainStore.service.cardRecord.data.CardRecord;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class CardRecordMgrTest {
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
	public void testFindByCardId(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<CardRecord> list = CardRecordMgr.getInstance().findByCardId("_dis_21_2");
		System.out.println(list);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", list.size() > 0);
	}
	
	@Test
	public void testFindByCond(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		QueryCardRecordForm queryForm = QueryCardRecordForm.newInstance();
		long nowTime = System.currentTimeMillis();
		queryForm.setPageItemCount(20);
		queryForm.setPageNo(1);
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(nowTime);
		queryForm.setMinTime(nowTime - 100L * 24 * 3600 * 1000);
		List<CardRecord> list = CardRecordMgr.getInstance().findByCond(queryForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", list.size() > 0);
	}
	
	@Test
	public void testPlusCount(){
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PlusCardRecordForm plusCardRecordForm = PlusCardRecordForm.newInstance();
		List<String> cardIds = new ArrayList<String>();
		cardIds.add("_dis_21_4");
		cardIds.add("_mem_21_4");
		cardIds.add("_prd_21_9");
		plusCardRecordForm.setProductId(49L);
		plusCardRecordForm.setCardIds(cardIds);
		plusCardRecordForm.setLeaguerId("21_158");
		CardRecordMgr.getInstance().plusCount(plusCardRecordForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindLeaguerUsefulCards(){
		String leaguerId = "21_218";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Set<String> cardIds = new HashSet<String>();
		cardIds.add("_mem_21_9");
		cardIds.add("_mem_21_2");
		cardIds.add("_dis_21_20");
		cardIds.add("_dis_21_12");
		List<CardRecord> list = CardRecordMgr.getInstance().findLeaguerUsefulCards(cardIds, leaguerId);
		System.out.println(list.size());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
