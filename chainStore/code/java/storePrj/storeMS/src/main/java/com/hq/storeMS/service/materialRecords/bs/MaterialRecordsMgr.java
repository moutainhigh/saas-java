package com.hq.storeMS.service.materialRecords.bs;

import java.util.List;

import com.hq.storeMS.service.materialRecords.data.MaterialRecords;
import com.zenmind.common.hotSwap.HotSwap;

public class MaterialRecordsMgr {

	public static MaterialRecordsMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MaterialRecordsMgr.class);
	}
	
	public void addAndReturnId(MaterialRecords target) {
		MaterialRecordsDataHolder.getInstance().addAndReturnId(target);
	}
	
	public MaterialRecords get(long id){
		return MaterialRecordsDataHolder.getInstance().get(id);
	}
	
	public List<MaterialRecords> findByStoreId(long storeId,long maxTime,long minTime,int pageItemCount, int pageNo) {
		List<MaterialRecords> findList = MaterialRecordsDataHolder.getInstance().findByStoreId(storeId,maxTime,minTime,pageItemCount, pageNo);
		return findList;
	}
	
	public List<MaterialRecords> findByMaterialId(String materialId,int pageItemCount, int pageNo) {
		List<MaterialRecords> findList = MaterialRecordsDataHolder.getInstance().findByMaterialId(materialId,pageItemCount, pageNo);
		return findList;
	}

}
