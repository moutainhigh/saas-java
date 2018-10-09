package com.hq.customerRestClient.service.areaCode.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.customerRestClient.service.areaCode.data.AreaCode;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class AreaCodeClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindUnAuth() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		List<AreaCode> areaCodes = AreaCodeClientMgr.getInstance().findByCondUnAuth(AreaCodeQueryForm.newInstance());
		System.out.println(JsonUtil.getInstance().toJson(areaCodes));
	}
}
