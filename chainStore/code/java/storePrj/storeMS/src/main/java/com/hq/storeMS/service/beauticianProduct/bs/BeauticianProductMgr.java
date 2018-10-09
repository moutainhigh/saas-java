package com.hq.storeMS.service.beauticianProduct.bs;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProduct;
import com.zenmind.common.hotSwap.HotSwap;

public class BeauticianProductMgr {

	public static BeauticianProductMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BeauticianProductMgr.class);
	}
	
	public void addWithId(BeauticianProduct target) {
		BeauticianProductDataHolder.getInstance().addWithId(target);
	}
	
	public void update(BeauticianProduct target) {
		BeauticianProductDataHolder.getInstance().update(target);
	}
	
	public BeauticianProduct get(String id){
		BeauticianProduct beauticianProduct = BeauticianProductDataHolder.getInstance().get(id);
		if(beauticianProduct == null){//不存在则添加
			String[] ids = id.split(ServerConstants.JOIN_SYMBOL);
			beauticianProduct = BeauticianProduct.newInstance(Long.valueOf(ids[0]), Long.valueOf(ids[1]));
			BeauticianProductDataHolder.getInstance().addWithId(beauticianProduct);
		}
		return beauticianProduct;
	}
	
}
