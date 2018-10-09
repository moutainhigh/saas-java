package com.hq.chainStore.service.report.bs;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.clerkSalary.bs.ClerkSalaryMgr;
import com.hq.chainStore.service.clerkSalary.data.ClerkSalary;
import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsQueryParam;
import com.hq.chainStore.service.materialRecords.bs.MaterialRecordsMgr;
import com.hq.chainStore.service.materialRecords.data.MaterialRecords;
import com.hq.chainStore.service.report.apiData.BeauticianSpecialData;
import com.hq.chainStore.service.report.apiData.CUserSpecialData;
import com.hq.chainStore.service.report.apiData.MaterialReportQueryParams;
import com.hq.chainStore.service.report.apiData.OrderReportQueryParams;
import com.hq.chainStore.service.report.apiData.ProductSpecialData;
import com.hq.chainStore.service.report.data.MaterialReport;
import com.hq.chainStore.service.report.data.OrderReport;
import com.hq.chainStore.service.report.data.SpecialData;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.clerkSalary.robot.ClerkSalaryRobot;
import com.hq.testClass.robot.storeMaterialInfo.robot.MaterialRobot;


public class ReportMgrTest {
	private static Boss boss;
	private static long storeId=21L;

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.setStoreId(storeId);
		boss.login();
	}
	
	private long getRoleTestId(){
		return boss.getId();
	}
	
	@Test
	public void test(){
		
	}
	
	@Test
	public void testOrderReport() {
		AccessTokenMgr.getInstance().setOpIdTL(getRoleTestId());
//		Date date = new Date();
//		long maxTime = date.getTime();
//		long minTime = DateUtils.addDays(date, -30).getTime();//30天前
//storeId=26&minTime=1509465600000&maxTime=1512057599000  11月
//storeId=26&minTime=1506873600000&maxTime=1509552000000  10月
		OrderReportQueryParams params = OrderReportQueryParams.newInstance();
		params.setMinTime(1509465600000L);
		params.setMaxTime(1512057599000L);
		params.setStoreId(26L);
//		params.setBuserId(26L);
		params.setPageItemCount(50);
		params.setPageNo(1);
		List<OrderReport> list = OrderReportMgr.getInstance().findOrderReportList(params);
		System.out.println(list);
		Assert.assertTrue("订单条数大于0", list.size() > 0);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testSpecialData() {
		AccessTokenMgr.getInstance().setOpIdTL(getRoleTestId());
		SpecialData specialData = SpecialDataMgr.getInstance().getSpecialData(storeId+"_" + boss.getId());
		System.out.println(specialData);
		
		BeauticianSpecialData data = BeauticianSpecialData.newInstance();
//		data.setBeauticianId(RandomUtils.nextLong(35L, 101L));
//		SpecialDataMgr.getInstance().addBeauticianSpecialData(storeId+"_" + boss.getId(), data);
		data.setBeauticianId(73L);
		SpecialDataMgr.getInstance().delBeauticianSpecialData(storeId+"_" + boss.getId(), data);
		specialData = SpecialDataMgr.getInstance().getSpecialData(storeId+"_" + boss.getId());
		System.out.println(specialData);
		
		CUserSpecialData data2 = CUserSpecialData.newInstance();
//		data2.setCuserId(RandomUtils.nextLong(1L, 57L));
//		SpecialDataMgr.getInstance().addCUserSpecialData(storeId+"_" + boss.getId(), data2);
		data2.setCuserId(26L);
		SpecialDataMgr.getInstance().delCUserSpecialData(storeId+"_" + boss.getId(), data2);
		specialData = SpecialDataMgr.getInstance().getSpecialData(storeId+"_" + boss.getId());
		System.out.println(specialData);
		
		ProductSpecialData data3 = ProductSpecialData.newInstance();
//		data3.setProductId(RandomUtils.nextLong(1L, 39L));
//		SpecialDataMgr.getInstance().addProductSpecialData(storeId+"_" + boss.getId(), data3);
		data3.setProductId(2L);
		SpecialDataMgr.getInstance().delProductSpecialData(storeId+"_" + boss.getId(), data3);
		specialData = SpecialDataMgr.getInstance().getSpecialData(storeId+"_" + boss.getId());
		System.out.println(specialData);
		
		Assert.assertNotNull("关心的数据不为空", specialData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	@Test
	public void testMaterialReport() {
		AccessTokenMgr.getInstance().setOpIdTL(getRoleTestId());
		
		MaterialReportQueryParams params = MaterialReportQueryParams.newInstance();
		params.setStoreId(storeId);
		params.setPageItemCount(50);
		params.setPageNo(1);
		List<MaterialReport> list = MaterialReportMgr.getInstance().findMaterialReportList(params);
		System.out.println(list);
		Assert.assertTrue("耗材明细条数大于0", list.size() > 0);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testStoreMaterialInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(getRoleTestId());
		MaterialRobot.newRandom().addMaterial(storeId);
		MaterialRobot.newRandom().addMaterial(storeId);
		MaterialRobot.newRandom().addMaterial(storeId);
		MaterialRobot.newRandom().addMaterial(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testMaterialRecords() {
		AccessTokenMgr.getInstance().setOpIdTL(getRoleTestId());
		//maxTime=1504195199000&minTime=1501516800000&pageItemCount=10&pageNo=1&storeId=14
		MaterialRecordsQueryParam params = MaterialRecordsQueryParam.newInstance();
		params.setMaxTime(1504195199000L);
		params.setMinTime(1501516800000L);
		params.setStoreId(14L);
		params.setPageItemCount(50);
		params.setPageNo(1);
		List<MaterialRecords> list = MaterialRecordsMgr.getInstance().findByStoreId(params);
		System.out.println(list);
//		Assert.assertTrue("耗材进货记录>0", list.size() > 0);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testClerkSalary() {
		AccessTokenMgr.getInstance().setOpIdTL(getRoleTestId());
		ClerkSalaryRobot robot = ClerkSalaryRobot.newRandom();
		robot.addRecord(storeId, 23L);
		List<ClerkSalary> salarys = ClerkSalaryMgr.getInstance().findByStoreId(storeId,50,1);
		System.out.println(salarys);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
