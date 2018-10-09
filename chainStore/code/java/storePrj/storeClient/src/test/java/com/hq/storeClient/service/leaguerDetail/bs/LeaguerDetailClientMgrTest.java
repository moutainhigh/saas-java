package com.hq.storeClient.service.leaguerDetail.bs;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.testClass.TestEnv;

public class LeaguerDetailClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetLeaguerDetailPageInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testTriggerNotice() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		LeaguerDetailClientMgr.getInstance().triggerNotice(16052L);
		ValidateThreadLocal.getInstance().remove();
	}

}
