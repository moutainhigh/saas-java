package com.hq.chainStore.service.activateCode.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.activateCode.apiData.ActivateCodeGenApiForm;
import com.hq.chainStore.service.activateCode.apiData.AddActivateCodeForm;
import com.hq.chainStore.service.activateCode.apiData.QueryActivateCodeForm;
import com.hq.chainStore.service.activateCode.data.ActivateCode;
import com.hq.chainStore.service.activateCode.data.ActivateCodeStatusEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ActivateCodeMgrTest {
	private static Boss boss;
	
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		QueryActivateCodeForm queryForm = QueryActivateCodeForm.newInstance();
		queryForm.setPageItemCount(10000);
		List<ActivateCode> list = ActivateCodeMgr.getInstance().findByCond(queryForm);
		List<String> activateCodes = new ArrayList<String>();
		
		if(list != null && list.size() > 0){
			for (ActivateCode activateCode : list) {
				activateCodes.add(activateCode.getActivateCode());
			}
		}
		
		Set<String> codes = new HashSet<String>();
		String chars = "qwertyuiopasdfghjklzxcvbnm0123456789";
		int count = 0;
		while(count < 100){
			String val = RandomStringUtils.random(2, chars);
			if(!codes.contains(val) && !activateCodes.contains(val)){
				codes.add(val);
				count++;
			}
		}
		
		for (String code : codes) {
			AddActivateCodeForm addActivateCodeForm = AddActivateCodeForm.newInstance();
			addActivateCodeForm.setActivateCode(code);
			ActivateCodeMgr.getInstance().addActivateCode(addActivateCodeForm);
			System.out.println(code);
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFind() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		QueryActivateCodeForm queryForm = QueryActivateCodeForm.newInstance();
		queryForm.setPageItemCount(10000);
		List<ActivateCode> list = ActivateCodeMgr.getInstance().findByCond(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(list));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGenActivateCodes() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ActivateCodeGenApiForm inputForm = ActivateCodeGenApiForm.newInstance();
		inputForm.setCount(4);
		inputForm.setLen(4);
		inputForm.setSource("qwertyuiopasdfghjklzxcvbnm0123456789");
		ActivateCodeMgr.getInstance().genActivateCodes(inputForm);
		QueryActivateCodeForm queryForm = QueryActivateCodeForm.newInstance();
		queryForm.setPageItemCount(10000);
		List<ActivateCode> list = ActivateCodeMgr.getInstance().findByCond(queryForm);
		for (ActivateCode activateCode : list) {
			if(activateCode.getStatus() == ActivateCodeStatusEnum.VALID.ordinal()){
				System.out.println(JsonUtil.getInstance().toJson(activateCode));
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
