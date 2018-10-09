package com.hq.chainStore.service.beauticianProduct.bs;

import com.hq.chainStore.service.beauticianProduct.apiData.AddForemost;
import com.hq.chainStore.service.beauticianProduct.apiData.BeauticianProductUpdateApiForm;
import com.hq.chainStore.service.beauticianProduct.apiData.BeauticianProductUpdateType;
import com.hq.chainStore.service.beauticianProduct.apiData.RemoveForemost;
import com.hq.chainStore.service.beauticianProduct.data.BeauticianProduct;
import com.hq.chainStore.service.beauticianProduct.data.BeauticianProductDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BeauticianProductMgr {

	public static BeauticianProductMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianProductMgr.class);
	}
	
	/**
	 * 
	 * @Deprecated 获取的方法 已经移到BeauticianProductSynDataHolder
	 *
	 */
	@Deprecated
	public BeauticianProduct getBeauticianProduct(String beauticianProductId) {
		return BeauticianProductDAO.getInstance().get(beauticianProductId);
	}

	public void updateBeauticianProduct(String beauticianProductId, BeauticianProductUpdateApiForm apiForm) {
		BeauticianProductDAO.getInstance().update(beauticianProductId, apiForm);
	}
	
	//=======================具体的修改操作=======================
	public void addForemost(String beauticianProductId, AddForemost param){
		BeauticianProductUpdateApiForm updateForm = BeauticianProductUpdateApiForm.newInstance();
		updateForm.setAddForemost(param);
		updateForm.setUpdateType(BeauticianProductUpdateType.AddForemost.ordinal());
		updateBeauticianProduct(beauticianProductId, updateForm);
	}
	
	public void removeForemost(String beauticianProductId, RemoveForemost param){
		BeauticianProductUpdateApiForm updateForm = BeauticianProductUpdateApiForm.newInstance();
		updateForm.setRemoveForemost(param);
		updateForm.setUpdateType(BeauticianProductUpdateType.RemoveForemost.ordinal());
		updateBeauticianProduct(beauticianProductId, updateForm);
	}
	
}
