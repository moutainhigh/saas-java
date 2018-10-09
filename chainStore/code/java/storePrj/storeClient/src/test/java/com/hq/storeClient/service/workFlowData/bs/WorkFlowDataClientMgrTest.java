package com.hq.storeClient.service.workFlowData.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.common.validate.info.ValidateInfo;
import com.hq.storeClient.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.storeClient.service.workFlowData.apiData.save.WorkFlowDataSaveForm;
import com.hq.storeClient.service.workFlowData.data.WorkFlowData;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class WorkFlowDataClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindWorkFlowDataPageInfo() {
		long storeId = 16052L;
		long buserId = 20L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		WorkFlowDataQueryForm queryForm = WorkFlowDataQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setBuserId(buserId);
		PageResp<WorkFlowData> page = WorkFlowDataClientMgr.getInstance().findWorkFlowDataPageInfo(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page.getTotalCount()));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGetWorkFlowData() {
		long storeId = 16052L;
		long workFlowDataId = 7957L;
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(1);
		info.setStoreId(storeId);
		ValidateThreadLocal.getInstance().setValidateInfo(info);
		WorkFlowData data = WorkFlowDataClientMgr.getInstance().getWorkFlowData(workFlowDataId);
		System.out.println(JsonUtil.getInstance().toJson(data.getEntityState()));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testDeleteWorkFlowData() {
		long storeId = 16052L;
		long workFlowDataId = 7957L;
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(1);
		info.setStoreId(storeId);
		ValidateThreadLocal.getInstance().setValidateInfo(info);
		WorkFlowDataClientMgr.getInstance().deleteWorkFlowData(workFlowDataId);
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testSaveOrUpdate() {
		long storeId = 16052L;
		long workFlowDataId = 8330L;
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(1);
		info.setStoreId(storeId);
		ValidateThreadLocal.getInstance().setValidateInfo(info);
		WorkFlowData workFlowData = WorkFlowDataClientMgr.getInstance().getWorkFlowData(workFlowDataId);
		System.out.println(JsonUtil.getInstance().toJson(workFlowData));
		WorkFlowDataSaveForm inputForm = WorkFlowDataSaveForm.newInstance();
		WorkFlowDataClientMgr.getInstance().saveOrUpdate(inputForm);
		ValidateThreadLocal.getInstance().remove();
	}

//	private WorkFlowDataSaveForm toWorkFlowDataSaveForm(WorkFlowData workFlowData) {
//		WorkFlowDataSaveForm inputForm = WorkFlowDataSaveForm.newInstance();
//		FastBeanCopyer.getInstance().copy(workFlowData, inputForm);
//		return inputForm;
//	}
}
