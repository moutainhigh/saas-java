package com.hq.storeMS.service.materialRecords.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.materialRecords.apiData.MaterialRecordsAddApiData;
import com.hq.storeMS.service.materialRecords.apiData.MaterialRecordsAddApiForm;
import com.hq.storeMS.service.materialRecords.data.MaterialRecords;
import com.hq.storeMS.service.storeMaterialInfo.bs.StoreMaterialInfoMgr;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class MaterialRecordsHandler {

	public static MaterialRecordsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MaterialRecordsHandler.class);
	}
	
	public ReqResult<MaterialRecords> findByStoreId(long storeId,long maxTime,long minTime,int pageItemCount,int pageNo) {
		ReqResult<MaterialRecords> result = ReqResult.newInstance(false, MaterialRecords.class);
		try {
			List<MaterialRecords> allList = MaterialRecordsMgr.getInstance().findByStoreId(storeId,maxTime,minTime,pageItemCount, pageNo);
			result.setTargetList(allList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, maxTime, minTime, pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MaterialRecords, "MaterialRecordsHandler[findByStoreId]", reason, e);
		}
		return result;
	}
	
	public ReqResult<MaterialRecords> findByMaterialId(String materialId,int pageItemCount,int pageNo) {
		ReqResult<MaterialRecords> result = ReqResult.newInstance(false, MaterialRecords.class);
		try {
			if(StringUtils.isNoneBlank(materialId)){
				List<MaterialRecords> allList = MaterialRecordsMgr.getInstance().findByMaterialId(materialId,pageItemCount, pageNo);
				result.setTargetList(allList);
				result.setSuccess(true);
			}else{
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("materialId为空");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(materialId, pageItemCount, pageNo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MaterialRecords, "MaterialRecordsHandler[findByMaterialId]", reason, e);
		}
		return result;
	}
	
	public ReqResult<MaterialRecords> get(long id) {
		ReqResult<MaterialRecords> result = ReqResult.newInstance(false, MaterialRecords.class);
		try {
			MaterialRecords material = MaterialRecordsMgr.getInstance().get(id);
			if(material!=null){
				result.setTarget(material);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("耗材不存在");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MaterialRecords, "MaterialRecordsHandler[get]", reason, e);
		}

		return result;
	}
	
	public ReqResult<MaterialRecords> add(MaterialRecordsAddApiForm formInfo) {
		ReqResult<MaterialRecords> result = ReqResult.newInstance(false, MaterialRecords.class);
		try {
			List<MaterialRecords> materialRecordsList = new ArrayList<MaterialRecords>();
			//遍历列表添加进货记录
			List<MaterialRecordsAddApiData> materialRecordsAddList = formInfo.getMaterialRecordsAddList();
			long createdTime = System.currentTimeMillis();
			for (MaterialRecordsAddApiData materialRecordsAddApiData : materialRecordsAddList) {
				if(materialRecordsAddApiData.getCount() > 0){
					MaterialRecords materialRecords = materialRecordsAddApiData.toMaterialRecords(createdTime);
					//添加进货记录
					MaterialRecordsMgr.getInstance().addAndReturnId(materialRecords);
					//更新耗材库存
					StoreMaterialInfoMgr.getInstance().addMaterialInventory(materialRecords.getStoreId(), materialRecords.getMaterialId(), materialRecords.getCount());
					
					materialRecordsList.add(materialRecords);
				}
			}
			result.setTargetList(materialRecordsList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.MaterialRecords, "MaterialRecordsHandler[add]", reason, e);
		}
		return result;
	}

}
