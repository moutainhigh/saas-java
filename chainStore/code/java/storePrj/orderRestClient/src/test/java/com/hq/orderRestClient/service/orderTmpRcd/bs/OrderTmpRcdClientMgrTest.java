package com.hq.orderRestClient.service.orderTmpRcd.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.orderRestClient.common.validate.ValidateThreadLocal;
import com.hq.orderRestClient.testClass.TestEnv;

public class OrderTmpRcdClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testCheckOrderTmpRcd() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		OrderTmpRcdClientMgr.getInstance().checkOrderTmpRcd();
		ValidateThreadLocal.getInstance().remove();
	}

}
