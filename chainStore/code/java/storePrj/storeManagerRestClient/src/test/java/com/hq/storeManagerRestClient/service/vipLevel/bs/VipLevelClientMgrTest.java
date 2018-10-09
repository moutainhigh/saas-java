package com.hq.storeManagerRestClient.service.vipLevel.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.common.validate.ValidateThreadLocal;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.AddVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.UpdateVipLevelStateForm;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.hq.storeManagerRestClient.testClass.AccessTokenMgr;
import com.hq.storeManagerRestClient.testClass.TestEnv;
import com.hq.storeManagerRestClient.testClass.robot.muser.Admin;
import com.hq.storeManagerRestClient.testClass.robot.muser.MUserRobot;
import com.hq.storeManagerRestClient.testClass.robot.vipLevel.VipLevelRobot;
import com.zenmind.common.json.JsonUtil;

public class VipLevelClientMgrTest {

	private static Admin admin;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		admin = Admin.newAdmin(MUserRobot.newRandom());
		admin.loginWithParam("admin","admin");
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		VipLevelRobot robot = VipLevelRobot.newRandom();
		AddVipLevelForm addForm = robot.toAddForm(); 
		VipLevel vipLevel = VipLevelClientMgr.getInstance().add(addForm);
		System.out.println(JsonUtil.getInstance().toJson(vipLevel));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		long id = 1L;
		VipLevel vipLevel = VipLevelClientMgr.getInstance().get(id);
		System.out.println("修改前： "+JsonUtil.getInstance().toJson(vipLevel));
		
		UpdateVipLevelStateForm updateStateForm = new UpdateVipLevelStateForm();
		updateStateForm.setId(id);
		updateStateForm.setState(1);
		VipLevelClientMgr.getInstance().updateState(id, updateStateForm);
		
		VipLevel vipLevel2 = VipLevelClientMgr.getInstance().get(id);
		System.out.println("修改后： "+JsonUtil.getInstance().toJson(vipLevel2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		VipLevel vipLevel = VipLevelClientMgr.getInstance().get(1L);
		System.out.println(JsonUtil.getInstance().toJson(vipLevel));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetList() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		QueryVipLevelForm queryForm = QueryVipLevelForm.newInstance();
		queryForm.setTypeId(1);
		queryForm.setPageItemCount(20);
		queryForm.setPageNo(1);
		PageResp<VipLevel> page = VipLevelClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGetFromMS() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		VipLevel vipLevel = VipLevelClientMgr.getInstance().get(1L);
		System.out.println(JsonUtil.getInstance().toJson(vipLevel));
		ValidateThreadLocal.getInstance().remove();
	}

}
