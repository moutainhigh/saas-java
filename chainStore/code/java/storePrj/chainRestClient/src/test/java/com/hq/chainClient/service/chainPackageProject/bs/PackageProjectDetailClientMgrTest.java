package com.hq.chainClient.service.chainPackageProject.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.hq.chainClient.service.chainPackageProject.data.PackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class PackageProjectDetailClientMgrTest {
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
	public void testGet() {
		PackageProject packageProject = boss.getRandomPackageProject(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectDetail PackageProjectDetail = PackageProjectDetailClientMgr.getInstance().getPackageProjectDetail(chainId, packageProject.getId());
		System.out.println(JsonUtil.getInstance().toJson(PackageProjectDetail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setChainId(chainId);
		PageResp<PackageProjectDetail> page = PackageProjectDetailClientMgr.getInstance().getPackageProjectDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<PackageProjectDetail> list = page.getList();
		for (PackageProjectDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
