package com.hq.chainMS.service.storeGoods.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeGoods.data.StoreGoodsCacheDAO;
import com.hq.storeClient.service.storeGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.storeClient.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeClient.service.storeGoods.bs.StoreGoodsClientMgr;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsDataHolder {

	public static StoreGoodsDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsDataHolder.class);
	}

	public StoreGoods getStoreGoods(long storeId) {
		StoreGoods data = StoreGoodsCacheDAO.getInstance().get(storeId);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreGoodsClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public void batchUpdateGoodsState(long storeId, GoodsBatchUpdateStateForm dataForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreGoodsClientMgr.getInstance().batchUpdateGoodsState(storeId, dataForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void updateGoodsState(long storeId, GoodsUpdateStateForm dataForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreGoodsClientMgr.getInstance().updateGoodsState(storeId, dataForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
}
