package com.hq.storeManagerMS.service.sysDataInit.bs;

import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.sysDataInit.data.VipLevelTypeEnum;
import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.bs.VipLevelTypeDataHolder;
import com.hq.storeManagerMS.service.vipLevelType.bs.VipLevelTypeMgr;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelTypeStateEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelTypeInitMgr {

	public static VipLevelTypeInitMgr getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeInitMgr.class);
	}

	public void init() {
		VipLevelTypeEnum[] values = VipLevelTypeEnum.values();
		
		long maxId = (long)values.length;
		VipLevelType one = VipLevelTypeDataHolder.getInstance().findOneByMaxId(maxId);
		
		if(one == null) {//没有大于len的信息
			PageResp<VipLevelType> page = VipLevelTypeMgr.getInstance().findPage(QueryVipLevelTypeForm.newInstance());
			int len = values.length - (int)page.getTotalCount();
			for (int i=0; i < len; i++) {
				VipLevelTypeMgr.getInstance().add(VipLevelType.newInstance());
			}
		}
		
		for (VipLevelTypeEnum typeEnum : values) {
			VipLevelType target = VipLevelType.newInstance();
			target.setId(typeEnum.ordinal());
			target.setName(typeEnum.getMark());
			target.setState(VipLevelTypeStateEnum.CLOSE.ordinal());
			VipLevelTypeMgr.getInstance().saveOrUpdate(target);
		}
	}

}
