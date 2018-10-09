
package com.hq.storeManagerRestClient.testClass.robot.muser;

import org.apache.commons.lang3.RandomUtils;

import com.hq.storeManagerRestClient.service.muser.apiData.LoginResp;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserAddApiForm;
import com.hq.storeManagerRestClient.service.muser.apiData.MUserLoginApiForm;
import com.hq.storeManagerRestClient.service.muser.bs.MUserClientMgr;
import com.hq.storeManagerRestClient.service.muser.data.MUser;
import com.hq.storeManagerRestClient.testClass.AccessTokenMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class MUserRobot {
	private MUserRobotData data;

	public static MUserRobot newRandom() {
		int mark = RandomUtils.nextInt(0, 100000);
		return newInstance(mark);
	}

	public static MUserRobot newInstance(int mark) {
		MUserRobot robot = new MUserRobot();
		robot.data = MUserRobotData.newInstance(mark);
		return robot;
	}
	
	public MUser reg(String account) {
		MUserAddApiForm formInfo = MUserAddApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(this.data, formInfo);
		formInfo.setAccount(account);
		RestResp restResp = MUserClientMgr.getInstance().reg(formInfo);
		if(restResp.getCode() == 200){
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), MUser.class);
		}
		return null;
	}
	
	public boolean loginWithParam(String account, String password) {
		MUserLoginApiForm loginForm = MUserLoginApiForm.newInstance();
		loginForm.setAccount(account);
		loginForm.setPassword(password);
		RestResp restResp = MUserClientMgr.getInstance().login(loginForm);
		if(restResp.getCode() == 200){
			LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
			data.setMuserId(loginResp.getMuser().getId());
			data.setName(loginResp.getMuser().getName());
			String token = loginResp.getToken();
			AccessTokenMgr.getInstance().putToken(data.getMuserId(), token);
			return true;
		}else{
			return false;
		}
	}

	public MUserRobotData getData() {
		return data;
	}

	public void setData(MUserRobotData data) {
		this.data = data;
	}

	public long getId() {
		return this.data.getMuserId();
	}

}