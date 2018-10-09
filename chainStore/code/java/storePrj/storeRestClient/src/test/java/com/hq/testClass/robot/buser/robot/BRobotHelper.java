package com.hq.testClass.robot.buser.robot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserChangePasswordApiData;
import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.BUserResetPasswordData;
import com.hq.chainStore.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.chainStore.service.buser.apiData.BUserVipRechargeData;
import com.hq.chainStore.service.buser.apiData.LoginResp;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.bs.BUserResetPasswordMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.BUserSynDataHolder;
import com.hq.chainStore.service.storeVipType.data.StoreVipLevelEnum;
import com.hq.testClass.AccessTokenMgr;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class BRobotHelper {
	
	private static BRobotHelper instance = new BRobotHelper();

	public static BRobotHelper getInstance(){
		return instance;
	}
	
	public boolean reg(BRobot robot){
		BUserAddApiForm formInfo = BUserAddApiForm.newInstance();

		BRobotData data = robot.getData();
		formInfo.setName(data.getName())
			.setPassword(data.getPassword())
			.setVerifyCode(data.getVerifyCode())
			.setPhone(data.getPhone())
			.setRoleSet(data.getRoleSet());
		RestResp restResp = BUserMgr.getInstance().reg(formInfo);
		
		if(restResp!=null && restResp.gettJson() != null){
			BUser buser =  JsonUtil.getInstance().fromJson(restResp.gettJson(), BUser.class);
			data.setUserId(buser.getId());
			data.setPhone(buser.getPhone());
			System.out.print("注册成功======="+buser.getPhone());
			return true;
		}
		
		return false;
	}
	
	public boolean regWithParam(BRobot robot,long phone){
		BUserAddApiForm formInfo = BUserAddApiForm.newInstance();

		BRobotData data = robot.getData();
		formInfo.setName(data.getName())
			.setPassword(data.getPassword())
			.setPhone(phone);
		RestResp restResp = BUserMgr.getInstance().reg(formInfo);
		
		if(restResp!=null && restResp.gettJson() != null){
			BUser buser =  JsonUtil.getInstance().fromJson(restResp.gettJson(), BUser.class);
			data.setUserId(buser.getId());
			data.setPhone(buser.getPhone());
			return true;
		}
		
		return false;
	}
	
	public boolean login(BRobot robot){
		
		BUserLoginApiForm loginForm = BUserLoginApiForm.newInstance();
		BRobotData data = robot.getData();
		loginForm.setPhone(data.getPhone())
		.setPassword(data.getPassword());
		
		RestResp restResp = BUserMgr.getInstance().login(loginForm);
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		data.setUserId(loginResp.getBuser().getId());
		data.setName(loginResp.getBuser().getName());
		
		data.setStoreIds(new ArrayList<>(loginResp.getBuser().getStoreIdSet()));
		
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(data.getUserId(), token);
		return true;
		
	}
	
	public boolean loginWithParam(BRobot robot,long phone,long buserId){
		
		BUserLoginApiForm loginForm = BUserLoginApiForm.newInstance();
		loginForm.setPhone(phone)
		.setPassword("123456");
		
		RestResp restResp = BUserMgr.getInstance().login(loginForm);
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(buserId, token);
		return true;
		
	}
	

	public BUser getBuser(BRobot robot, Long buserId) {
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		BUser buser = BUserMgr.getInstance().get(buserId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return buser;
	}
	
	public BUser findByPhone(BRobot robot) {
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		BUser buser = BUserMgr.getInstance().findByPhone(data.getPhone());
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return buser;
	}
	
	public List<BUser> findByMultitId(BRobot robot,String idList) {
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		List<BUser> buserList = BUserMgr.getInstance().findByMultitId(idList);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return buserList;
	}
	
	public BUser getBuserFromHodler(BRobot robot) {
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		BUser buser = BUserSynDataHolder.getInstance().getData(robot.getId(), robot.getId());
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return buser;
	}
	
	public boolean updateInfo(BRobot robot){
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		BUserUpdateInfoApiData updateInfoData = BUserUpdateInfoApiData.newInstance();
		updateInfoData.setBuserId(data.getUserId());
		updateInfoData.setName(data.getName()+"new");
		
		BUserMgr.getInstance().updateInfo(data.getUserId(),updateInfoData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return true;
	}
	
	public boolean changePassword(BRobot robot){
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		BUserChangePasswordApiData changePasswordData = BUserChangePasswordApiData.newInstance();
		changePasswordData.setBuserId(data.getUserId());
		changePasswordData.setPassword(data.getPassword()+"new");
		changePasswordData.setOldPassword(data.getPassword());
		
		BUserMgr.getInstance().changePassword(data.getUserId(),changePasswordData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return true;
	}
	
	public boolean resetPassword(BRobot robot){
		BRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		BUserResetPasswordData resetPasswordData = BUserResetPasswordData.newInstance();
		resetPasswordData.setPhone(data.getPhone());
		resetPasswordData.setPassword(data.getPassword()+"new");
		
		BUserResetPasswordMgr.getInstance().resetPassword(resetPasswordData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return true;
	}
	
	public void changeVipLevel(BRobot robot){
		long phone = robot.getData().getPhone();
		String dateStr="2020-1-23";
		long expiredTime=0;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(dateStr);
			expiredTime = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BUser buser = BUserMgr.getInstance().findByPhone(phone);
		
		if(buser.getRoleSet().contains(0)){
			long buserId = buser.getId();
			BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
			vipRechargeData.setAmount(RandomUtils.nextLong(1000L, 5000L));
			vipRechargeData.setExpiredTime(expiredTime);
			vipRechargeData.setBuserId(buserId);
			vipRechargeData.setVipType(StoreVipLevelEnum.HonKonUser.ordinal());
			BUserMgr.getInstance().vipRecharge(buserId, vipRechargeData);
			
			BUser buser2 = BUserMgr.getInstance().get(buserId);
			System.out.println(JsonUtil.getInstance().toJson(buser2));
		}else{
			System.out.println("普通工作者，不用设置会员等级与有效时间");
		}
	}
	
}