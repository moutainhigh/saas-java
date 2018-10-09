package com.hq.chainStore.service.storePackageProject.bs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectBatchUpdateStateForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectRemoveForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectUpdateForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainStore.service.storePackageProject.data.PackageProject;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProject;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class StorePackageProjectMgrTest {
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
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storePackageProject));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAddPackageProjectType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(storeId);
		PackageProjectTypeAddForm inputForm = PackageProjectTypeAddForm.newInstance();
		inputForm.setIndex(storePackageProject.getPackageProjectTypeIndex()+1);
		inputForm.setName("分类-" + inputForm.getIndex());
		StorePackageProjectMgr.getInstance().addPackageProjectType(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdatePackageProjectType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectTypeUpdateForm inputForm = PackageProjectTypeUpdateForm.newInstance();
		inputForm.setId("1");
		inputForm.setName("修改分类名称");
		StorePackageProjectMgr.getInstance().updatePackageProjectType(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testRemovePackageProjectType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectTypeRemoveForm inputForm = PackageProjectTypeRemoveForm.newInstance();
		inputForm.setId("1");
		StorePackageProjectMgr.getInstance().removePackageProjectType(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testAddPackageProject() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storePackageProject));
		PackageProjectAddForm inputForm = PackageProjectAddForm.newInstance();
		inputForm.setIndex(storePackageProject.getPackageProjectIndex()+1);
		inputForm.setName("套餐-"+RandomUtils.nextInt(100, 1000));
		inputForm.setTypeId("2");
		inputForm.setNumber("number-"+RandomUtils.nextInt(100, 1000));
		inputForm.setSellPrice(RandomUtils.nextFloat(100f, 1000f));
		inputForm.setState(0);
		StorePackageProjectMgr.getInstance().addPackageProject(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdatePackageProject() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(storeId);
		PackageProject packageProject = storePackageProject.getPackageProjectMap().get("21_1");
		PackageProjectUpdateForm inputForm = PackageProjectUpdateForm.newInstance();
		FastBeanCopyer.getInstance().copy(packageProject, inputForm);
//		inputForm.setName("修改名称");
		inputForm.setDescript("描述");
		StorePackageProjectMgr.getInstance().updatePackageProject(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdatePackageProjectStatus() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectUpdateStateForm inputForm = PackageProjectUpdateStateForm.newInstance();
		inputForm.setId("21_1");
		inputForm.setState(1);
		StorePackageProjectMgr.getInstance().updPackageProjectState(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testBatchUpdatePackageProjectState() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectBatchUpdateStateForm inputForm = PackageProjectBatchUpdateStateForm.newInstance();
		inputForm.addId("21_1");
		inputForm.addId("21_2");
		inputForm.setState(1);
		StorePackageProjectMgr.getInstance().batchUpdatePackageProjectState(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
//	@Ignore
	@Test
	public void testRemovePackageProject() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectRemoveForm inputForm = PackageProjectRemoveForm.newInstance();
		inputForm.setId("21_1");
		StorePackageProjectMgr.getInstance().removePackageProject(storeId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
