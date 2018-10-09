package com.hq.storeManagerMS.service.sysDataInit.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeClient.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.sysDataInit.data.StoreVipLevelEnum;
import com.hq.storeManagerMS.service.sysDataInit.data.VipLevelTypeEnum;
import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.bs.VipLevelDataHolder;
import com.hq.storeManagerMS.service.vipLevel.bs.VipLevelMgr;
import com.hq.storeManagerMS.service.vipLevel.data.ValidPeriodUnitEnum;
import com.hq.storeManagerMS.service.vipLevel.data.VipContent;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevel;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevelStateEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelInitMgr {

	public static VipLevelInitMgr getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelInitMgr.class);
	}

	// 商户会员等级初始化
	public void init() {
		StoreVipLevelEnum[] values = StoreVipLevelEnum.values();
		long maxId = (long)values.length;
		VipLevel one = VipLevelDataHolder.getInstance().findOneByMaxId(maxId);
		
		if(one == null) {//没有大于len的等级信息
			PageResp<VipLevel> page = VipLevelMgr.getInstance().findPage(QueryVipLevelForm.newInstance());
			int len = values.length - (int)page.getTotalCount();
			for (int i=0; i < len; i++) {
				VipLevelMgr.getInstance().add(VipLevel.newInstance());
			}
		}
		
		for (StoreVipLevelEnum storeVipLevelEnum : values) {
			VipLevel vipLevel = buildVipLevel(storeVipLevelEnum);
			VipLevelMgr.getInstance().saveOrUpdate(vipLevel);
		}
	}
	
	private VipLevel buildVipLevel(StoreVipLevelEnum vipLevelEnum) {
		boolean honkonFlag = false;
		if(vipLevelEnum==StoreVipLevelEnum.HonKonUser) {
			honkonFlag = true;
		}
		
		VipLevel data = VipLevel.newInstance();
		data.setId(vipLevelEnum.ordinal());
		data.setName(vipLevelEnum.getMark());
		data.setNumber(StringUtils.leftPad(data.getId()+"", 4, "0"));
		data.setOpenCharge(0L);
		data.setRenewCharge(0L);
		data.setState(VipLevelStateEnum.CLOSE.ordinal());
		if(honkonFlag) {
			data.setTypeId(VipLevelTypeEnum.HonKonEdition.ordinal());
		}else {
			data.setTypeId(VipLevelTypeEnum.StandardEdition.ordinal());
		}
		data.setValidPeriod(12);
		data.setValidPeriodUnit(ValidPeriodUnitEnum.MONTH.ordinal());
		data.setDefualtImg("img/logo/package/default.png");
		data.setVipContent(getVipContent(honkonFlag));
		return data;
	}
	
	private VipContent getVipContent(boolean honkonFlag) {
		int maxNum = 20000;
		VipContent vipContent = VipContent.newInstance();
		vipContent.setBuserLimit(maxNum);
		vipContent.setGoodsLimit(maxNum);
		vipContent.setLeaguerLimit(maxNum);
		vipContent.setMemberCardLimit(maxNum);
		vipContent.setPackageLimit(maxNum);
		vipContent.setPrdCardLimit(maxNum);
		vipContent.setProductLimit(maxNum);
		vipContent.setStoreLimit(10);
		
		StoreAdminPermEnum[] values = StoreAdminPermEnum.values();
		for (StoreAdminPermEnum storeAdminPermEnum : values) {
			//排除老板和仪器管理
			if(storeAdminPermEnum!=StoreAdminPermEnum.BOSS || storeAdminPermEnum!=StoreAdminPermEnum.DEVICE_ADMIN) {
				vipContent.addPerm(storeAdminPermEnum.ordinal());
			}
		}
		
		//宏强定制会员
		if(honkonFlag) {
			vipContent.addPerm(StoreAdminPermEnum.DEVICE_ADMIN.ordinal());
		}
		
		return vipContent;
	}

}
