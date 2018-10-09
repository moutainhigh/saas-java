package com.hq.customerMS.service.buser.bs;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserHandler {

	public static BUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BUserHandler.class);
	}
	
	public ReqResult<BUser> findByMultitId(Set<Long> buserIds) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			if(CollectionUtils.isEmpty(buserIds)) {
				result.setTargetList(new ArrayList<BUser>());
				result.setSuccess(true);
				return result;
			}
			BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
			queryForm.setBuserIds(buserIds);
			PageResp<BUser> page = BUserMgr.getInstance().findByCond(queryForm);
			result.setTargetList(page.getList());
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(buserIds);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findByMultitId]", reason, e);
		}
		return result;
	}

	public ReqResult<BUser> get(long buserId) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			BUser buser = BUserMgr.getInstance().get(buserId);
			result.setTarget(buser);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(buserId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[get]", reason, e);
		}
		return result;
	}
}
