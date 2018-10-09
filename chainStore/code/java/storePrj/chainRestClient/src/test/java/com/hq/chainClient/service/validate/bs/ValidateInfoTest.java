package com.hq.chainClient.service.validate.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.common.validate.ValidateThreadLocal;
import com.hq.chainClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainClient.service.areaCode.bs.AreaCodeClientMgr;
import com.hq.chainClient.service.areaCode.data.AreaCode;
import com.hq.chainClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class ValidateInfoTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testList() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		List<AreaCode> areaCodes = AreaCodeClientMgr.getInstance().findByCond(AreaCodeQueryForm.newInstance());
		ValidateThreadLocal.getInstance().remove();
		System.out.println(JsonUtil.getInstance().toJson(areaCodes));
	}
}
