package com.hq.storeMS.service.vipLevel.bs;

import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelMgr {

	public static VipLevelMgr getInstance(){
		return HotSwap.getInstance().getSingleton(VipLevelMgr.class);
	}
	
	public VipLevel getRO(long id){
		return VipLevelDataHolder.getInstance().get(id);
	}
	
	public PageResp<VipLevel> findPage(QueryVipLevelForm queryForm){
		return VipLevelDataHolder.getInstance().findPage(queryForm);
	}
}
