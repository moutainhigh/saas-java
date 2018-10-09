package com.hq.chainClient.service.chainCard.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainClient.service.chainCard.data.MembershipCard;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.json.JsonUtil;

public class MemberCardDetailMgrTest {
	private static Boss boss;
	private static long chainId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}

	@Test
	public void testGet() {
		MembershipCard MembershipCard = boss.getRandomMembershipCard(chainId);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MembershipCardDetail MembershipCardDetail = MembershipCardDetailClientMgr.getInstance().getMembershipCardDetail(chainId, MembershipCard.getId());
		System.out.println(JsonUtil.getInstance().toJson(MembershipCardDetail));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testList() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		MembershipCardDetailQueryForm queryForm = MembershipCardDetailQueryForm.newInstance();
		queryForm.setChainId(chainId);
		PageResp<MembershipCardDetail> page = MembershipCardDetailClientMgr.getInstance().getMembershipCardDetailPageInfo(queryForm);
		System.out.println(page.getTotalCount());
		List<MembershipCardDetail> list = page.getList();
		for (MembershipCardDetail detail : list) {
			System.out.println(JsonUtil.getInstance().toJson(detail));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
