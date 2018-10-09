package com.hq.storeClient.service.storeConfig.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeConfig.apiData.ShareDataForm;
import com.hq.storeClient.service.storeConfig.apiData.StoreConfigUpdateForm;
import com.hq.storeClient.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.hq.storeClient.service.storeConfig.data.chain.ShareEnum;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreConfigClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGetStoreConfigs() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		String storeIds = "16052,16045";
		List<StoreConfig> storeConfigs = StoreConfigClientMgr.getInstance().getStoreConfigs(storeIds);
		System.out.println(JsonUtil.getInstance().toJson(storeConfigs));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindStoreConfigByStoreId() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		StoreConfig storeConfig = StoreConfigClientMgr.getInstance().findStoreConfigByStoreId(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeConfig));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdate() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 4L;
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		
		ShareDataForm dataForm = ShareDataForm.newInstance();
		dataForm.setChainId(1L);
		dataForm.setShareFlag(ShareEnum.SHARE.ordinal());
		updateForm.setUpdateType(StoreConfigUpdateType.SaveShareData.ordinal());
		updateForm.setShareDataForm(dataForm);
		
//		LeaguerBirthdayConfig birthdayConfig = LeaguerBirthdayConfig.newInstance();
//		birthdayConfig.setCount(0);
//		birthdayConfig.setUseFlag(UseFlagEnum.Enable.ordinal());
//		LeaguerBirthdayUpdateForm leaguerBirthdayUpdateForm = LeaguerBirthdayUpdateForm.newInstance();
//		leaguerBirthdayUpdateForm.getBirthdayConfigs().add(birthdayConfig);
//		updateForm.setUpdateType(StoreConfigUpdateType.UpdateLeaguerBirthday.ordinal());
//		updateForm.setLeaguerBirthdayUpdateForm(leaguerBirthdayUpdateForm);
		
		StoreConfigClientMgr.getInstance().update(storeId, updateForm);
		ValidateThreadLocal.getInstance().remove();
	}

}
