package com.hq.payms.service.bossPayInfo.bs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoQueryForm;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.BossPayInfoCount;
import com.hq.payms.service.common.ExceptionInfo;
import com.hq.payms.service.common.HandlerHelper;
import com.hq.payms.service.common.OperateTips;
import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RespStatus;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class BossPayInfoHandler {
	
	private final LogModule logModule = LogModule.BossPayInfo;
	private final String reason = "Exception happens.";
	
	public static BossPayInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BossPayInfoHandler.class);
	}
	
	public ReqResult<BossPayInfo> get(long Id) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			BossPayInfo mtype = BossPayInfoMgr.getInstance().get(Id);
			if (mtype != null) {
				result.setTarget(mtype);
				result.setSuccess(true);
			} else {
				result.setTips("获取详情失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "BossPayInfoHandler[get]", "", e);
		}
		return result;
	}
	
	
	public ReqResult<BossPayInfo> add(BossPayInfoAddApiForm addForm) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		OperateTips operateTips = OperateTips.newInstance(false, "添加失败");
		try {
			operateTips = checkStoreId(addForm.getStoreId());
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
//			operateTips = checkWxpayAppId(addForm.getWxpayAppId());
//			if(!operateTips.isSuccess()){
//				HandlerHelper.getInstance().handleReqResult(result, operateTips);
//				return result;
//			}
//			operateTips = checkWxpayCertPath(addForm.getWxpayCertPath());
//			if(!operateTips.isSuccess()){
//				HandlerHelper.getInstance().handleReqResult(result, operateTips);
//				return result;
//			}
//			operateTips = checkAlipayAppId(addForm.getAlipayAppId());
//			if(!operateTips.isSuccess()){
//				HandlerHelper.getInstance().handleReqResult(result, operateTips);
//				return result;
//			}
			BossPayInfo target = addForm.toBossPayInfo();
			BossPayInfoMgr.getInstance().add(target);
			result.setTarget(target);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "BossPayInfoHandler[add]", "", e);
		}
		return result;
	}
	
	/**
	 * storeId唯一性校验
	 * @param storeId
	 * @return
	 */
	private OperateTips checkStoreId(long storeId) {
		OperateTips tips = OperateTips.newInstance();
		if(storeId <= 0) {
			return tips;
		}
		BossPayInfoQueryForm params = BossPayInfoQueryForm.newInstance();
		params.setStoreId(storeId);
		BossPayInfoCount countObj = BossPayInfoMgr.getInstance().getCount4ExactQuery(params);
		if(countObj.getCount() > 0){
			tips.setSuccess(false);
			tips.setTips("storeId已存在");
		}
		return tips;
	}
	
	/**
	 * wxpayAppId唯一性校验
	 * @param wxpayAppId
	 * @return
	 */
	private OperateTips checkWxpayAppId(String wxpayAppId) {
		OperateTips tips = OperateTips.newInstance();
		if(StringUtils.isBlank(wxpayAppId)) {
			return tips;
		}
		BossPayInfoQueryForm params = BossPayInfoQueryForm.newInstance();
		params.setWxpayAppId(wxpayAppId);
		BossPayInfoCount countObj = BossPayInfoMgr.getInstance().getCount4ExactQuery(params);
		if(countObj.getCount() > 0){
			tips.setSuccess(false);
			tips.setTips("wxpayAppId已存在");
		}
		return tips;
	}
	
	/**
	 * wxpayCertPath唯一性校验
	 * @param wxpayCertPath
	 * @return
	 */
	private OperateTips checkWxpayCertPath(String wxpayCertPath) {
		OperateTips tips = OperateTips.newInstance();
		if(StringUtils.isBlank(wxpayCertPath)) {
			return tips;
		}
		BossPayInfoQueryForm params = BossPayInfoQueryForm.newInstance();
		params.setWxpayCertPath(wxpayCertPath);
		BossPayInfoCount countObj = BossPayInfoMgr.getInstance().getCount4ExactQuery(params);
		if(countObj.getCount() > 0){
			tips.setSuccess(false);
			tips.setTips("wxpayCertPath已存在");
		}
		return tips;
	}
	
	/**
	 * alipayAppId唯一性校验
	 * @param alipayAppId
	 * @return
	 */
	private OperateTips checkAlipayAppId(String alipayAppId) {
		OperateTips tips = OperateTips.newInstance();
		if(StringUtils.isBlank(alipayAppId)) {
			return tips;
		}
		BossPayInfoQueryForm params = BossPayInfoQueryForm.newInstance();
		params.setAlipayAppId(alipayAppId);
		BossPayInfoCount countObj = BossPayInfoMgr.getInstance().getCount4ExactQuery(params);
		if(countObj.getCount() > 0){
			tips.setSuccess(false);
			tips.setTips("alipayAppId已存在");
		}
		return tips;
	}
	
	public ReqResult<BossPayInfo> update(long id, BossPayInfoAddApiForm updateForm) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			OperateTips operateTips = OperateTips.newInstance(false, "更新失败");
			BossPayInfo target = BossPayInfoMgr.getInstance().get(id);
			if(target != null){
				//校验唯一性（与自身相同的除外）
				if( target.getStoreId() != updateForm.getStoreId() ){ 
					operateTips = checkStoreId(updateForm.getStoreId());
					if(!operateTips.isSuccess()){
						HandlerHelper.getInstance().handleReqResult(result, operateTips);
						return result;
					}
				}
//				if( StringUtils.isNotBlank(target.getWxpayAppId()) 
//						&& !target.getWxpayAppId().equals(updateForm.getWxpayAppId()) ){ 
//					operateTips = checkWxpayAppId(updateForm.getWxpayAppId());
//					if(!operateTips.isSuccess()){
//						HandlerHelper.getInstance().handleReqResult(result, operateTips);
//						return result;
//					}
//				}
//				if( StringUtils.isNotBlank(target.getWxpayCertPath()) 
//						&& !target.getWxpayCertPath().equals(updateForm.getWxpayCertPath()) ){ 
//					operateTips = checkWxpayCertPath(updateForm.getWxpayCertPath());
//					if(!operateTips.isSuccess()){
//						HandlerHelper.getInstance().handleReqResult(result, operateTips);
//						return result;
//					}
//				}
//				if( StringUtils.isNotBlank(target.getAlipayAppId())
//						&& !target.getAlipayAppId().equals(updateForm.getAlipayAppId()) ){ 
//					operateTips = checkAlipayAppId(updateForm.getAlipayAppId());
//					if(!operateTips.isSuccess()){
//						HandlerHelper.getInstance().handleReqResult(result, operateTips);
//						return result;
//					}
//				}
				FastBeanCopyer.getInstance().copy(updateForm, target);
				BossPayInfoMgr.getInstance().update(target);
				result.setTarget(target);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				operateTips.setTips("BossPayInfo不存在，更新失败");
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "BossPayInfoHandler[updateBossPayInfo]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
				.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BossPayInfo> findList(BossPayInfoQueryForm params) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			List<BossPayInfo> mtype = BossPayInfoMgr.getInstance().findList(params);
			result.setTargetList(mtype);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "BossPayInfoHandler[findList]", "", e);
		}
		return result;
	}
	
	public ReqResult<BossPayInfoCount> getCount(BossPayInfoQueryForm params) {
		ReqResult<BossPayInfoCount> result = ReqResult.newInstance(false, BossPayInfoCount.class);
		try {
			BossPayInfoCount mtypeCount = BossPayInfoMgr.getInstance().getCount(params);
			result.setTarget(mtypeCount);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "BossPayInfoHandler[countMtype]", "", e);
		}
		return result;
	}

	public ReqResult<BossPayInfo> findByStoreId(long storeId) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			BossPayInfo target = BossPayInfoMgr.getInstance().findByStoreId(storeId);
			if(target!=null){
				result.setTarget(target);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setTips("BossPayInfo不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "BossPayInfoHandler[findByClientId]", "", e);
		}
		return result;
	}
	
	
}
