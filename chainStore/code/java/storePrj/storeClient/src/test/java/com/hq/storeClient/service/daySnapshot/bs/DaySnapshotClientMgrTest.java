package com.hq.storeClient.service.daySnapshot.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.daySnapshot.apiData.DaySnapshotAddForm;
import com.hq.storeClient.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.hq.storeClient.service.daySnapshot.data.DaySnapshot;
import com.hq.storeClient.service.daySnapshot.data.PreDaySnapshotData;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class DaySnapshotClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long maxTime = System.currentTimeMillis();
		long minTime = maxTime - 60L * 24 * 3600 * 1000;
		long storeId = 16052L;
		DaySnapshotQueryForm queryForm = DaySnapshotQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		PageResp<DaySnapshot> page = DaySnapshotClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGetPreDaySnapshotData() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long maxTime = System.currentTimeMillis();
		long minTime = maxTime - 30L * 24 * 3600 * 1000;
		long storeId = 16052L;
		DaySnapshotQueryForm queryForm = DaySnapshotQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		PreDaySnapshotData data = DaySnapshotClientMgr.getInstance().getPreDaySnapshotData(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testAddDaySnapshotData() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long maxTime = System.currentTimeMillis();
		long minTime = maxTime - 30L * 24 * 3600 * 1000;
		long storeId = 16052L;
		DaySnapshotQueryForm queryForm = DaySnapshotQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		PreDaySnapshotData data = DaySnapshotClientMgr.getInstance().getPreDaySnapshotData(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		
		DaySnapshotAddForm addForm = DaySnapshotAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(data, addForm);
		addForm.setStoreId(storeId);
		addForm.setStartTime(minTime);
		addForm.setEndTime(maxTime);
		addForm.setBuserId(20L);
		addForm.setBuserName("kevin");
		addForm.setRemark("测试");
		DaySnapshotClientMgr.getInstance().addDaySnapshot(addForm);
		
		ValidateThreadLocal.getInstance().remove();
	}

}
