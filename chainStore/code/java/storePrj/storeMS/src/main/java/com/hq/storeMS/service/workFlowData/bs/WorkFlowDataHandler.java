package com.hq.storeMS.service.workFlowData.bs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.appointment.apiData.AppointmentUpdateStatusApiForm;
import com.hq.storeMS.service.appointment.bs.AppointmentMgr;
import com.hq.storeMS.service.appointment.data.AppointProduct;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.appointment.data.AppointmentStatusEnum;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItemEnum;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerCardEnum;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataAddByAppoint;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataAddByLeaguer;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataAddForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataSwitchLeaguer;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateForm;
import com.hq.storeMS.service.workFlowData.bs.updateHandle.WorkFlowDataHandleHelper;
import com.hq.storeMS.service.workFlowData.data.AppointInfo;
import com.hq.storeMS.service.workFlowData.data.DelimitCardRecord;
import com.hq.storeMS.service.workFlowData.data.LeaguerInfo;
import com.hq.storeMS.service.workFlowData.data.ProdRecord;
import com.hq.storeMS.service.workFlowData.data.RecordTypeEnum;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowType.bs.WorkFlowTypeMgr;
import com.hq.storeMS.service.workFlowType.data.SysFixCompEnum;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataHandler {

	public static WorkFlowDataHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataHandler.class);
	}
	
	private final LogModule logModule = LogModule.WorkFlowData;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findWorkFlowDataPageInfo(WorkFlowDataQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			WorkFlowType typeRO = WorkFlowTypeMgr.getInstance().findOne(SysFixCompEnum.BuyComp.getMark());
			//默认只查询购买消费的流程
			if(queryForm.getWorkFlowTypeId() == 0) {
				queryForm.setWorkFlowTypeId(typeRO.getId());
			}
			
			PageResp<WorkFlowData> target = WorkFlowDataMgr.getInstance().findWorkFlowDataPageInfo(queryForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[findWorkFlowDataPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> findByCond(WorkFlowDataQueryForm queryForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowType typeRO = WorkFlowTypeMgr.getInstance().findOne(SysFixCompEnum.BuyComp.getMark());
			//默认只查询购买消费的流程
			if(queryForm.getWorkFlowTypeId() == 0) {
				queryForm.setWorkFlowTypeId(typeRO.getId());
			}
			List<WorkFlowData> WorkFlowDatas = WorkFlowDataMgr.getInstance().findByCond(queryForm);
			result.setTargetList(WorkFlowDatas);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[findByCond]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> getWorkFlowData(long storeId, long workFlowDataId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData WorkFlowData = WorkFlowDataMgr.getInstance().get(storeId, workFlowDataId);
			result.setTarget(WorkFlowData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[getWorkFlowData]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, workFlowDataId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> deleteWorkFlowData(long workFlowDataId) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData target = WorkFlowDataMgr.getInstance().get(workFlowDataId);
			if(target != null){
				WorkFlowDataMgr.getInstance().delete(target);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("删除的数据不存在");
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[deleteWorkFlowData]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> updateWorkFlowData(long workFlowDataId, WorkFlowDataUpdateForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			OperateTips operateTips = WorkFlowDataHandleHelper.getInstance().update(workFlowDataId, inputForm);
			if(operateTips.isSuccess()){
				WorkFlowData target = WorkFlowDataMgr.getInstance().get(workFlowDataId);
				result.setTarget(target);
				result.setSuccess(true);
			}else{
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[updateWorkFlowData]";
			final String reason = LogHelper.getInstance().exceptionReason(workFlowDataId, inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<WorkFlowData> addWorkFlowData(WorkFlowDataAddForm inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			if(!checkWorkFlowTypeId(inputForm.getWorkFlowTypeId())){
				result.setTips("工作流类型Id为空");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			
			WorkFlowData target = WorkFlowData.newInstance();
			FastBeanCopyer.getInstance().copy(inputForm, target);
			WorkFlowDataMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[addWorkFlowData]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private boolean checkWorkFlowTypeId(long typeId){
		return typeId > 0;
	}
	
	public ReqResult<WorkFlowData> addByAppoint(WorkFlowDataAddByAppoint inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			long storeId = inputForm.getStoreId();
			if(storeId==0) {
				storeId = getStoreId();
			}
			WorkFlowType workFlowType = WorkFlowTypeMgr.getInstance().findOne(SysFixCompEnum.BuyComp.getMark());
			Appointment appointment = AppointmentMgr.getInstance().get(storeId, inputForm.getAppointmentId());
			StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
			Map<String, ProductInfo> productInfoMap = storeProductInfo.getProductInfoMap();
			List<AppointProduct> appointProducts = appointment.getAppointProducts();
			LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().get(storeId, appointment.getLeaguerId());
			StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
			Map<String, MembershipCard> membershipCardMap = storeCardInfo.getMembershipCardMap();
			
			WorkFlowData target = WorkFlowData.newInstance();
			//工作流数据基本信息
			target.setWorkFlowTypeId(workFlowType.getId());
			target.setBuserId(inputForm.getCreatorId());
			target.setStoreId(storeId);
			target.setAppointInfo(AppointInfo.newInstance(inputForm.getAppointmentId()));
			//客户信息
			LeaguerInfo leaguerInfo = LeaguerInfo.newInstance();
			leaguerInfo.setLeaguerId(appointment.getLeaguerId());
			target.setLeaguerInfo(leaguerInfo);
			//项目与划卡信息
			Map<String, ProdRecord> prodRecordMap = new HashMap<String, ProdRecord>();
			Map<String, DelimitCardRecord> delimitCardRecordMap = new HashMap<String, DelimitCardRecord>();
			
			for (AppointProduct product : appointProducts) {
				if(StringUtils.isNoneBlank(product.getProductCardId()) || StringUtils.isNoneBlank(product.getLeaguerPrdCardId())){//次卡项目
					String leaguerPrdCardId = StringUtils.isNoneBlank(product.getLeaguerPrdCardId()) ? product.getLeaguerPrdCardId() : product.getProductCardId();
					DelimitCardRecord record = DelimitCardRecord.newInstance(leaguerPrdCardId, product.getProductId(), ProductCardItemEnum.PRODUCT.ordinal());
					record.setCount((int)product.getProductCount());
					delimitCardRecordMap.put(record.getDelimitCardId(), record);
				}else{//普通项目
					float discount = ServerConstants.DISCOUNT_NUM * 1.0f;
					MembershipCard membershipCard = null;
					if(leaguer != null && leaguer.getLeaguerMemberCard() != null && leaguer.getLeaguerMemberCard().getState() == LeaguerCardEnum.VALID.ordinal()){
						membershipCard = membershipCardMap.get(leaguer.getLeaguerMemberCard().getCardId());
					}
					if(membershipCard != null){
						discount = membershipCard.getProdDiscount();
					}
					ProdRecord prodRecord = ProdRecord.newInstance();
					prodRecord.setId(AppUtils.joinByUnderline(product.getProductId(), RecordTypeEnum.Buy.ordinal()));
					prodRecord.setProdId(String.valueOf(product.getProductId()));
					prodRecord.setCount((int)product.getProductCount());
					ProductInfo productInfo = productInfoMap.get(product.getProductId()+"");
					float oldPrice = productInfo != null ? productInfo.getPrice() : 0f;
					prodRecord.setPrice(BigDecimalUtil.round(oldPrice * discount / ServerConstants.DISCOUNT_NUM, 2));
					prodRecord.setDiscount(discount);
					prodRecord.setOldPrice(oldPrice);
					prodRecord.setCost(BigDecimalUtil.round(oldPrice*prodRecord.getCount(), 2));
					prodRecord.setPay(BigDecimalUtil.round(prodRecord.getPrice()*prodRecord.getCount(), 2));
					prodRecordMap.put(prodRecord.getId(), prodRecord);
				}
			}
			
			target.setProdRecordMap(prodRecordMap);
			target.setDelimitCardRecordMap(delimitCardRecordMap);
			
			WorkFlowDataMgr.getInstance().addAndReturnId(target);
			updateAppointmentStatus(storeId, inputForm.getAppointmentId());
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[addByAppoint]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	//更新预约的状态
	private OperateTips updateAppointmentStatus(long storeId, long appointmentId){
		AppointmentUpdateStatusApiForm updateStatusData = AppointmentUpdateStatusApiForm.newInstance();
		updateStatusData.setStatus(AppointmentStatusEnum.SUCCESS.ordinal());
		return AppointmentMgr.getInstance().updateState(storeId, appointmentId, updateStatusData);
	}
	
	public ReqResult<WorkFlowData> addByLeaguer(WorkFlowDataAddByLeaguer inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			if(!checkWorkFlowTypeId(inputForm.getWorkFlowTypeId())){
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("工作流类型Id为空");
				return result;
			}
			
			WorkFlowData target = WorkFlowData.newInstance();
			//工作流数据基本信息
			target.setWorkFlowTypeId(inputForm.getWorkFlowTypeId());
			target.setBuserId(inputForm.getCreatorId());
			target.setStoreId(inputForm.getStoreId());
			//客户信息
			LeaguerInfo leaguerInfo = LeaguerInfo.newInstance();
			leaguerInfo.setLeaguerId(inputForm.getLeaguerId());
			target.setLeaguerInfo(leaguerInfo);
			
			WorkFlowDataMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[addByLeaguer]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<WorkFlowData> switchLeaguer(WorkFlowDataSwitchLeaguer inputForm) {
		ReqResult<WorkFlowData> result = ReqResult.newInstance(false, WorkFlowData.class);
		try {
			WorkFlowData data = WorkFlowDataMgr.getInstance().get(inputForm.getWorkFlowDataId());
			if(data == null || data.getLeaguerInfo() == null) {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("切换客户失败");
				return result;
			}
			
			Leaguer leaguer = StoreLeaguerInfoMgr.getInstance().get(data.getStoreId()).takeLeaguerById(inputForm.getLeaguerId());
			data.getLeaguerInfo().setLeaguerId(inputForm.getLeaguerId());
			data.getLeaguerInfo().setLeaguerName(leaguer.getName());
			
			data.getBonusInfoMap().clear();
			data.getGoodsRecordMap().clear();
			data.getProdRecordMap().clear();
			
			data.setMemCardInfo(null);
			data.setOrderInfo(null);
			
			WorkFlowDataMgr.getInstance().updateWorkFlowData(data);
			result.setTarget(data);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "WorkFlowDataHandler[switchLeaguer]";
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
