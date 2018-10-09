package com.hq.storeClient.service.dynamic.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.dynamic.apiData.DynamicAddForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeClient.service.dynamic.apiData.DynamicQueryFormForCuser;
import com.hq.storeClient.service.dynamic.data.Dynamic;
import com.hq.storeClient.service.dynamic.data.DynamicItem;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class DynamicClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		DynamicQueryForm queryForm = DynamicQueryForm.newInstance();
		queryForm.setBuserId(20L).setStoreId(16052L).setContent("文案23");
		PageResp<Dynamic> page = DynamicClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testFindPageForCuser() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		DynamicQueryFormForCuser queryForm = DynamicQueryFormForCuser.newInstance();
		queryForm.addBuserId(20L);
		PageResp<Dynamic> page = DynamicClientMgr.getInstance().findPageForCuser(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Dynamic target = DynamicClientMgr.getInstance().get(1L);
		System.out.println(JsonUtil.getInstance().toJson(target));
		ValidateThreadLocal.getInstance().remove();
	}

	@Ignore
	@Test
	public void testUpdate() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
//		long dynamicId = 1L;
//		Dynamic target = DynamicClientMgr.getInstance().get(dynamicId);
		
//		DynamicUpdateInfoForm inputForm = DynamicUpdateInfoForm.newInstance();
//		inputForm.setDynamicItems(target.getDynamicItems());
//		inputForm.setImgPaths(target.getImgPaths());
//		inputForm.setDocContent("修改文案123");
//		Dynamic dynamic = DynamicClientMgr.getInstance().updateDynamicInfo(dynamicId, inputForm);
//		System.out.println(JsonUtil.getInstance().toJson(dynamic));
		
//		DynamicUpdateStatusForm inputForm = DynamicUpdateStatusForm.newInstance();
//		inputForm.setStatus(DynamicStatusEnum.Release.ordinal());
//		Dynamic dynamic = DynamicClientMgr.getInstance().updateDynamicStatus(dynamicId, inputForm);
//		System.out.println(JsonUtil.getInstance().toJson(dynamic));
		
		ValidateThreadLocal.getInstance().remove();
	}

	@Ignore
	@Test
	public void testAdd() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		DynamicAddForm addForm = DynamicAddForm.newInstance();
		addForm.setBuserId(20L);
		addForm.setDocContent("动态内容测试-"+RandomUtils.nextInt(100, 1000));
		addForm.setStoreId(16052L);
		
		List<String> imgPaths = new ArrayList<String>();
		imgPaths.add("test");
		addForm.setImgPaths(imgPaths);
		
		List<DynamicItem> dynamicItems = new ArrayList<DynamicItem>();
		DynamicItem item = DynamicItem.newInstance();
		item.setSellItemType(2);
		item.setId("11");
		dynamicItems.add(item);
		addForm.setDynamicItems(dynamicItems);
		
		Dynamic target = DynamicClientMgr.getInstance().add(addForm);
		System.out.println(JsonUtil.getInstance().toJson(target));
		ValidateThreadLocal.getInstance().remove();
	}

}
