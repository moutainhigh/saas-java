package com.hq.storeManagerMS.service.vipLevel.bs;

import java.util.List;

import com.hq.storeManagerMS.common.util.PageUtil;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelMgr {

	public static VipLevelMgr getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelMgr.class);
	}
	
	public void saveOrUpdate(VipLevel target) {
		VipLevel vipLevel = get(target.getId());
		if(vipLevel == null) {
			VipLevelDataHolder.getInstance().addWithId(target);
		}else {
			VipLevelDataHolder.getInstance().update(target);
		}
	}
	
	public VipLevel get(long id) {
		return VipLevelDataHolder.getInstance().get(id);
	}
	
	public void add(VipLevel target) {
		VipLevelDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void update(VipLevel target) {
		VipLevelDataHolder.getInstance().update(target);
	}
	
	public PageResp<VipLevel> findPage(QueryVipLevelForm queryForm){
		List<VipLevel> resultList = VipLevelDataHolder.getInstance().findList(queryForm);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
}
