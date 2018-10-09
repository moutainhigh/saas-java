package com.hq.storeMS.service.cuserChainData.data;

import com.zenmind.common.hotSwap.HotSwap;

public class CuserChainDataBeanHelper {
	
	public static CuserChainDataBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CuserChainDataBeanHelper.class);
	}
	
}
