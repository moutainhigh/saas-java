package com.hq.storeManagerMS.service.areaCode.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeManagerMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeManagerMS.service.areaCode.data.AreaCode;
import com.hq.storeManagerMS.service.areaCode.data.AreaCodeCacheDAO;
import com.hq.storeManagerMS.service.areaCode.data.AreaCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeDataHolder {
	
	public static AreaCodeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeDataHolder.class);
	}
	
	public AreaCode get(long id) {
		AreaCode data = AreaCodeCacheDAO.getInstance().get(id);
		if(data == null) {
			data = AreaCodeDAO.getInstance().get(id);
			if(data!=null) {
				AreaCodeCacheDAO.getInstance().save(data);
			}
		}
		return data;
	}
	
	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		List<AreaCode> list = AreaCodeCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)) {
			list = AreaCodeDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)) {
				AreaCodeCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
}
