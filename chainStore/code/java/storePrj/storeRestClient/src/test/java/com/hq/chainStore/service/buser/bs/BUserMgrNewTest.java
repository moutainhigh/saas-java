package com.hq.chainStore.service.buser.bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.BUserResetPasswordData;
import com.hq.chainStore.service.buser.apiData.BUserVipRechargeData;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.BuserRoleEnum;
import com.hq.chainStore.service.sms.apiData.SmsAPIForm;
import com.hq.chainStore.service.sms.apiData.SmsTypeEnum;
import com.hq.chainStore.service.sms.bs.SmsMgr;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeVipType.data.StoreVipLevelEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class BUserMgrNewTest {
	private static Boss boss;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testFindDevUserList(){
		List<BUser> list = BUserMgr.getInstance().findDevUserList(100, 1);
		System.out.println(list.size());
		for (BUser bUser : list) {
			long phone = bUser.getPhone();
			String phoneStr = String.valueOf(phone);
			if(phoneStr.endsWith("9") && bUser.getVipType() == StoreVipLevelEnum.InnerBetaUser.ordinal()){
				System.out.println(JsonUtil.getInstance().toJson(bUser));
			}
			System.out.println(JsonUtil.getInstance().toJson(bUser));
		}
	}
	
	@Ignore
	@Test
	public void testLoginWithTestPhone(){
		BUserLoginApiForm loginForm = BUserLoginApiForm.newInstance();
		loginForm.setPhone(18573456789L);
		RestResp restResp = BUserMgr.getInstance().loginWithTestPhone(loginForm);
		System.out.println(JsonUtil.getInstance().toJson(restResp));
	}
	
	@Ignore
	@Test
	public void login(){
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testGet() {
		login();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		StoreClerkInfoMgr.getInstance().addClerk(21L, boss.getId());
		
		BUser buser = BUserMgr.getInstance().get(boss.getId());
		System.out.println(JsonUtil.getInstance().toJson(buser));
		System.out.println("areaCode="+buser.getAreaCode());
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		login();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		long buserId = boss.getId();
		BUser buser = BUserMgr.getInstance().get(buserId);
		System.out.println(JsonUtil.getInstance().toJson(buser));
		
//		BUserUpdateInfoApiData formInfo = BUserUpdateInfoApiData.newInstance();
//		FastBeanCopyer.getInstance().copy(buser, formInfo);
//		formInfo.setBuserId(buser.getId());
//		formInfo.setName("测试卡卡卡卡");
//		BUserMgr.getInstance().updateInfo(boss.getId(), formInfo);
		
//		BUserChangePasswordApiData formInfo = BUserChangePasswordApiData.newInstance();
//		formInfo.setBuserId(buser.getId());
//		formInfo.setOldPassword("123456");
//		formInfo.setPassword("654321");
//		BUserMgr.getInstance().changePassword(boss.getId(), formInfo);
		
//		BUserUpdateVipTypeData updateVipTypeData = BUserUpdateVipTypeData.newInstance();
//		updateVipTypeData.setId(buser.getId());
//		updateVipTypeData.setVipType(StoreVipLevelEnum.ExperienceUser.ordinal());
//		BUserMgr.getInstance().updateVipType(buser.getId(), updateVipTypeData);
		
		BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
		vipRechargeData.setAmount(0);
		vipRechargeData.setExpiredTime(1548432000000L);
		vipRechargeData.setBuserId(buserId);
		vipRechargeData.setVipType(StoreVipLevelEnum.StandardUser.ordinal());
		BUserMgr.getInstance().vipRecharge(buserId, vipRechargeData);

		BUser buser2 = BUserMgr.getInstance().get(buserId);
		System.out.println(JsonUtil.getInstance().toJson(buser2));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testChangeBUserVipRecharge() {
		login();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		long phone = 18925172150L;
		String dateStr="2019-2-23";
		long expiredTime=0;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(dateStr);
			expiredTime = date.getTime();
			System.out.println(expiredTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BUser buser = BUserMgr.getInstance().findByPhone(phone);
		System.out.println(JsonUtil.getInstance().toJson(buser));
		
		if(buser.getRoleSet().contains(0) || buser.getRoleSet().contains(2)){
			long buserId = buser.getId();
			BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
			vipRechargeData.setAmount(RandomUtils.nextLong(1000L, 5000L));
			vipRechargeData.setExpiredTime(expiredTime);
			vipRechargeData.setBuserId(buserId);
			vipRechargeData.setVipType(StoreVipLevelEnum.SilverUser.ordinal());
			BUserMgr.getInstance().vipRecharge(buserId, vipRechargeData);
			
			BUser buser2 = BUserMgr.getInstance().get(buserId);
			System.out.println(JsonUtil.getInstance().toJson(buser2));
		}else{
			System.out.println("普通工作者，不用设置会员等级与有效时间");
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
//	@Ignore
	@Test
	public void testReg() {
		String code = "1407";
		long phone = 66661376L;
		String name="贵妃紫";
		
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(BuserRoleEnum.INIT.ordinal());
		
		int mark = RandomUtils.nextInt(100, 999);
		BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
		formInfo.setName(name)
			.setHeadImg("headImg-" + mark)
			.setGender(2)
			.setAge(58)
			.setPassword("123456")
			.setRoleSet(roleSet)
			.setVerifyCode(code)
			.setAreaCode("+853")
			.setPhone(phone);
		RestResp restResp = BUserMgr.getInstance().reg(formInfo);
		System.out.println(restResp.gettJson());
	}
	
//	@Ignore
	@Test
	public void testAddClerk(){
		long storeId = 2L;
		long buserId = 6L;
		login();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfoMgr.getInstance().addClerk(storeId, buserId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	@Ignore
	@Test
	public void testResetPassword() {
		String code = "4561";
		long phone = 13660623958L;
		String password = "123456";
		
		Map<String,String> dictMap = new HashMap<String, String>();
		dictMap.put("code", code);
		
		SmsAPIForm apiForm = SmsAPIForm.newInstance();
		apiForm.setPhoneNumbers(phone+"");
		apiForm.setSmsType(SmsTypeEnum.B_VERIFICATION_CODE.ordinal());
		apiForm.setDictMap(dictMap);
		SmsMgr.getInstance().sendSms(apiForm);
		
		BUserResetPasswordData resetPasswordData = BUserResetPasswordData.newInstance();
		resetPasswordData.setPhone(phone);
		resetPasswordData.setPassword(password);
		resetPasswordData.setVerifyCode(code);
		
		BUserResetPasswordMgr.getInstance().resetPassword(resetPasswordData);
		
	}

}
