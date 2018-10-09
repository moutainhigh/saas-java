package com.hq.customerMS.service.areaCode.bs;

import java.util.List;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeClient.service.areaCode.bs.AreaCodeClientMgr;
import com.hq.storeClient.service.areaCode.data.AreaCode;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeDataHolder {

	public static AreaCodeDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeDataHolder.class);
	}

	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<AreaCode> data = AreaCodeClientMgr.getInstance().findByCond(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
