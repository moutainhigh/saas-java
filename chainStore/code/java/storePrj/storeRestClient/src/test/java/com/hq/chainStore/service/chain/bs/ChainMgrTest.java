package com.hq.chainStore.service.chain.bs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.chain.apiData.ApplyChainForm;
import com.hq.chainStore.service.chain.apiData.ChainQueryForm;
import com.hq.chainStore.service.chain.apiData.RelieveStoreForm;
import com.hq.chainStore.service.chain.data.Chain;
import com.hq.chainStore.service.common.PageResp;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class ChainMgrTest {
	private static Boss boss;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
	}

	@Test
	public void testGet() {
		long chainId=15L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Chain data = ChainMgr.getInstance().getChain(chainId);
		System.out.println(JsonUtil.getInstance().toJson(data));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testApplyChain() {
		long chainId=15L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ApplyChainForm inputForm = ApplyChainForm.newInstance();
		inputForm.setStoreId(16055L);
		inputForm.setChainId(chainId);
		ChainMgr.getInstance().applyChain(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRelieveStore() {
		long chainId=10L;
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		RelieveStoreForm inputForm = RelieveStoreForm.newInstance();
		inputForm.setStoreId(16052L);
		inputForm.setChainId(chainId);
		ChainMgr.getInstance().relieveStore(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindChainByCond() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainQueryForm queryForm = ChainQueryForm.newInstance();
		Set<Long> chainIds = new HashSet<Long>();
		chainIds.add(12L);
		chainIds.add(15L);
		queryForm.setChainIds(chainIds);
		PageResp<Chain> page = ChainMgr.getInstance().findChainByCond(queryForm);
		List<Chain> list = page.getList();
		for (Chain chain : list) {
			System.out.println(chain.getName());
//			System.out.println(JsonUtil.getInstance().toJson(chain));
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
