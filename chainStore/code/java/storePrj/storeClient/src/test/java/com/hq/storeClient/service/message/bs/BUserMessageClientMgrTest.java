package com.hq.storeClient.service.message.bs;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.message.apiData.BUserMessageAddForm;
import com.hq.storeClient.service.message.apiData.BUserMessageAddListForm;
import com.hq.storeClient.service.message.apiData.MessageQueryForm;
import com.hq.storeClient.service.message.data.BUserMessage;
import com.hq.storeClient.service.message.data.MsgUnReadCount;
import com.hq.storeClient.service.message.data.TriggerTypeEnum;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class BUserMessageClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testAddBUserMessage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		BUserMessageAddForm inputForm = BUserMessageAddForm.newInstance();
		inputForm.setBuserId(20L).setStoreId(16052L).setMessageBody("kevin连锁总店产品有新上架信息，请注意查看。")
		.setMessageObjId("_cgti_10_3").setMessageType(TriggerTypeEnum.CHAIN_PRODUCT_UP.ordinal());
		BUserMessageAddListForm list = BUserMessageAddListForm.newInstance();
		list.getMessageAddForms().add(inputForm);
		BUserMessage data = BUserMessageClientMgr.getInstance().addBUserMessage(list);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindPage() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		Set<Integer> messageType = new HashSet<Integer>();
		MessageQueryForm queryForm = MessageQueryForm.newInstance();
		queryForm.setBuserId(20L);
		messageType.add(5);
		queryForm.setMessageType(messageType);
		PageResp<BUserMessage> page = BUserMessageClientMgr.getInstance().findPage(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(page));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testFindUnReadCount() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		MessageQueryForm queryForm = MessageQueryForm.newInstance();
		queryForm.setBuserId(20L);
		MsgUnReadCount unRead = BUserMessageClientMgr.getInstance().findUnReadCount(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(unRead));
		ValidateThreadLocal.getInstance().remove();
	}

}
