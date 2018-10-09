package com.hq.storeClient.service.pay.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.order.data.PayTypeEnum;
import com.hq.storeClient.service.pay.apiData.PayCallbackForm;
import com.hq.storeClient.testClass.TestEnv;

public class PayClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testPayCallback() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		PayCallbackForm form = PayCallbackForm.newInstance();
		form.setAmount(15f);
		form.setOrderId(2031L);
		form.setOrderOriginType(0);
		form.setOutTradeNo("xxx");
		form.setPayType(PayTypeEnum.CASH.ordinal());
		form.setStoreId(332L);
		form.setTradeNo("");
		PayClientMgr.getInstance().payCallback(form);
		ValidateThreadLocal.getInstance().remove();
	}

}
