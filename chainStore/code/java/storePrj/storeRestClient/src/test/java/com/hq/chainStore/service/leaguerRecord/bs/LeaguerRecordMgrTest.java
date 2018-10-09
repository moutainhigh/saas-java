package com.hq.chainStore.service.leaguerRecord.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordAddForm;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordUpdateForm;
import com.hq.chainStore.service.leaguerRecord.data.LeaguerRecord;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class LeaguerRecordMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

//	@Ignore
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRecordAddForm inputForm = LeaguerRecordAddForm.newInstance();
		inputForm.setOrderId(1761L);
		inputForm.setOrderContent("测试订单内容");
		inputForm.setContent("content"+RandomUtils.nextInt(10, 100));
		inputForm.setLeaguerId("21_66813");
		inputForm.setTitle("title"+RandomUtils.nextInt(10, 100));
		LeaguerRecord record = LeaguerRecordMgr.getInstance().addLeaguerRecord(inputForm);
		System.out.println(JsonUtil.getInstance().toJson(record));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Ignore
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long leaguerRecordId = 3L;
		LeaguerRecordUpdateForm inputForm = LeaguerRecordUpdateForm.newInstance();
		inputForm.setContent("修改content"+RandomUtils.nextInt(10, 100));
		inputForm.setTitle("修改title"+RandomUtils.nextInt(10, 100));
		LeaguerRecordMgr.getInstance().updateInfo(storeId, leaguerRecordId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long leaguerRecordId = 2L;
		LeaguerRecordMgr.getInstance().deleteLeaguerRecord(storeId, leaguerRecordId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long leaguerRecordId = 3L;
		LeaguerRecord LeaguerRecord = LeaguerRecordMgr.getInstance().getLeaguerRecord(storeId, leaguerRecordId);
		System.out.println(JsonUtil.getInstance().toJson(LeaguerRecord));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	@Test
	public void testPageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerRecordQueryForm queryForm = LeaguerRecordQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<LeaguerRecord> pageResp = LeaguerRecordMgr.getInstance().getLeaguerRecordPageInfo(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(pageResp));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
