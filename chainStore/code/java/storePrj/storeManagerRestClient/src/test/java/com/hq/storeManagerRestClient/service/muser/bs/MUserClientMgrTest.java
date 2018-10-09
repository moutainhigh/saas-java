package com.hq.storeManagerRestClient.service.muser.bs;

import java.util.Set;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.service.muser.apiData.MUserChangePasswordApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateInfoApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateRoleSetApiData;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserUpdateStatusApiData;
import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.hq.storeManagerRestClient.service.muser.data.MUserStatusEnum;
import com.hq.storeManagerRestClient.testClass.AccessTokenMgr;
import com.hq.storeManagerRestClient.testClass.TestEnv;
import com.hq.storeManagerRestClient.testClass.robot.muser.Admin;
import com.hq.storeManagerRestClient.testClass.robot.muser.MUserRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class MUserClientMgrTest {
	private static Admin admin;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		admin = Admin.newAdmin(MUserRobot.newRandom());
		admin.loginWithParam("admin","admin");
	}
	
	@Test
	public void testFindByAccount() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		MUser muser = MUserClientMgr.getInstance().findByAccount("admin");
		System.out.println(JsonUtil.getInstance().toJson(muser));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		MUser muser = MUserClientMgr.getInstance().getMUser(1L);
		System.out.println(JsonUtil.getInstance().toJson(muser));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		MUserRobot robot = MUserRobot.newRandom();
		MUser muser = robot.reg("13660623953");
		System.out.println(JsonUtil.getInstance().toJson(muser));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		MUser muser = MUserClientMgr.getInstance().getMUser(2L);
		long muserId = muser.getId();
		
		MUserUpdateInfoApiData updateInfoData = MUserUpdateInfoApiData.newInstance();
		FastBeanCopyer.getInstance().copy(muser, updateInfoData);
		updateInfoData.setMuserId(muserId);
		updateInfoData.setName("test");
		MUserClientMgr.getInstance().updateInfo(muserId, updateInfoData);
		
		MUserUpdateStatusApiData updateStatusApiData = MUserUpdateStatusApiData.newInstance();
		updateStatusApiData.setMuserId(muserId);
		updateStatusApiData.setStatus(MUserStatusEnum.Delete.ordinal());
		MUserClientMgr.getInstance().updMUserStatus(muserId, updateStatusApiData);
		
		MUserUpdateRoleSetApiData updateRoleSetApiData = MUserUpdateRoleSetApiData.newInstance();
		updateRoleSetApiData.setMuserId(muserId);
		Set<Long> muserAdminRoleIds = new TreeSet<Long>();
		muserAdminRoleIds.add(42L);
		updateRoleSetApiData.setMuserAdminRoleIds(muserAdminRoleIds);
		MUserClientMgr.getInstance().updRoleSet4Clerk(muserId, updateRoleSetApiData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testChangePassword() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		MUserChangePasswordApiData changePasswordData = MUserChangePasswordApiData.newInstance();
		changePasswordData.setMuserId(admin.getId());
		changePasswordData.setOldPassword("123456");
		changePasswordData.setPassword("admin");
		MUserClientMgr.getInstance().changePassword(admin.getId(), changePasswordData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
