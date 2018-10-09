package com.hq.storeMS.service.leaguerRecord.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordAddForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordApiForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordUpdateType;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerRecordHandler {

	public static LeaguerRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.LeaguerRecord;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getLeaguerRecordPageInfo(LeaguerRecordQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<LeaguerRecord> pageResp = LeaguerRecordMgr.getInstance().getLeaguerRecordPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.LeaguerRecord, "LeaguerRecordHandler[getLeaguerRecordPageInfo]", reason, e);
		}
		return result;
	}
	
	public ReqResult<LeaguerRecord> getLeaguerRecord(long storeId, long id) {
		ReqResult<LeaguerRecord> result = ReqResult.newInstance(false, LeaguerRecord.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			LeaguerRecord leaguerRecord = LeaguerRecordMgr.getInstance().get(storeId, id);
			result.setTarget(leaguerRecord);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.LeaguerRecord, "LeaguerRecordHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<LeaguerRecord> updateLeaguerRecord(long leaguerRecordId, LeaguerRecordApiForm inputForm) {
		ReqResult<LeaguerRecord> result = ReqResult.newInstance(false, LeaguerRecord.class);
		try {
			long storeId = inputForm.getStoreId();
			if(storeId == 0) {
				storeId = getStoreId();
			}
			
			LeaguerRecordUpdateType updateType = inputForm.getLeaguerRecordUpdateType();
			LeaguerRecord leaguerRecord = LeaguerRecordMgr.getInstance().get(storeId, leaguerRecordId);
			
			if(leaguerRecord == null){
				result.setTips("更新的记录不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			
			switch (updateType) {
			case UpdateInfo:
				inputForm.getLeaguerRecordUpdateForm().updateLeaguerRecord(leaguerRecord);
				LeaguerRecordMgr.getInstance().update(leaguerRecord);
				result.setTarget(leaguerRecord);
				result.setSuccess(true);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			final String logId = "LeaguerRecordHandler[updateLeaguerRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(leaguerRecordId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		
		return result;
	}
	
	public ReqResult<LeaguerRecord> deleteLeaguerRecord(long storeId, long leaguerRecordId) {
		ReqResult<LeaguerRecord> result = ReqResult.newInstance(false, LeaguerRecord.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			LeaguerRecord leaguerRecord = LeaguerRecordMgr.getInstance().get(storeId, leaguerRecordId);
			if(leaguerRecord != null){
				LeaguerRecordMgr.getInstance().delete(leaguerRecord);
				result.setSuccess(true);
			}else{
				result.setTips("删除的记录不存在");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "LeaguerRecordHandler[deleteLeaguerRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, leaguerRecordId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<LeaguerRecord> addLeaguerRecord(LeaguerRecordAddForm inputForm) {
		ReqResult<LeaguerRecord> result = ReqResult.newInstance(false, LeaguerRecord.class);
		try {
			if(StringUtils.isBlank(inputForm.getLeaguerId())) {
				result.setTips("客户Id为空");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			//兼容2.0.0之前的版本
			if(inputForm.getStoreId() == 0) {
				inputForm.setStoreId(getStoreId());
			}
			
			BUser buser = BUserAuthUtils.getInstance().getSessionBUser();
			LeaguerRecord leaguerRecord = inputForm.toLeaguerRecord();
			leaguerRecord.setCreatorId(buser.getId());
			leaguerRecord.setCreatorName(buser.getName());
			LeaguerRecordMgr.getInstance().addAndReturnId(leaguerRecord);
			result.setTarget(leaguerRecord);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "LeaguerRecordHandler[addLeaguerRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
