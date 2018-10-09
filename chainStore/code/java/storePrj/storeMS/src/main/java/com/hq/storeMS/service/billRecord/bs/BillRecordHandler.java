package com.hq.storeMS.service.billRecord.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.billRecord.apiData.BillRecordAddForm;
import com.hq.storeMS.service.billRecord.apiData.BillRecordQueryForm;
import com.hq.storeMS.service.billRecord.data.BillRecord;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class BillRecordHandler {

	public static BillRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BillRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.BillRecord;
	
	public ReqResult<BillRecord> add(BillRecordAddForm addForm) {
		ReqResult<BillRecord> result = ReqResult.newInstance(false, BillRecord.class);
		try {
			OperateTips OperateTips = BillRecordMgr.getInstance().add(addForm);
			if(OperateTips.isSuccess()){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setTips(OperateTips.getTips());
			}
		} catch (Exception e) {
			final String logId = "BillRecordHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BillRecord> get(long storeId, long id) {
		ReqResult<BillRecord> result = ReqResult.newInstance(false, BillRecord.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			BillRecord target = BillRecordMgr.getInstance().get(storeId, id);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BillRecordHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BillRecord> findList(BillRecordQueryForm queryForm) {
		ReqResult<BillRecord> result = ReqResult.newInstance(false, BillRecord.class);
		try {
			List<BillRecord> list = BillRecordMgr.getInstance().findList(queryForm);
			result.setTargetList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BillRecordHandler[findList]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findPageInfo(BillRecordQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<BillRecord> pageInfo = BillRecordMgr.getInstance().findPageInfo(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BillRecordHandler[findPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
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
