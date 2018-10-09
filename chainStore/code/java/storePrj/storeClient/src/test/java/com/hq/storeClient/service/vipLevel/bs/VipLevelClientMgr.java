package com.hq.storeClient.service.vipLevel.bs;

import com.hq.storeClient.common.exception.AppNameAuthUtils;
import com.hq.storeClient.common.exception.AppNameEnum;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.vipLevel.apiData.StoreVipLevelQueryForm;
import com.hq.storeClient.service.vipLevel.data.VipLevel;
import com.hq.storeClient.service.vipLevel.data.VipLevelDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelClientMgr {

	public static VipLevelClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(VipLevelClientMgr.class);
	}
	
	public VipLevel get(long vipLevelId) {
		AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
		return VipLevelDAO.getInstance().get(vipLevelId);
	}
	
	public PageResp<VipLevel> findPage(StoreVipLevelQueryForm queryForm){
		AppNameAuthUtils.getInstance().checkMS(AppNameEnum.StoreMngMS);
		String findPath = "findPage";
		return VipLevelDAO.getInstance().findPage(findPath, queryForm);
	}
}
