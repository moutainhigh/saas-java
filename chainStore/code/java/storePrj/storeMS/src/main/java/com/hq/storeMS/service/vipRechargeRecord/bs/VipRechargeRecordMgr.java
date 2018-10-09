package com.hq.storeMS.service.vipRechargeRecord.bs;

import java.util.List;

import com.hq.storeMS.service.vipRechargeRecord.data.VipRechargeRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class VipRechargeRecordMgr {
	
	public static VipRechargeRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(VipRechargeRecordMgr.class);
	}
	
	public VipRechargeRecord get(long id) {
		return VipRechargeRecordDataHolder.getInstance().get(id);
	}
	
	public void add(VipRechargeRecord target) {
		VipRechargeRecordDataHolder.getInstance().addAndReturnId(target);
	}
	
	public List<VipRechargeRecord> findPage(long phone,long minTime,long maxTime,int pageItemCount,int pageNo){
		return VipRechargeRecordDataHolder.getInstance().findPage(phone,minTime,maxTime,pageItemCount, pageNo);
	}
	
}
