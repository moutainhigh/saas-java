package com.hq.storeMS.service.vipLevel.bs;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.vipLevel.data.VipLevelCacheDAO;
import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.bs.VipLevelClientMgr;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelDataHolder {
	
	public static VipLevelDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(VipLevelDataHolder.class);
	}
	
	public VipLevel get(long id) {
		VipLevel data = VipLevelCacheDAO.getInstance().get(id);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = VipLevelClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public PageResp<VipLevel> findPage(QueryVipLevelForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<VipLevel> data = VipLevelClientMgr.getInstance().findPage(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
