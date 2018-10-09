package com.hq.storeMS.service.areaCode.bs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeMS.service.areaCode.data.AreaCode;
import com.hq.storeMS.service.sysDataInit.data.SysInitAreaCodeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeMgr {

	public static AreaCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeMgr.class);
	}
	
	public void init(){
		AreaCodeQueryForm queryForm = AreaCodeQueryForm.newInstance();
		List<AreaCode> list = this.findByCond(queryForm);
		Map<String, AreaCode> map = new HashMap<String, AreaCode>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (AreaCode areaCode : list) {
				map.put(areaCode.getCountryEn(), areaCode);
			}
		}
		SysInitAreaCodeEnum[] values = SysInitAreaCodeEnum.values();
		for (SysInitAreaCodeEnum sysInitAreaCodeEnum : values) {
			if(!map.containsKey(sysInitAreaCodeEnum.getCountryEn())) {
				this.addAndReturnId(AreaCode.newInstanceByAreaCodeEnum(sysInitAreaCodeEnum));
			}
		}
	}
	
	public void addAndReturnId(AreaCode target) {
		AreaCodeDataHolder.getInstance().addAndReturnId(target);
	}
	
	public AreaCode get(long id){
		return AreaCodeDataHolder.getInstance().get(id);
	}
	
	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		return AreaCodeDataHolder.getInstance().findByCond(queryForm);
	}
}
