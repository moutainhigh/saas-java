package com.hq.chainClient.service.chainUser.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chainUser.apiData.ChainUserQueryForm;
import com.hq.chainClient.service.chainUser.apiData.ChainUserUpdateInfoForm;
import com.hq.chainClient.service.chainUser.data.ChainUser;
import com.hq.chainClient.service.chainUser.data.ChainUserDto;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ChainUserMgrTest2 {
	private static Boss boss;
	private static long chainId = 12L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}

	@Test
	public void testFindByCond() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainUserQueryForm queryForm = ChainUserQueryForm.newInstance();
		queryForm.setChainId(chainId);
//		queryForm.setPhoneOrName("136");
		queryForm.setRoleId(0);
//		queryForm.getChainUserIds().add(5L);
//		queryForm.getChainUserIds().add(2L);
		PageResp<ChainUserDto> page = ChainUserClientMgr.getInstance().findByCond(queryForm);
		List<ChainUserDto> list = page.getList();
		for (ChainUserDto chainUserDto : list) {
			System.out.println(JsonUtil.getInstance().toJson(chainUserDto));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainUser chainUser = ChainUserClientMgr.getInstance().get(boss.getId());
		ChainUserUpdateInfoForm inputForm = ChainUserUpdateInfoForm.newInstance();
		FastBeanCopyer.getInstance().copy(chainUser, inputForm);
		inputForm.setName("kevin");
		ChainUserClientMgr.getInstance().updateInfo(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
