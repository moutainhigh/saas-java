package com.hq.chainStore.service.chainPackageProject.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainStore.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ChainPackageProjectMgrTest {
	private static Boss boss;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testGet() {
		long chainId=1L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainPackageProject data = ChainPackageProjectMgr.getInstance().get(chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindPackageProjectDetail() {
		long chainId=1L;
		String id="_cppi_1_5";
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectDetail data = ChainPackageProjectMgr.getInstance().findPackageProjectDetail(id, chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
