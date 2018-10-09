package com.hq.chainStore.service.clerkSalary.bs;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.clerkSalary.data.ClerkPayroll;
import com.hq.chainStore.service.clerkSalary.data.ClerkSalary;
import com.hq.chainStore.service.store.data.Store;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.Clerk;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.clerkSalary.TClerkSalary;
import com.hq.testClass.robot.clerkSalary.robot.ClerkSalaryRobot;
import com.zenmind.common.json.JsonUtil;

public class ClerkSalaryMgrTest {

	private static Boss boss;
	
	private static Store store;
	
	private static Clerk clerk;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		
		int mark = RandomUtils.nextInt(0, 1000);
		//老板注册、登陆、开店、提交审核
		boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();

		//开店
		store = boss.openSimpleStore(mark);
		
		//店员注册、登录
		clerk = Clerk.newClerk(BRobot.newRandom());
		clerk.reg();

		//boss登陆、添加店员
		boss.login();
		boss.addClerk(clerk.getId());

		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
	}
	
	@Test
	public void testPattern(){
		String str = "0.140000";
		Pattern pattern = Pattern.compile("^[0-9]+[.]?[0-9]+$");
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			System.out.println("false");
		}else{
			System.out.println("true");
		}
		
		System.out.println(StringUtils.isNumeric(str));
	}
	
	@Test
	public void testAll() {
		TClerkSalary tClerkSalary = TClerkSalary.newInstance(ClerkSalaryRobot.newRandom());
		//修改员工薪酬
		tClerkSalary.addRecord(store.getId(), clerk.getId());
		tClerkSalary.addRecord(store.getId(), clerk.getId());
		
		//验证
		//获取员工薪酬信息
		ClerkSalary clerkSalary = tClerkSalary.getById(store.getId(), clerk.getId());
		Assert.assertTrue("员工薪酬信息id应该为 ：", clerkSalary.getId().equals(store.getId() + "_" + clerk.getId()));
		
		//验证
		//获取店铺所有员工薪酬信息
		List<ClerkSalary> findByStoreId = tClerkSalary.findByStoreId(store.getId());
		Assert.assertTrue("店铺所有员工薪酬记录 ：", findByStoreId.size() > 0);
		System.out.println("店铺所有员工薪酬记录 ：" + JsonUtil.getInstance().toJson(findByStoreId));
		
		//删除员工所有薪酬记录
		tClerkSalary.removeRecord(1);
		
		//验证
		clerkSalary = ClerkSalaryMgr.getInstance().findById(store.getId(), clerk.getId());
		Assert.assertTrue("员工薪酬记录应该为空 ：", clerkSalary.getClerkSalaryRecordsMap().isEmpty());
		
		
		//创建员工工资单
		tClerkSalary.addPayroll(store.getId(), clerk.getId());

		//验证
		//获取员工工资单信息
		clerkSalary = tClerkSalary.getById(store.getId(), clerk.getId());
		Map<String, ClerkPayroll> clerkPayrollMap = clerkSalary.getClerkPayrollMap();
		Assert.assertTrue("员工工资单size应该 > 0 ：", clerkPayrollMap.values().size()>0);
		for (ClerkPayroll item : clerkPayrollMap.values()) {
			System.out.println("员工工资单信息" + JsonUtil.getInstance().toJson(item));;
		}
	}

}
