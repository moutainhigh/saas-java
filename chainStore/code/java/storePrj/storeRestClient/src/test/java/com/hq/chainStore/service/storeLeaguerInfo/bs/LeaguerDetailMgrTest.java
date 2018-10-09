package com.hq.chainStore.service.storeLeaguerInfo.bs;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.SortEnum;
import com.hq.chainStore.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.chainStore.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetail;
import com.hq.chainStore.service.leaguerDetail.data.LeaguerDetailDataHolder;
import com.hq.chainStore.service.leaguerDetail.data.MemberCardExist;
import com.hq.chainStore.service.leaguerDetail.data.SortTypeEnum;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerUpdateInfoApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.SaveFollowUserForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.common.validate.info.ValidateInfo;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class LeaguerDetailMgrTest {
	private static Boss boss;
	private static long storeId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		BRobotData data = boss.getRobot().getData();
		data.setPassword("123456");
		data.setPhone(13660623953L);
		boss.login();
		storeId = boss.getRobotStoreId();
	}
	
	@Test
	public void testSaveFollowUser() {
		ClerkInfo clerk = boss.getRandomClerk(storeId);
		Leaguer leaguer = boss.getRandomLeaguer(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerDetail detail = LeaguerDetailMgr.getInstance().getLeaguerDetail(storeId, leaguer.getId());
		System.out.println(JsonUtil.getInstance().toJson(detail));
		
		SaveFollowUserForm inputForm = SaveFollowUserForm.newInstance();
		inputForm.setLeaguerId(leaguer.getId());
		inputForm.getBuserIds().add(clerk.getBuserId());
		StoreLeaguerInfoMgr.getInstance().saveFollowUser(storeId, inputForm);
		
		LeaguerDetail detail2 = LeaguerDetailMgr.getInstance().getLeaguerDetail(storeId, leaguer.getId());
		System.out.println(JsonUtil.getInstance().toJson(detail2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	@Test
	public void testUpdateMemberCard() {
		storeId = 16052L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String leaguerId = storeId + "_66977";
		LeaguerDetail data = LeaguerDetailDataHolder.getInstance().getData(String.valueOf(boss.getId()), leaguerId, storeId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		LeaguerMemberCard leaguerMemberCard = data.getLeaguerMemberCard();
		UpdateMemberCardForm upMemberCardForm = UpdateMemberCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(leaguerMemberCard, upMemberCardForm);
		upMemberCardForm.setLeaguerId(leaguerId);
		StoreLeaguerInfoMgr.getInstance().updateMemberCard(storeId, upMemberCardForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		storeId = 509L;
//		Leaguer leaguer = boss.getRandomLeaguer(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String leaguerId = "509_6963";
//		LeaguerDetail data = LeaguerDetailDataHolder.getInstance().getData(String.valueOf(boss.getId()), leaguerId, storeId);
//		System.out.println(JsonUtil.getInstance().toJson(data));
		
		ValidateInfo info = ValidateInfo.newInstance(1457L, 509L);
		AccessTokenMgr.getInstance().putValidateInfo(boss.getId(), info);
		LeaguerDetail data2 = LeaguerDetailMgr.getInstance().getLeaguerDetail(storeId, leaguerId);
		System.out.println(JsonUtil.getInstance().toJson(data2));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
//		queryForm.setLeaguerNameOrPhone("136");
//		queryForm.setLeaguerType(LeaguerTypeEnum.ATTENTION_CUSTOMER.ordinal());
		queryForm.setSortType(SortTypeEnum.MonthRate.ordinal());
		queryForm.setSort(SortEnum.DESC.ordinal());
		PageResp<LeaguerDetail> page = LeaguerDetailMgr.getInstance().getLeaguerDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<LeaguerDetail> list = page.getList();
		for (LeaguerDetail detail : list) {
			System.out.println(detail.getName()+"\t"+detail.getMonthRate());
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testCheckMemberCardExist() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String number="279523";
		MemberCardExist memberCardExist = LeaguerDetailMgr.getInstance().checkMemberCardExist(number, storeId);
		System.out.println(JsonUtil.getInstance().toJson(memberCardExist));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerDetail data = LeaguerDetailDataHolder.getInstance().getData(String.valueOf(boss.getId()), "21_67136", storeId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		
		LeaguerUpdateInfoApiForm inputForm = LeaguerUpdateInfoApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(data, inputForm);
		inputForm.setName("name-"+RandomUtils.nextInt(10, 100));
		StoreLeaguerInfoMgr.getInstance().updateLeaguerInfo(storeId, inputForm);
		
		LeaguerDetail data2 = LeaguerDetailDataHolder.getInstance().getData(String.valueOf(boss.getId()), "21_67136", storeId);
		System.out.println(JsonUtil.getInstance().toJson(data2));
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
