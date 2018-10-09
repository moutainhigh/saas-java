package com.hq.testClass.robot.opUser.robot;

import com.hq.chainStore.service.buser.apiData.LoginResp;
import com.hq.chainStore.service.opuser.apiData.OPUserAddApiForm;
import com.hq.chainStore.service.opuser.apiData.OPUserLoginApiForm;
import com.hq.chainStore.service.opuser.bs.OPUserMgr;
import com.hq.chainStore.service.opuser.data.OPUser;
import com.hq.testClass.AccessTokenMgr;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class OPRobotHelper {
	
	private static OPRobotHelper instance = new OPRobotHelper();

	public static OPRobotHelper getInstance(){
		return instance;
	}
	
	public boolean reg(OPRobot robot){
		OPUserAddApiForm formInfo = OPUserAddApiForm.newInstance();

		OPRobotData data = robot.getData();
		formInfo.setName(data.getName())
			.setPassword(data.getPassword())
			.setPhone(data.getPhone());
		
		RestResp restResp = OPUserMgr.getInstance().reg(formInfo);
		
		if(restResp!=null && restResp.gettJson() != null){
			OPUser opId =  JsonUtil.getInstance().fromJson(restResp.gettJson(), OPUser.class);
			data.setUserId(opId.getId());
			return true;
		}
		
		return false;
	}
	
	public boolean login(OPRobot robot){
		
		OPUserLoginApiForm loginForm = OPUserLoginApiForm.newInstance();
		OPRobotData data = robot.getData();
		loginForm.setPhone(data.getPhone())
		.setPassword(data.getPassword());
		
		RestResp restResp = OPUserMgr.getInstance().login(loginForm);
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(data.getUserId(), token);
		return true;
		
	}
	

	public OPUser getOpUser(OPRobot robot, Long opId) {
		OPRobotData data = robot.getData();
		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());
		
		OPUser buser = OPUserMgr.getInstance().get(opId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		return buser;
	}
	
	
}