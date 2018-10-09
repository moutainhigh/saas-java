package com.hq.storeMS.service.storeVipType.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeVipType.apiData.AddStoreVipTypeForm;
import com.hq.storeMS.service.storeVipType.apiData.UpdateStoreVipTypeForm;
import com.hq.storeMS.service.storeVipType.data.StoreVipType;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreVipTypeHandler {
	
	public static StoreVipTypeHandler getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipTypeHandler.class);
	}

	private final LogModule logModule = LogModule.StoreVipType;

	public ReqResult<StoreVipType> get(long id) {
		ReqResult<StoreVipType> result = ReqResult.newInstance(false, StoreVipType.class);
		try {
			StoreVipType storeVipType = StoreVipTypeMgr.getInstance().get(id);
			result.setTarget(storeVipType);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreVipTypeHandler[get]", reason, e);
		}
		return result;
	}
	
	public ReqResult<StoreVipType> findPage(int pageItemCount,int pageNo) {
		ReqResult<StoreVipType> result = ReqResult.newInstance(false, StoreVipType.class);
		try {
			List<StoreVipType> storeVipTypeList = StoreVipTypeMgr.getInstance().findPage(pageItemCount, pageNo);
			result.setTargetList(storeVipTypeList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "StoreVipTypeHandler[findPage]", reason, e);
		}
		return result;
	}
	
	/**
	 * 查询会员类型信息
	 * @param vipName
	 * @return
	 */
	public ReqResult<StoreVipType> findVipByName(String vipName) {
		ReqResult<StoreVipType> result = ReqResult.newInstance(false, StoreVipType.class);
		try {
			StoreVipType findByName = StoreVipTypeMgr.getInstance().findByName(vipName);
			if(findByName != null){
				result.setTarget(findByName);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(vipName);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "StoreVipTypeHandler[findVipByName]", reason, e);
		}
		return result;
	}
	
	/**
	 * 添加会员类型
	 * @param AddStoreVipTypeForm
	 * @return
	 */
	public ReqResult<StoreVipType> addStoreVipType(AddStoreVipTypeForm addForm) {
		ReqResult<StoreVipType> result = ReqResult.newInstance(false, StoreVipType.class);
		try {
			StoreVipType storeVipType = addForm.toStoreVipType();
			StoreVipTypeMgr.getInstance().add(storeVipType);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "StoreVipTypeHandler[addStoreVipType]", reason, e);
		}
		return result;
	}
	
	/**
	 * 修改会员类型
	 * @param UpdateStoreVipTypeForm
	 * @return
	 */
	public ReqResult<StoreVipType> updateStoreVipTypeInfo(UpdateStoreVipTypeForm updateForm) {
		ReqResult<StoreVipType> result = ReqResult.newInstance(false, StoreVipType.class);
		try {
			StoreVipType storeVipType = StoreVipTypeMgr.getInstance().get(updateForm.getId());
			storeVipType = updateForm.toStoreVipType(storeVipType);
			StoreVipTypeMgr.getInstance().update(storeVipType);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, "StoreVipTypeHandler[updateStoreVipTypeInfo]", reason, e);
		}
		return result;
	}
	
}
