package com.hq.storeManagerMS.service.vipLevelType.bs;

import java.util.List;

import com.hq.storeManagerMS.common.util.PageUtil;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelTypeMgr {

	public static VipLevelTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeMgr.class);
	}
	
	public void saveOrUpdate(VipLevelType target) {
		VipLevelType data = get(target.getId());
		if(data == null) {
			VipLevelTypeDataHolder.getInstance().addWithId(target);
		}else {
			VipLevelTypeDataHolder.getInstance().update(target);
		}
	}

	public VipLevelType get(long id) {
		return VipLevelTypeDataHolder.getInstance().get(id);
	}

	public void add(VipLevelType target) {
		VipLevelTypeDataHolder.getInstance().addAndReturnId(target);
	}

	public void update(VipLevelType target) {
		VipLevelTypeDataHolder.getInstance().update(target);
	}

	public PageResp<VipLevelType> findPage(QueryVipLevelTypeForm queryForm) {
		List<VipLevelType> list = VipLevelTypeDataHolder.getInstance().findList(queryForm);
		return PageUtil.getInstance().buildPageResp(list, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
}
