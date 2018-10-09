package com.hq.storeMS.service.leaguerDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.notice.LeaguerNoticeMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.MemberCardExist;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailHandler {

	public static LeaguerDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailHandler.class);
	}

	private final LogModule logModule = LogModule.LeaguerDetail;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getLeaguerDetailPageInfo(LeaguerDetailQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<LeaguerDetail> pageResp = LeaguerDetailMgr.getInstance().getLeaguerDetailPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LeaguerDetailHandler[getLeaguerDetailPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<LeaguerDetail> getStoreLeaguerDetail(long storeId, String id) {
		ReqResult<LeaguerDetail> result = ReqResult.newInstance(false, LeaguerDetail.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().get(storeId, id);
			result.setTarget(leaguerDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LeaguerDetailHandler[getLeaguerDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<MemberCardExist> checkMemberCardExist(String memberCardNumber, long storeId) {
		ReqResult<MemberCardExist> result = ReqResult.newInstance(false, MemberCardExist.class);
		try {
			MemberCardExist existResult = LeaguerDetailMgr.getInstance().checkMemberCardExist(memberCardNumber, storeId);
			result.setTarget(existResult);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LeaguerDetailHandler[getLeaguerDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(memberCardNumber, storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<LeaguerDetail> triggerNotice(long storeId) {
		ReqResult<LeaguerDetail> result = ReqResult.newInstance(false, LeaguerDetail.class);
		try {
			LeaguerNoticeMgr.getInstance().singleStoreNotice(storeId);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LeaguerDetailHandler[triggerNotice]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
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
