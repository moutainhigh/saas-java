package com.hq.chainStore.service.dataReport.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.dataReport.apiData.DataReportQueryForm;
import com.hq.chainStore.service.dataReport.data.DataReport;
import com.hq.chainStore.service.dataReport.data.MemberDataCount;
import com.hq.chainStore.service.order.data.Order;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;


public class DataReportMgrTest {
	private static Boss boss;
	private static long storeId=21L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testGetMemberDataCount() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MemberDataCount memberDataCount = DataReportMgr.getInstance().getMemberDataCount(storeId);
		System.out.println(JsonUtil.getInstance().toJson(memberDataCount));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindDataReprotList() {
		//storeId=515&orderType=0&cuserId=0&maxTime=1534780799000&minTime=1533052800000&pageItemCount=10&pageNo=1&
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setMaxTime(1534780799000L);
		queryForm.setMinTime(1533052800000L);
		queryForm.setStoreId(515L);
		List<DataReport> dataReprotList = DataReportMgr.getInstance().findDataReprotList(queryForm);
		System.out.println(dataReprotList.size());
		System.out.println(JsonUtil.getInstance().toJson(dataReprotList));
		
		float sum = 0.0f;
		for (DataReport dataReport : dataReprotList) {
			sum += dataReport.getPay();
		}
		System.out.println(sum);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindOrderList() {
		//pageItemCount=100000&pageNo=1&storeId=34&maxTime=1515513599000&minTime=1514736000000&
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setMaxTime(1515513599000L);
		queryForm.setMinTime(1514736000000L);
		queryForm.setStoreId(34L);
		List<Order> orderList = DataReportMgr.getInstance().findOrderList(queryForm);
		System.out.println(orderList.size());
		float amount = 0.0f;
		for (Order order : orderList) {
			amount += order.getCost();
		}
		System.out.println(amount);
		System.out.println(JsonUtil.getInstance().toJson(orderList));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
