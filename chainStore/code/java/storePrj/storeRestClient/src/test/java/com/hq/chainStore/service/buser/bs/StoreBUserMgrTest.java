package com.hq.chainStore.service.buser.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.StoreBUser;
import com.hq.chainStore.service.buser.data.StoreBUserDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class StoreBUserMgrTest {
	private static Boss boss;
	private static long storeId=21L;

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

	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		StoreBUser storeBUser = StoreBUserMgr.getInstance().get(storeId);
		StoreBUser storeBUser = StoreBUserDataHolder.getInstance().getData(boss.getId()+"", storeId+"", storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeBUser));
		
		BUser bUser = BUserMgr.getInstance().get(boss.getId());
		BUserUpdateInfoApiData updateInfoData = BUserUpdateInfoApiData.newInstance();
		FastBeanCopyer.getInstance().copy(bUser, updateInfoData);
		updateInfoData.setBuserId(bUser.getId());
		updateInfoData.setAge(RandomUtils.nextInt(20, 50));
		BUserMgr.getInstance().updateInfo(boss.getId(), updateInfoData);
		
		StoreBUserDataHolder.getInstance().getData(boss.getId()+"", storeId+"", storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
