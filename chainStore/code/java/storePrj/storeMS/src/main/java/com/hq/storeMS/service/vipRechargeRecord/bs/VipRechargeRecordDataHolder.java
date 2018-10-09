package com.hq.storeMS.service.vipRechargeRecord.bs;

import java.util.List;

import com.hq.storeMS.service.vipRechargeRecord.data.VipRechargeRecord;
import com.hq.storeMS.service.vipRechargeRecord.data.VipRechargeRecordDAO;
import com.hq.storeMS.service.vipRechargeRecord.data.VipRechargeRecordRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class VipRechargeRecordDataHolder {
	
	public static VipRechargeRecordDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(VipRechargeRecordDataHolder.class);
	}
	
	public void addAndReturnId(VipRechargeRecord target) {
		VipRechargeRecordDAO.getInstance().addAndReturnId(target);
	}

	public VipRechargeRecord get(long id) {
		VipRechargeRecord target = VipRechargeRecordRedisDAO.getInstance().get(id);
		if(target == null){
			target = VipRechargeRecordDAO.getInstance().get(id);
			if(target != null){
				VipRechargeRecordRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<VipRechargeRecord> findPage(long phone,long minTime,long maxTime,int pageItemCount, int pageNo){
		return VipRechargeRecordDAO.getInstance().findPage(phone, minTime, maxTime, pageItemCount, pageNo);
	}

}
