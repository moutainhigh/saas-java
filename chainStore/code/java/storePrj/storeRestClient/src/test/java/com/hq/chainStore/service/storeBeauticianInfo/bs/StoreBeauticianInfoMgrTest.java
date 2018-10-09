package com.hq.chainStore.service.storeBeauticianInfo.bs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianInfo;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianState;
import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.Clerk;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.opUser.OPSuper;
import com.hq.testClass.robot.storeBeauticianInfo.StoreBeaut;
import com.hq.testClass.robot.storeBeauticianInfo.robot.StoreBeauticianRobot;
import com.zenmind.common.json.JsonUtil;

public class StoreBeauticianInfoMgrTest {

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}
	
	@Ignore
	@Test
	public void testAddBeauticianInfo() {
		//老板注册、登陆、开店、提交审核
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();

		Store store = boss.openSimpleStore(1);
		boss.submit4Check(store.getId());

		//管理员审核店铺
		OPSuper.getInstance().approveStore(store.getId());

		//店员注册、登录
		Clerk clerk = Clerk.newClerk(BRobot.newRandom());
		clerk.reg();
		clerk.login();

		//boss登陆、添加店员
		boss.login();
		boss.addClerk(clerk.getId());

		//获取员工列表
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		
		//添加医美师
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreBeaut storeBeaut = StoreBeaut.newInstance(StoreBeauticianRobot.newRandom());
		StoreBeauticianInfo storeBeautician = storeBeaut.addStoreBeautician(storeClerkInfo, clerk.getId());
		//验证
		Assert.assertNotNull("应该非空", storeBeautician);
		
		//查询医美师
		StoreBeauticianInfo StoreBeauticianGet = storeBeaut.getById();
		BeauticianInfo beauticianInfoGet = StoreBeauticianGet.getBeauticianInfoMap().get(clerk.getId());
		//验证
		Assert.assertNotNull("beauticianInfoGet 状态 应该为onWork", beauticianInfoGet.getState()==BeauticianState.ONWork.ordinal());
		
		//修改医美师信息
		storeBeaut.updateInfo();
		//验证
		StoreBeauticianInfo storeBeauticianUpdate = storeBeaut.getById();
		BeauticianInfo beauticianInfoUpdate = storeBeauticianUpdate.getBeauticianInfoMap().get(clerk.getId());
		Assert.assertTrue("beauticianInfoUpdate 状态 应该为offWork", beauticianInfoUpdate.getState()==BeauticianState.OffWork.ordinal());
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetBeauticianInfo() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreBeauticianInfo info = StoreBeauticianInfoMgr.getInstance().get(15922L);
		System.out.println(JsonUtil.getInstance().toJson(info));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}


}
