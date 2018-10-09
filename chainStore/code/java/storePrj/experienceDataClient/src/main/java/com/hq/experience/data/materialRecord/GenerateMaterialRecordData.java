package com.hq.experience.data.materialRecord;

import java.util.ArrayList;
import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsAddApiData;
import com.hq.chainStore.service.materialRecords.apiData.MaterialRecordsAddApiForm;
import com.hq.chainStore.service.materialRecords.bs.MaterialRecordsMgr;
import com.hq.chainStore.service.storeMaterialInfo.bs.StoreMaterialInfoMgr;
import com.hq.chainStore.service.storeMaterialInfo.data.MaterialInfo;
import com.hq.experienceData.TMaterialRecord;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

@Deprecated
public class GenerateMaterialRecordData extends BaseGenerate{
	
	public static void main(String[] args) throws Exception {
		long phone = 13660623958L;
		String service = "http://192.168.10.170:9114/ws/v1";
		String reportService = "http://192.168.10.170:9117/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateMaterialRecordData().genData(phone);
	}
	
	public GenerateMaterialRecordData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genMaterialRecordData();
			}
			System.out.println("Generate MaterialRecord Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genMaterialRecordData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<MaterialInfo> materialInfos = new ArrayList<MaterialInfo>(StoreMaterialInfoMgr.getInstance().get(storeId).getMaterialInfoMap().values());
		for (MaterialInfo materialInfo : materialInfos) {
			List<TMaterialRecord> materialRecords = TMaterialRecord.buildTMaterialRecords();
			for (TMaterialRecord tMaterialRecord : materialRecords) {
				MaterialRecordsAddApiData materialRecordsAddApiData = MaterialRecordsAddApiData.newInstance();
				materialRecordsAddApiData.setStoreId(storeId);
				materialRecordsAddApiData.setMaterialId(materialInfo.getId());
				materialRecordsAddApiData.setCount(tMaterialRecord.getCount());
				materialRecordsAddApiData.setPrice(tMaterialRecord.getPrice());
				MaterialRecordsAddApiForm materialRecordsAddApiForm = MaterialRecordsAddApiForm.newInstance();
				materialRecordsAddApiForm.addMaterialRecords(materialRecordsAddApiData);
				MaterialRecordsMgr.getInstance().add(materialRecordsAddApiForm);
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
	
