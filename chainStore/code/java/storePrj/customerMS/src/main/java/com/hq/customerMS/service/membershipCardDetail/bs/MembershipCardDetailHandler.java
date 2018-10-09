package com.hq.customerMS.service.membershipCardDetail.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailHandler {

	public static MembershipCardDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailHandler.class);
	}

	private final LogModule logModule = LogModule.MembershipCardDetail;

	public ReqResult<MembershipCardDetail> getMembershipCardDetail(long storeId, String id) {
		ReqResult<MembershipCardDetail> result = ReqResult.newInstance(false, MembershipCardDetail.class);
		try {
			MembershipCardDetail membershipCardDetail = MembershipCardDetailMgr.getInstance().get(storeId, id);
			result.setTarget(membershipCardDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "MembershipCardDetailHandler[getMembershipCardDetail]", reason, e);
		}
		return result;
	}
}
