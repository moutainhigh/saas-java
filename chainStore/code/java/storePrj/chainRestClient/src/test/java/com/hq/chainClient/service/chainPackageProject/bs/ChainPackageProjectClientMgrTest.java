package com.hq.chainClient.service.chainPackageProject.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectAddForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectRemoveForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectTypeUpdateForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectUpdateForm;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageItem;
import com.hq.chainClient.service.chainPackageProject.data.PackageItemEnum;
import com.hq.chainClient.service.chainPackageProject.data.PackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectType;
import com.hq.chainClient.service.chainPackageProject.data.PackageStateEnum;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.hq.chainClient.testClass.robot.chainPackageProject.ChainPackageProjectRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChainPackageProjectClientMgrTest {
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
	public void testAddPackageProjectType() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainPackageProject chainData = ChainPackageProjectClientMgr.getInstance().get(chainId);
		PackageProjectTypeAddForm inputForm = PackageProjectTypeAddForm.newInstance();
		inputForm.setIndex(chainData.getPackageProjectTypeIndex()+1);
		inputForm.setName("套餐分类"+RandomUtils.nextInt(1, 10));
		ChainPackageProjectClientMgr.getInstance().addPackageProjectType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdatePackageProjectType() {
		PackageProjectType type = boss.getRandomPackageProjectType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectTypeUpdateForm inputForm = PackageProjectTypeUpdateForm.newInstance();
		FastBeanCopyer.getInstance().copy(type, inputForm);
		inputForm.setName("修改套餐分类");
		ChainPackageProjectClientMgr.getInstance().updatePackageProjectType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemovePackageProjectType() {
		PackageProjectType type = boss.getRandomPackageProjectType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectTypeRemoveForm inputForm = PackageProjectTypeRemoveForm.newInstance();
		inputForm.setId(type.getId());
		ChainPackageProjectClientMgr.getInstance().removePackageProjectType(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddPackageProject() {
		List<PackageItem> packageItems = getPackageItems();
		PackageProjectType type = boss.getRandomPackageProjectType(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainPackageProject chainData = ChainPackageProjectClientMgr.getInstance().get(chainId);
		PackageProjectAddForm inputForm = ChainPackageProjectRobotData.newRandomInstance().toPackageProjectAddForm(chainData.getPackageProjectIndex()+1, type.getId());
		inputForm.setPackageItems(packageItems);
		ChainPackageProjectClientMgr.getInstance().addPackageProject(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdatePackageProject() {
		List<PackageItem> packageItems = getPackageItems();
		PackageProject PackageProject = boss.getRandomPackageProject(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectUpdateForm inputForm = PackageProjectUpdateForm.newInstance();
		FastBeanCopyer.getInstance().copy(PackageProject, inputForm);
		inputForm.setName("傻姑娘");
		inputForm.setPackageItems(packageItems);
		ChainPackageProjectClientMgr.getInstance().updatePackageProject(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemovePackageProject() {
		PackageProject PackageProject = boss.getRandomPackageProject(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectRemoveForm inputForm = PackageProjectRemoveForm.newInstance();
		inputForm.setId(PackageProject.getId());
		ChainPackageProjectClientMgr.getInstance().removePackageProject(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdatePackageProjectState() {
		PackageProject PackageProject = boss.getRandomPackageProject(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		PackageProjectUpdateStateForm inputForm = PackageProjectUpdateStateForm.newInstance();
		inputForm.setId(PackageProject.getId());
		inputForm.setState(PackageStateEnum.Close.ordinal());
		ChainPackageProjectClientMgr.getInstance().updPackageProjectState(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private List<PackageItem> getPackageItems(){
		List<PackageItem> packageItems = new ArrayList<PackageItem>();
		PackageItem item = PackageItem.newInstance();
		item.setCount(RandomUtils.nextInt(1, 10));
		item.setItemType(RandomUtils.nextInt(0, 2));
		item.setDiscountPrice(RandomUtils.nextFloat(100f, 500f));
		if(item.getItemType() == PackageItemEnum.PRODUCT.ordinal()) {
			item.setPgId(boss.getRandomProduct(chainId).getId());
		}else {
			item.setPgId(boss.getRandomGoods(chainId).getId());
		}
		packageItems.add(item);
		return packageItems;
	}
	
}
