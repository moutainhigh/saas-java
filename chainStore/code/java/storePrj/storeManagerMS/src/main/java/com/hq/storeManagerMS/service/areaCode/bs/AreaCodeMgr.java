package com.hq.storeManagerMS.service.areaCode.bs;

import java.util.List;

import com.hq.storeManagerMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeManagerMS.service.areaCode.data.AreaCode;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeMgr {

	public static AreaCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeMgr.class);
	}
	
	public AreaCode get(long id){
		return AreaCodeDataHolder.getInstance().get(id);
	}
	
	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		return AreaCodeDataHolder.getInstance().findByCond(queryForm);
	}
}
