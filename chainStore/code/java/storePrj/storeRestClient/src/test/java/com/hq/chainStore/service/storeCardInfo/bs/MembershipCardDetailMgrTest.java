package com.hq.chainStore.service.storeCardInfo.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.chainStore.service.membershipCardDetail.bs.MembershipCardDetailMgr;
import com.hq.chainStore.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.chainStore.service.membershipCardDetail.data.MembershipCardDetailDataHolder;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;
import com.zenmind.common.json.JsonUtil;

public class MembershipCardDetailMgrTest {
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
	public void testGet() {
		MembershipCard card = boss.getRandomMembershipCard(storeId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		String id = card.getId();
		MembershipCardDetailMgr.getInstance().getMembershipCardDetail(storeId, id);
		MembershipCardDetailDataHolder.getInstance().getData(String.valueOf(boss.getId()), id, storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MembershipCardDetailQueryForm queryForm = MembershipCardDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<MembershipCardDetail> page = MembershipCardDetailMgr.getInstance().getMembershipCardDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<MembershipCardDetail> list = page.getList();
		for (MembershipCardDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
