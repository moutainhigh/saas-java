package com.hq.chainMS.service.detailDataVersion.bs;

import java.util.Map;

import com.hq.chainMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.chainMS.service.detailDataVersion.data.DetailDataVersion;
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

	public DetailDataVersion get(long chainId) {
		DetailDataVersion detailDataVersion = DetailDataVersionDataHolder.getInstance().get(chainId);
		//不存在则创建
		if(detailDataVersion == null){
			detailDataVersion = DetailDataVersion.newInstance(chainId);
			addWithId(detailDataVersion);
		}
		return detailDataVersion;
	}

	public void update(DetailDataVersion target) {
		DetailDataVersionDataHolder.getInstance().update(target);
	}
	
	public void updateByChainId(long chainId, DataVersionEnum dataVersionEnum) {
		DetailDataVersion detailDataVer = get(chainId);
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
