package com.hq.chainClient.service.chain.bs;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.common.validate.ValidateThreadLocal;
import com.hq.chainClient.service.chain.apiData.ApplyChainBatchDoForm;
import com.hq.chainClient.service.chain.apiData.ApplyChainDoForm;
import com.hq.chainClient.service.chain.apiData.ApplyChainForm;
import com.hq.chainClient.service.chain.apiData.ChainAddForm;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateInfoForm;
import com.hq.chainClient.service.chain.apiData.RelieveStoreForm;
import com.hq.chainClient.service.chain.data.ApplyStatusEnum;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.hq.chainClient.testClass.robot.chain.ChainRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ChainClientMgrTest {
	private static Boss boss;
	private static long chainId = 15L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}
	
	@Test
	public void testAdd() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainAddForm addForm = ChainRobotData.newRandomInstance().toChainAddForm();
		Chain chain = ChainClientMgr.getInstance().addChain(addForm);
		System.out.println(JsonUtil.getInstance().toJson(chain));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Chain chain = ChainClientMgr.getInstance().getChain(chainId);
		System.out.println(JsonUtil.getInstance().toJson(chain));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testFindChainByCond() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainQueryForm queryForm = ChainQueryForm.newInstance();
		queryForm.getChainIds().add(12L);
		queryForm.getChainIds().add(15L);
		queryForm.getChainIds().add(14L);
		PageResp<Chain> page = ChainClientMgr.getInstance().findChainByCond(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet2() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Chain chain = ChainClientMgr.getInstance().getChain(chainId);
		System.out.println(JsonUtil.getInstance().toJson(chain));
	}
	
	@Test
	public void testUpdate() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Chain chain = ChainClientMgr.getInstance().getChain(chainId);
		ChainUpdateInfoForm inputForm = ChainUpdateInfoForm.newInstance();
		FastBeanCopyer.getInstance().copy(chain, inputForm);
		inputForm.setName("智美连锁店2");
		inputForm.setChainId(chainId);
		ChainClientMgr.getInstance().updateChainInfo(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testApplyChain() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ApplyChainForm inputForm = ApplyChainForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setStoreId(16052L);
		ChainClientMgr.getInstance().applyChain(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRelieveStore() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		RelieveStoreForm inputForm = RelieveStoreForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setStoreId(16055L);
		ChainClientMgr.getInstance().relieveStore(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHandleApplyChain() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ApplyChainDoForm inputForm = ApplyChainDoForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setStoreId(16055L);
		inputForm.setStatus(ApplyStatusEnum.PASS.ordinal());
		ChainClientMgr.getInstance().handleApplyChain(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testBatchHandleApplyChain() {
		Set<Long> storeIds = new HashSet<Long>();
		storeIds.add(16052L);
		storeIds.add(16055L);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ApplyChainBatchDoForm inputForm = ApplyChainBatchDoForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setStoreIds(storeIds);
		inputForm.setStatus(ApplyStatusEnum.PASS.ordinal());
		ChainClientMgr.getInstance().batchHandleApplyChain(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
