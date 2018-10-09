package com.hq.storeMS.service.vipRechargeRecord.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.vipRechargeRecord.data.VipRechargeRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class VipRechargeRecordHandler {
	
	public static VipRechargeRecordHandler getInstance(){
		return HotSwap.getInstance().getSingleton(VipRechargeRecordHandler.class);
	}

	private final LogModule logModule = LogModule.VipRechargeRecord;
//	private final String reason = "Exception happens.";

	public ReqResult<VipRechargeRecord> get(long id) {
		ReqResult<VipRechargeRecord> result = ReqResult.newInstance(false, VipRechargeRecord.class);
		try {
			VipRechargeRecord storeVipType = VipRechargeRecordMgr.getInstance().get(id);
			result.setTarget(storeVipType);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "VipRechargeRecordHandler[get]", reason, e);
		}
		return result;
	}
	
	public ReqResult<VipRechargeRecord> findPage(long phone,long minTime,long maxTime,int pageItemCount,int pageNo) {
		ReqResult<VipRechargeRecord> result = ReqResult.newInstance(false, VipRechargeRecord.class);
		try {
			List<VipRechargeRecord> storeVipTypeList = VipRechargeRecordMgr.getInstance().findPage(phone,minTime,maxTime,pageItemCount, pageNo);
			result.setTargetList(storeVipTypeList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(phone, minTime, maxTime, pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "VipRechargeRecordHandler[findPage]", reason, e);
		}
		return result;
	}
	
}
