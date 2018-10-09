package com.hq.storeClient.service.storeChain.bs;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.data.StoreChainItemType;
import com.hq.storeClient.service.storeChain.data.StoreChainStatus;
import com.hq.storeClient.testClass.TestEnv;

public class StoreChainClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testBatchUpdateState() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		String id="1";
		List<StoreChainUpdateStatusForm> inputForm = new ArrayList<StoreChainUpdateStatusForm>();
		StoreChainUpdateStatusForm data = StoreChainUpdateStatusForm.newInstance();
		data.setStoreId(16052L);
		data.setItemType(StoreChainItemType.Goods.ordinal());
		data.setStatus(StoreChainStatus.Open.ordinal());
		data.setId("1");
		inputForm.add(data);
		StoreChainClientMgr.getInstance().batchUpdateState(id, inputForm);
	}

}
