package com.hq.storeClient.service.charge.bs;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeClient.service.charge.data.Charge;
import com.hq.storeClient.service.charge.data.ChargeOriginEnum;
import com.hq.storeClient.service.charge.data.ChargeStatusEnum;
import com.hq.storeClient.service.charge.data.ChargeTypeEnum;
import com.hq.storeClient.service.vipLevel.data.ValidPeriodUnitEnum;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class ChargeClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	/**
	 * 查询 收费 列表
	 */
	@Test
	public void testFindChargePageInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		ChargeQueryForm queryForm = ChargeQueryForm.newInstance();
		queryForm.setOrigin(ChargeOriginEnum.ManagerMS.ordinal());
		queryForm.setStatus(ChargeStatusEnum.HAS_PAY.ordinal());
		queryForm.setMaxCreateTime(System.currentTimeMillis());
		PageResp<Charge> pageResp = ChargeClientMgr.getInstance().findChargePageInfo(queryForm);
		List<Charge> list = pageResp.getList();
		System.out.println(list.size());
		System.out.println(JsonUtil.getInstance().toJson(list));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGetCharge() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long chargeId = 24L;
		Charge charge = ChargeClientMgr.getInstance().getCharge(chargeId);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdateChargeStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCharge() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		ChargeAddForm formInfo = ChargeAddForm.newInstance();
		formInfo.setBuserId(20L);
		formInfo.setName("kevin");
		formInfo.setChargeType(ChargeTypeEnum.RENEW.ordinal());
		formInfo.setDiscountAmount(500L);
		formInfo.setExpiredTime(System.currentTimeMillis());
		formInfo.setOffsetAmount(50L);
		formInfo.setOrigin(ChargeOriginEnum.StoreMS.ordinal());
		formInfo.setPhone(13660623953L);
		formInfo.setValidPeriod(12);
		formInfo.setValidPeriodUnit(ValidPeriodUnitEnum.MONTH.ordinal());
		formInfo.setVipLevelId(5);
		formInfo.setVipLevelName("宏强定制版");
		Charge charge = ChargeClientMgr.getInstance().addCharge(formInfo);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		ValidateThreadLocal.getInstance().remove();
	}

}
