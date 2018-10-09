package com.hq.chainStore.service.appointment.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.chainStore.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateInfoApiForm;
import com.hq.chainStore.service.appointment.apiData.AppointmentUpdateStatusApiForm;
import com.hq.chainStore.service.appointment.data.AppointProduct;
import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.appointment.data.AppointmentDateGroup;
import com.hq.chainStore.service.appointment.data.AppointmentQueryParams;
import com.hq.chainStore.service.appointment.data.AppointmentStatusEnum;
import com.hq.chainStore.service.appointment.data.AppointmentSynDataHolder;
import com.hq.chainStore.service.appointment.data.CancelReason;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestConstants;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;


public class AppointmentMgrTest {
	private static Boss boss;
	private static long storeId=0L;
	
	private static List<ClerkInfo> clerks;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		clerks = new ArrayList<ClerkInfo>(storeClerkInfo.getClerkInfoMap().values());
		
		Store store = StoreMgr.getInstance().get(storeId);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), ValidateInfo.newInstance(store.getBossId(), storeId));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAdd() {
		ClerkInfo clerkInfo = clerks.get(RandomUtils.nextInt(0, clerks.size()));
		Leaguer leaguer = boss.getRandomLeaguer(storeId);
		ProductInfo product = boss.getRandomProductInfo(storeId);
		
		List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();
		AppointProduct apd = AppointProduct.newInstance();
		Set<Long> buserIds = new HashSet<Long>();
		buserIds.add(clerkInfo.getBuserId());
		apd.setProductId(product.getId());
		apd.setBuserIds(buserIds);
		appointProducts.add(apd);
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppointmentAddApiForm addForm = AppointmentAddApiForm.newInstance();
		addForm.setStoreId(storeId);
		addForm.setLeaguerId(leaguer.getId());
		addForm.setAppointTime(System.currentTimeMillis());
		addForm.setCreatorId(boss.getId());
		addForm.setAppointProducts(appointProducts);
		Appointment appointment = AppointmentMgr.getInstance().addAppointment(addForm);
		System.out.println(JsonUtil.getInstance().toJson(appointment));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long appointmentId = 4552L;
		Appointment appointment = AppointmentMgr.getInstance().getAppointment(storeId, appointmentId);
		System.out.println(JsonUtil.getInstance().toJson(appointment));
		
		AppointmentUpdateInfoApiForm updateInfoForm = AppointmentUpdateInfoApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(appointment, updateInfoForm);
		updateInfoForm.setAppointTime(System.currentTimeMillis());
		AppointmentMgr.getInstance().updateAppointmentInfo(appointmentId, storeId, updateInfoForm);
		
		AppointmentUpdateStatusApiForm updateStatusForm = AppointmentUpdateStatusApiForm.newInstance();
		updateStatusForm.setStatus(RandomUtils.nextInt(0, 3));
		AppointmentMgr.getInstance().updateAppointmentStatus(appointmentId, storeId, updateStatusForm);
		
		Appointment appointment2 = AppointmentMgr.getInstance().getAppointment(storeId, appointmentId);
		System.out.println(JsonUtil.getInstance().toJson(appointment2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AppointmentMgr.getInstance().deleteAppoint(5043L, storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		long appointId = 5171L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		Appointment app1 = AppointmentMgr.getInstance().getAppointment(appointId);
//		System.out.println(JsonUtil.getInstance().toJson(app1));
		
		Appointment appointment = AppointmentSynDataHolder.getInstance().getData(String.valueOf(boss.getId()), appointId+"", storeId);
		System.out.println(JsonUtil.getInstance().toJson(appointment));
		Appointment app2 = AppointmentMgr.getInstance().getAppointment(storeId, appointId);
		System.out.println(JsonUtil.getInstance().toJson(app2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		System.out.println(now - TestConstants.ONE_DAY);
		AppointmentQueryParams params = AppointmentQueryParams.newInstance();
//		params.setMaxTime(now + TestConstants.ONE_MONTH);
		params.setMinTime(now - TestConstants.ONE_DAY);
		params.setStoreId(400L);
		params.setPageItemCount(100);
		
		List<Appointment> list = AppointmentMgr.getInstance().findAppointmentList(params);
		System.out.println(list.size());
		System.out.println(JsonUtil.getInstance().toJson(list));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindAppointmentDateGroupList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		AppointmentQueryParams params = AppointmentQueryParams.newInstance();
		params.setMaxTime(1519833600000L);
		params.setMinTime(1519747200000L);
		params.setStoreId(storeId);
		params.setStatus("0,1");
		List<AppointmentDateGroup> list = AppointmentMgr.getInstance().findAppointmentDateGroupList(params);
		System.out.println(list.size());
		for (AppointmentDateGroup appointmentDateGroup : list) {
			System.out.println(JsonUtil.getInstance().toJson(appointmentDateGroup));
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testPageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long now = System.currentTimeMillis();
		System.out.println(now - TestConstants.ONE_MONTH * 4);
		AppointmentQueryParams params = AppointmentQueryParams.newInstance();
		params.setMinTime(1533052800000L);
		params.setMaxTime(1533139199999L);
		params.setStoreId(storeId);
		params.setPageItemCount(20);
		params.setOrigin(0);
		params.setStatus("0,1");
		
		PageResp<Appointment> pageResp = AppointmentMgr.getInstance().getAppointmentPageInfo(params);
		System.out.println(JsonUtil.getInstance().toJson(pageResp));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testCancel() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long appointmentId = 4891L;
		AppointmentUpdateStatusApiForm updateStatusForm = AppointmentUpdateStatusApiForm.newInstance();
		updateStatusForm.setStatus(AppointmentStatusEnum.CANCEL.ordinal());
		CancelReason cancelReason = CancelReason.newInstance();
		cancelReason.setReason("顾客个人原因");
		cancelReason.setRemarks("dddddddddddddd");
		updateStatusForm.setCancelReason(cancelReason);
		AppointmentMgr.getInstance().updateAppointmentStatus(appointmentId, storeId, updateStatusForm);
		
		Appointment appointment = AppointmentMgr.getInstance().getAppointment(storeId, appointmentId);
		System.out.println(JsonUtil.getInstance().toJson(appointment));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
