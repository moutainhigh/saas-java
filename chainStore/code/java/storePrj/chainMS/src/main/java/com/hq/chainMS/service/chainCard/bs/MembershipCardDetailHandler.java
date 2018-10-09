package com.hq.chainMS.service.chainCard.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetail;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailHandler {

	public static MembershipCardDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailHandler.class);
	}

	private final LogModule logModule = LogModule.MembershipCardDetail;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getMembershipCardDetailPageInfo(MembershipCardDetailQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<MembershipCardDetail> pageResp = MembershipCardDetailMgr.getInstance().getMembershipCardDetailPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "MembershipCardDetailHandler[getMembershipCardDetailPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<MembershipCardDetail> getMembershipCardDetail(long chainId, String id) {
		ReqResult<MembershipCardDetail> result = ReqResult.newInstance(false, MembershipCardDetail.class);
		try {
			MembershipCardDetail membershipCardDetail = MembershipCardDetailMgr.getInstance().get(chainId, id);
			result.setTarget(membershipCardDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "MembershipCardDetailHandler[getMembershipCardDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
