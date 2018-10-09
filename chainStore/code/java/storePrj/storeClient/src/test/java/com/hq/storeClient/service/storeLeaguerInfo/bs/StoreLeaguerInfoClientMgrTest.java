package com.hq.storeClient.service.storeLeaguerInfo.bs;

import java.util.Collection;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.storeClient.service.storeLeaguerInfo.apiData.LeaguerDelApiForm;
import com.hq.storeClient.service.storeLeaguerInfo.apiData.LeaguerLabelUpdateForm;
import com.hq.storeClient.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.storeClient.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.storeClient.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeClient.testClass.AccessTokenMgr;
import com.hq.storeClient.testClass.Boss;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StoreLeaguerInfoClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		long storeId = 16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreLeaguerInfo storeData = StoreLeaguerInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeData));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdateStoreLeaguerInfo() {
		long storeId = 4L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		
		//更新会员标签
//		LeaguerLabelUpdateForm leaguerLabelUpdateForm = LeaguerLabelUpdateForm.newInstance();
//		leaguerLabelUpdateForm.setId("1");
//		leaguerLabelUpdateForm.setName("会员标签1");
//		updateForm.setStoreId(storeId);
//		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.UpdateLeaguerLabel.ordinal());
//		updateForm.setLeaguerLabelUpdateForm(leaguerLabelUpdateForm);
		
		LeaguerAddApiForm leaguerAddInfoData = LeaguerAddApiForm.newInstance();
		leaguerAddInfoData.setName("kevin"+RandomUtils.nextInt(100, 1000));
		leaguerAddInfoData.setSex(1);
		leaguerAddInfoData.setHeadImg("");
		leaguerAddInfoData.setPhone(11022222228L);
		
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddLeaguerInfo.ordinal());
		updateForm.setLeaguerAddInfoData(leaguerAddInfoData);
		updateForm.setStoreId(storeId);
		
		
		StoreLeaguerInfoClientMgr.getInstance().updateStoreLeaguerInfo(storeId, updateForm);
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testUpdateStoreLeaguerInfoWithLogin() {
		Boss boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		long storeId = boss.getStoreId();
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		LeaguerLabelUpdateForm leaguerLabelUpdateForm = LeaguerLabelUpdateForm.newInstance();
		leaguerLabelUpdateForm.setId("1");
		leaguerLabelUpdateForm.setName("会员标签12");
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.UpdateLeaguerLabel.ordinal());
		updateForm.setLeaguerLabelUpdateForm(leaguerLabelUpdateForm);
		StoreLeaguerInfoClientMgr.getInstance().updateStoreLeaguerInfo(storeId, updateForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testDelete() {
		long storeId = 209L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreLeaguerInfo storeData = StoreLeaguerInfoClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeData));
		ValidateThreadLocal.getInstance().remove();
		
		Collection<Leaguer> values = storeData.getLeaguerInfoMap().values();
		for (Leaguer data : values) {
			update(storeId, data);
			System.out.println(JsonUtil.getInstance().toJson(data));
		}
	}
	
	private void update(long storeId, Leaguer data) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		LeaguerDelApiForm removeForm = LeaguerDelApiForm.newInstance();
		removeForm.setId(data.getId());
		StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreLeaguerInfoUpdateType.DelLeaguer.ordinal());
		updateForm.setLeaguerDelInfoData(removeForm);
		StoreLeaguerInfoClientMgr.getInstance().updateStoreLeaguerInfo(storeId, updateForm);
		ValidateThreadLocal.getInstance().remove();
	}

}
