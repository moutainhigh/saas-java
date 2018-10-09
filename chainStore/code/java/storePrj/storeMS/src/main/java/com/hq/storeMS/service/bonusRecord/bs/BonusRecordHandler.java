package com.hq.storeMS.service.bonusRecord.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordForm;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordListForm;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.bonusRecord.data.BonusRecordGroup;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusRecordHandler {

	public static BonusRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.BonusRecord;
	
	public ReqResult<BonusRecord> saveBonusRecord(BonusRecordForm addForm) {
		ReqResult<BonusRecord> result = ReqResult.newInstance(false, BonusRecord.class);
		try {
			Order order = OrderMgr.getInstance().get(addForm.getStoreId(), addForm.getOrderId());
			BonusRecordMgr.getInstance().saveBonusRecord(addForm, order);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[addBonusRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BonusRecord> saveList(BonusRecordListForm forms) {
		ReqResult<BonusRecord> result = ReqResult.newInstance(false, BonusRecord.class);
		try {
			long storeId = forms.getStoreId();
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Order order = OrderMgr.getInstance().get(storeId, forms.getOrderId());
			List<BonusRecordForm> bonusRecordForms = forms.getBonusRecordForms();
			if(CollectionUtils.isNotEmpty(bonusRecordForms)){
				for (BonusRecordForm bonusRecordForm : bonusRecordForms) {
					BonusRecordMgr.getInstance().saveBonusRecord(bonusRecordForm, order);
				}
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[saveList]";
			final String reason = LogHelper.getInstance().exceptionReason(forms);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BonusRecord> deleteBonusRecord(long storeId, long bonusRecordId) {
		ReqResult<BonusRecord> result = ReqResult.newInstance(false, BonusRecord.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			BonusRecord target = BonusRecordMgr.getInstance().get(storeId, bonusRecordId);
			BonusRecordMgr.getInstance().delete(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[deleteBonusRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(bonusRecordId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BonusRecord> getBonusRecord(long storeId, long bonusRecordId) {
		ReqResult<BonusRecord> result = ReqResult.newInstance(false, BonusRecord.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			BonusRecord target = BonusRecordMgr.getInstance().get(storeId, bonusRecordId);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[getBonusRecord]";
			final String reason = LogHelper.getInstance().exceptionReason(bonusRecordId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BonusRecord> findBonusRecordList(BonusRecordQueryForm queryForm) {
		ReqResult<BonusRecord> result = ReqResult.newInstance(false, BonusRecord.class);
		try {
			List<BonusRecord> list = BonusRecordMgr.getInstance().findBonusRecordList(queryForm);
			result.setTargetList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[findBonusRecordList]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findBonusRecordPageInfo(BonusRecordQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<BonusRecord> pageInfo = BonusRecordMgr.getInstance().findBonusRecordPageInfo(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[findBonusRecordPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findBonusRecordGroupPageInfo(BonusRecordQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<BonusRecordGroup> pageInfo = BonusRecordMgr.getInstance().getBonusRecordGroupPageInfo(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BonusRecordHandler[findBonusRecordGroupPageInfo]";
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
