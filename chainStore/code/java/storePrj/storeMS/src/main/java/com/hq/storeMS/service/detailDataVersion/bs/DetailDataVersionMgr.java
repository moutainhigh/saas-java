package com.hq.storeMS.service.detailDataVersion.bs;

import java.util.Map;

import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersion;
import com.zenmind.common.hotSwap.HotSwap;

public class DetailDataVersionMgr {

	public static DetailDataVersionMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionMgr.class);
	}
	
	public void addWithId(DetailDataVersion target) {
		DetailDataVersionDataHolder.getInstance().addWithId(target);
	}

	public void delete(DetailDataVersion target) {
		DetailDataVersionDataHolder.getInstance().delete(target);
	}

	public DetailDataVersion get(long id) {
		DetailDataVersion detailDataVersion = DetailDataVersionDataHolder.getInstance().get(id);
		//不存在则创建
		if(detailDataVersion == null){
			detailDataVersion = DetailDataVersion.newInstance(id);
			addWithId(detailDataVersion);
		}
		return detailDataVersion;
	}
	
	public void update(DetailDataVersion target) {
		DetailDataVersionDataHolder.getInstance().update(target);
	}
	
	public void updateByStoreId(long storeId, DataVersionEnum dataVersionEnum) {
		DetailDataVersion detailDataVer = get(storeId);
		Map<Integer, Long> detailDataVerMap = detailDataVer.getDetailDataVerMap();
		Long ver = detailDataVerMap.get(dataVersionEnum.ordinal());
		if(ver == null) {
			detailDataVerMap.put(dataVersionEnum.ordinal(), 0L);
		}else {
			detailDataVerMap.put(dataVersionEnum.ordinal(), ver + 1);
		}
		update(detailDataVer);
	}
}
