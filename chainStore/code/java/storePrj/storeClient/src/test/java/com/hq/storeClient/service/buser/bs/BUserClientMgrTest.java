package com.hq.storeClient.service.buser.bs;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeClient.service.buser.apiData.BUserBindingWeChatForm;
import com.hq.storeClient.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeClient.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeClient.service.buser.apiData.BUserUpdateType;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class BUserClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testUpdate() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long buserId = 20L;
		BUserUpdateApiForm updateForm = BUserUpdateApiForm.newInstance();
		
//		updateForm.setUpdateType(BUserUpdateType.VipRecharge.ordinal());
//		BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
//		vipRechargeData.setAmount(0f);
//		vipRechargeData.setBuserId(buserId);
//		vipRechargeData.setExpiredTime(1566360155000L);
//		vipRechargeData.setVipType(1L);
//		updateForm.setVipRechargeData(vipRechargeData);
		
		String wxUnionId = "o-bgd04HUKafyykebYwz0btg_uBk";
		updateForm.setBUserUpdateType(BUserUpdateType.BindingWeChat);
		BUserBindingWeChatForm bindingWeChatForm = BUserBindingWeChatForm.newInstance();
		bindingWeChatForm.setBuserId(buserId);
		bindingWeChatForm.setWxUnionId(wxUnionId);
		updateForm.setBindingWeChatForm(bindingWeChatForm);
		
		BUser bUser = BUserClientMgr.getInstance().update(buserId, updateForm);
		System.out.println(JsonUtil.getInstance().toJson(bUser));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUser bUser = BUserClientMgr.getInstance().get(399L);
		System.out.println(JsonUtil.getInstance().toJson(bUser));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindByPhone() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUser bUser = BUserClientMgr.getInstance().findByPhone(13660623953L);
		System.out.println(JsonUtil.getInstance().toJson(bUser));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testRegByChainForm() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUserAddByChainForm formInfo = BUserAddByChainForm.newInstance();
		formInfo.setAreaCode("+86");
		formInfo.setChainId(1L);
		formInfo.setGender(1);
		formInfo.setName("kevin");
		formInfo.setPassword("d39b9b32a36b0f37805ead7e7e276d32");
		formInfo.setPhone(13507802178L);
		formInfo.setSalt("ef17438d2940a55aa472659ea9e08732");
		BUser bUser = BUserClientMgr.getInstance().regByChainForm(formInfo);
		System.out.println(JsonUtil.getInstance().toJson(bUser));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindByChain() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUserChainQueryForm queryForm = BUserChainQueryForm.newInstance();
		queryForm.setChainId(1L);
		PageResp<BUser> pageInfo = BUserClientMgr.getInstance().findByChain(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(pageInfo));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindByCond() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(0);
		queryForm.setRoleSet(roleSet);
		PageResp<BUser> pageInfo = BUserClientMgr.getInstance().findByCond(queryForm);
		System.out.println(pageInfo.getTotalCount());
//		List<BUser> list = pageInfo.getList();
//		for (BUser bUser : list) {
//			System.out.println(JsonUtil.getInstance().toJson(bUser));
//		}
		ValidateThreadLocal.getInstance().remove();
	}
}
