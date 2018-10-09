package com.hq.chainStore.service.storePackageProject.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.chainStore.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.chainStore.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.chainStore.service.storePackageProject.data.PackageProject;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;

public class PackageProjectDetailMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}

	@Test
	public void testGet() {
		PackageProject packageProject = boss.getRandomPackageProject(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String id = packageProject.getId();
		PackageProjectDetail data = PackageProjectDetailMgr.getInstance().getPackageProjectDetail(storeId, id);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setStatus("1");
		PageResp<PackageProjectDetail> page = PackageProjectDetailMgr.getInstance().getPackageProjectDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<PackageProjectDetail> list = page.getList();
		for (PackageProjectDetail data : list) {
			System.out.println(JsonUtil.getInstance().toJson(data));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
