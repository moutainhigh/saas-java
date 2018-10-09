package com.hq.storeClient.service.sellItem.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.sellItem.apiData.SellItemIdForm;
import com.hq.storeClient.service.sellItem.apiData.SellItemQueryForm;
import com.hq.storeClient.service.sellItem.data.SellItem;
import com.hq.storeClient.service.sellItem.data.SellItemTypeEnum;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class SellItemClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		SellItem sellItem = SellItemClientMgr.getInstance().get(16052L, SellItemTypeEnum.GOODS.ordinal(), "11");
		System.out.println(JsonUtil.getInstance().toJson(sellItem));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGetSellItemList() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long storeId = 16052L;
		SellItemQueryForm queryForm = SellItemQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.addSellItemIdForm(SellItemIdForm.newInstance(SellItemTypeEnum.GOODS.ordinal(), "11"));
		queryForm.addSellItemIdForm(SellItemIdForm.newInstance(SellItemTypeEnum.GOODS.ordinal(), "12"));
		List<SellItem> list = SellItemClientMgr.getInstance().getSellItemList(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(list));
		ValidateThreadLocal.getInstance().remove();
	}

}
