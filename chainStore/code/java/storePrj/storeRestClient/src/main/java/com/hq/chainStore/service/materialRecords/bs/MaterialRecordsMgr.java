package com.hq.chainStore.service.materialRecords.bs;

import java.util.List;

import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsAddApiForm;
import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsQueryParam;
import com.hq.chainStore.service.materialRecords.data.MaterialRecords;
import com.hq.chainStore.service.materialRecords.data.MaterialRecordsDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class MaterialRecordsMgr {

	public static MaterialRecordsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MaterialRecordsMgr.class);
	}

	public MaterialRecords add(MaterialRecordsAddApiForm addForm) {
		return MaterialRecordsDAO.getInstance().add(addForm);
	}

	public MaterialRecords get(long id) {
		return MaterialRecordsDAO.getInstance().get(id);
	}
	
	public List<MaterialRecords> findByStoreId(MaterialRecordsQueryParam params) {
		final String findPath = "findByStoreId";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", params.getStoreId())
				.add("maxTime", params.getMaxTime())
				.add("minTime", params.getMinTime());
		return MaterialRecordsDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo());
	}
	
	public List<MaterialRecords> findByMaterialId(MaterialRecordsQueryParam params) {
		final String findPath = "findByMaterialId";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("materialId", params.getMaterialId());
		return MaterialRecordsDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo());
	}

}
