package com.hq.storeMS.service.membershipCardDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
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

	public ReqResult<MembershipCardDetail> getStoreMembershipCardDetail(long storeId, String id) {
		ReqResult<MembershipCardDetail> result = ReqResult.newInstance(false, MembershipCardDetail.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			MembershipCardDetail membershipCardDetail = MembershipCardDetailMgr.getInstance().get(storeId, id);
			result.setTarget(membershipCardDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "MembershipCardDetailHandler[getStoreMembershipCardDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
