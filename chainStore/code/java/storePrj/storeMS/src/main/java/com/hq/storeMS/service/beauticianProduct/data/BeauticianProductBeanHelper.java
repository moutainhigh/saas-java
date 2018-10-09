package com.hq.storeMS.service.beauticianProduct.data;

import java.util.List;

import com.hq.storeMS.service.beauticianProduct.apiData.AddForemost;
import com.hq.storeMS.service.beauticianProduct.apiData.RemoveForemost;
import com.zenmind.common.hotSwap.HotSwap;

public class BeauticianProductBeanHelper {

	public static BeauticianProductBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(BeauticianProductBeanHelper.class);
	}

	public boolean addForemost(BeauticianProduct beauticianProduct, AddForemost addForemost) {
		List<Long> ids = beauticianProduct.getProductIds();
		if (!ids.contains(addForemost.getProductId())) {
			ids.add(0, addForemost.getProductId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeForemost(BeauticianProduct beauticianProduct, RemoveForemost removeForemost) {
		List<Long> ids = beauticianProduct.getProductIds();
		if (ids.contains(removeForemost.getProductId())) {
			ids.remove(removeForemost.getProductId());
			return true;
		} else {
			return false;
		}
	}
}
