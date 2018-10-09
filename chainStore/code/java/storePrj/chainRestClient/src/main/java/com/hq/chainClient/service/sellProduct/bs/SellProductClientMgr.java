package com.hq.chainClient.service.sellProduct.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.sellProduct.apiData.SellProductAllotForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductBatchAllotForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductBatchUpdateStateForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductQueryForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductUpdateApiForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductUpdateStateForm;
import com.hq.chainClient.service.sellProduct.apiData.SellProductUpdateType;
import com.hq.chainClient.service.sellProduct.data.SellProduct;
import com.hq.chainClient.service.sellProduct.data.SellProductDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SellProductClientMgr {

	public static SellProductClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(SellProductClientMgr.class);
	}
	
	public PageResp<SellProduct> getPageInfo(SellProductQueryForm queryForm) {
		final String findPath = "getPageInfo";
		return SellProductDAO.getInstance().getPageInfo(findPath, queryForm);
	}
	
	//分配
	public void allotSellProduct(long chainId, SellProductAllotForm inputForm) {
		SellProductUpdateApiForm updateForm = SellProductUpdateApiForm.newInstance();
		updateForm.setAllotSellProductForm(inputForm);
		updateForm.setUpdateType(SellProductUpdateType.AllotSellProduct.ordinal());
		update(chainId, updateForm);
	}
	
	//批量分配
	public void batchAllotSellProduct(long chainId, SellProductBatchAllotForm inputForm) {
		SellProductUpdateApiForm updateForm = SellProductUpdateApiForm.newInstance();
		updateForm.setBatchAllotSellProductForm(inputForm);
		updateForm.setUpdateType(SellProductUpdateType.BatchAllotSellProduct.ordinal());
		update(chainId, updateForm);
	}
	
	//上下架
	public void updateSellProductState(long chainId, SellProductUpdateStateForm inputForm) {
		SellProductUpdateApiForm updateForm = SellProductUpdateApiForm.newInstance();
		updateForm.setUpdateStateForm(inputForm);
		updateForm.setUpdateType(SellProductUpdateType.UpdateSellProductState.ordinal());
		update(chainId, updateForm);
	}
	
	//批量上下架
	public void batchUpdateSellProductState(long chainId, SellProductBatchUpdateStateForm inputForm) {
		SellProductUpdateApiForm updateForm = SellProductUpdateApiForm.newInstance();
		updateForm.setBatchUpdateStateForm(inputForm);
		updateForm.setUpdateType(SellProductUpdateType.BatchUpdateSellProductState.ordinal());
		update(chainId, updateForm);
	}
	
	private void update(long chainId, SellProductUpdateApiForm updateForm) {
		SellProductDAO.getInstance().update(chainId, updateForm);
	}
	
}
