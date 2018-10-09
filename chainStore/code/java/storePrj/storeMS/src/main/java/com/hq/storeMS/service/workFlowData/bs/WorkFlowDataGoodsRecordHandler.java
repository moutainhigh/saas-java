package com.hq.storeMS.service.workFlowData.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordAddListForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordUpdListForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataGoodsRecordHandler {

	public static WorkFlowDataGoodsRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataGoodsRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	public ReqResult<WorkFlowData> update(long workFlowDataId, String goodsId, GoodsRecordUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().updateGoodsRecord(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("更新商品信息失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataGoodsRecordHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, goodsId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> delete(long workFlowDataId, String goodsId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().deleteGoodsRecord(workFlowData, goodsId)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除商品失败");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataGoodsRecordHandler[delete]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, goodsId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> add(long workFlowDataId, GoodsRecordAddForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(WorkFlowDataBeanHelper.getInstance().addGoodsRecord(workFlowData, inputForm)){
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("商品不能重复选择");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataGoodsRecordHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> addList(long workFlowDataId, GoodsRecordAddListForm addListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			List<GoodsRecordAddForm> inputForms = addListForm.getGoodsRecordAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				for (GoodsRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addGoodsRecord(workFlowData, inputForm);
				}
				WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
				result.setTarget(workFlowData);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataGoodsRecordHandler[addList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, addListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> updList(long workFlowDataId, GoodsRecordUpdListForm updListForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			workFlowData.getGoodsRecordMap().clear();
			List<GoodsRecordAddForm> inputForms = updListForm.getGoodsRecordAddForms();
			if(CollectionUtils.isNotEmpty(inputForms)){
				for (GoodsRecordAddForm inputForm : inputForms) {
					WorkFlowDataBeanHelper.getInstance().addGoodsRecord(workFlowData, inputForm);
				}
			}
			WorkFlowDataMgr.getInstance().updateWorkFlowData(workFlowData);
			result.setTarget(workFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataGoodsRecordHandler[updList]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, updListForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
