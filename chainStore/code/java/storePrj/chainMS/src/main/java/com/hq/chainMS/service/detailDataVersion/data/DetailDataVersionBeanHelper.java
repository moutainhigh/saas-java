package com.hq.chainMS.service.detailDataVersion.data;

import java.util.Map;

import com.zenmind.common.hotSwap.HotSwap;

public class DetailDataVersionBeanHelper {

	public static DetailDataVersionBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionBeanHelper.class);
	}
	
	//详情的数据版本号加1
	public boolean incrVer(DetailDataVersion dataVersion, int dataVersionEnumIndex) {
		if (dataVersion == null) {
			return false;
		}
		boolean success = false;
		Map<Integer, Long> detailDataVerMap = dataVersion.getDetailDataVerMap();
		if (detailDataVerMap.containsKey(dataVersionEnumIndex)) {
			long ver = detailDataVerMap.get(dataVersionEnumIndex);
			detailDataVerMap.put(dataVersionEnumIndex, ver++);
			success = true;
		}else if(dataVersionEnumIndex < DataVersionEnum.values().length) {
			detailDataVerMap.put(dataVersionEnumIndex, 0L);
			success = true;
		}
		return success;
	}

}
