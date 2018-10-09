package com.hq.storeManagerRestClient.service.charge.bs;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.common.validate.ValidateThreadLocal;
import com.hq.storeManagerRestClient.service.buser.bs.BUserClientMgr;
import com.hq.storeManagerRestClient.service.buser.data.BUser;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateInfoForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdatePayInfoForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateStatusForm;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.hq.storeManagerRestClient.service.charge.data.ChargeChannelEnum;
import com.hq.storeManagerRestClient.service.charge.data.ChargeOriginEnum;
import com.hq.storeManagerRestClient.service.charge.data.ChargePayItem;
import com.hq.storeManagerRestClient.service.charge.data.ChargeStatusEnum;
import com.hq.storeManagerRestClient.testClass.AccessTokenMgr;
import com.hq.storeManagerRestClient.testClass.TestEnv;
import com.hq.storeManagerRestClient.testClass.robot.muser.Admin;
import com.hq.storeManagerRestClient.testClass.robot.muser.MUserRobot;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ChargeClientMgrTest {
	private static Admin admin;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		admin = Admin.newAdmin(MUserRobot.newRandom());
		admin.loginWithParam("admin", "admin");
	}

	/**
	 * 修改收费信息
	 */
	@Test
	public void testUpdateChargePayInfo() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long chargeId = 16L;
		ChargePayItem item = ChargePayItem.newInstance();
		item.setCost(500L);
		item.setCreatedTime(System.currentTimeMillis());
		item.setPayType(ChargeChannelEnum.CASH.ordinal());
		List<ChargePayItem> payItems = new ArrayList<ChargePayItem>();
		payItems.add(item);
		ChargeUpdatePayInfoForm inputForm = ChargeUpdatePayInfoForm.newInstance();
		inputForm.setId(chargeId);
		inputForm.setStatus(ChargeStatusEnum.HAS_PAY.ordinal());
		inputForm.setPayItems(payItems);
		Charge charge = ChargeClientMgr.getInstance().updateChargePayInfo(chargeId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		ValidateThreadLocal.getInstance().remove();
	}
	
	/**
	 * 添加收费
	 */
	@Test
	public void testAddCharge() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		long buserId = 20l;
		BUser bUser = BUserClientMgr.getInstance().getBUser(buserId);
		if (bUser == null) {
			AccessTokenMgr.getInstance().removeOpIdTL();
			return;
		}
		
		List<ChargePayItem> payItems = new ArrayList<ChargePayItem>();
		ChargePayItem item = ChargePayItem.newInstance();
		item.setCost(100L);
		item.setPayType(ChargeChannelEnum.CASH.ordinal());
		payItems.add(item);
		
		ChargeAddForm form = ChargeAddForm.newInstance();
		form.setBuserId(bUser.getId());
		form.setName(bUser.getName());
		form.setPhone(bUser.getPhone());
		form.setVipLevelId(6);
		form.setVipLevelName("标准会员");
		form.setExpiredTime(1577808000000L);
		form.setAgencyArea("广东省/广州市/天河区/");
		form.setAgencyName("代理3XX");
		form.setAgencyPhone(String.valueOf(15088132621l));
		form.setOrigin(ChargeOriginEnum.ManagerMS.ordinal());
		form.setPayItems(payItems);
		Charge charge = ChargeClientMgr.getInstance().addCharge(form);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	/**
	 * 编辑收费
	 */
	@Test
	public void testUpdateCharge() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		BUser bUser = BUserClientMgr.getInstance().getBUser(20l);
		if (bUser == null) {
			AccessTokenMgr.getInstance().removeOpIdTL();
			return;
		}
		int chargeId = 16;
		Charge charge = ChargeClientMgr.getInstance().getCharge(chargeId);
		ChargeUpdateInfoForm form = ChargeUpdateInfoForm.newInstance();
		FastBeanCopyer.getInstance().copy(charge, form);
		form.setRemark("修改测试");
		ChargeClientMgr.getInstance().updateChargeInfo(charge.getId(), form);
		charge = ChargeClientMgr.getInstance().getCharge(chargeId);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateChargeStatus() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long chargeId = 6L;
		ChargeUpdateStatusForm inputForm = ChargeUpdateStatusForm.newInstance();
		inputForm.setId(chargeId);
		inputForm.setStatus(ChargeStatusEnum.HAS_PAY.ordinal());
		Charge charge = ChargeClientMgr.getInstance().updateChargeStatus(chargeId, inputForm);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		ValidateThreadLocal.getInstance().remove();
	}

	/**
	 * 删除收费
	 */
	@Test
	public void testDeleteCharge() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		int chargeId = 1;
		ChargeClientMgr.getInstance().deleteCharge(chargeId);
		Charge charge = ChargeClientMgr.getInstance().getCharge(chargeId);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	/**
	 * 根据 id 获取收费
	 */
	@Test
	public void testGetCharge() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		int chargeId = 24;
		Charge charge = ChargeClientMgr.getInstance().getCharge(chargeId);
		System.out.println(JsonUtil.getInstance().toJson(charge));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	/**
	 * 查询 收费 列表
	 */
	@Test
	public void testFindChargePageInfo() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		ChargeQueryForm queryForm = ChargeQueryForm.newInstance();
		queryForm.setOrigin(ChargeOriginEnum.ManagerMS.ordinal());
		queryForm.setStatus(ChargeStatusEnum.HAS_PAY.ordinal());
		queryForm.setMaxCreateTime(System.currentTimeMillis());
		PageResp<Charge> pageResp = ChargeClientMgr.getInstance().findChargePageInfo(queryForm);
		List<Charge> list = pageResp.getList();
		System.out.println(list.size());
		System.out.println(JsonUtil.getInstance().toJson(list));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
