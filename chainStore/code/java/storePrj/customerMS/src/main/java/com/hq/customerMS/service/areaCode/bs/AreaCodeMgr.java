package com.hq.customerMS.service.areaCode.bs;

import java.util.List;

import com.hq.storeClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeClient.service.areaCode.data.AreaCode;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeMgr {

	public static AreaCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeMgr.class);
	}
	
	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		return AreaCodeDataHolder.getInstance().findByCond(queryForm);
	}
}
