package com.hq.experience.data.storeMaterial;

import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.storeMaterialInfo.apiData.AddMaterialInfoForm;
import com.hq.chainStore.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateForm;
import com.hq.chainStore.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateType;
import com.hq.chainStore.service.storeMaterialInfo.bs.StoreMaterialInfoMgr;
import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.experienceData.TMaterial;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

@Deprecated
public class GenerateStoreMaterialData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623958L;
		String service = "http://192.168.10.169:9110/storems/ws/v1";
		String reportService = "http://192.168.10.169:9110/storereportms/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateStoreMaterialData().genData(phone);
	}
	
	public GenerateStoreMaterialData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genStoreMaterialData();
			}
			System.out.println("Generate Material Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreMaterialData() {
		List<TMaterial> tMaterials = TMaterial.buildTMaterials();
		for (TMaterial tMaterial : tMaterials) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StoreMaterialInfo storeMaterialInfo = StoreMaterialInfoMgr.getInstance().get(storeId);
			
			StoreMaterialInfoUpdateForm storeMaterialInfoUpdateForm = StoreMaterialInfoUpdateForm.newInstance();
			storeMaterialInfoUpdateForm.setUpdateTypeEnum(StoreMaterialInfoUpdateType.AddMaterialInfo);
			storeMaterialInfoUpdateForm.setStoreId(storeId);
			
			AddMaterialInfoForm addMaterialInfoForm = AddMaterialInfoForm.newInstance();
			FastBeanCopyer.getInstance().copy(tMaterial, addMaterialInfoForm);
			addMaterialInfoForm.setId(storeId+"_"+(storeMaterialInfo.getMaterialIdIndex()+1));
			storeMaterialInfoUpdateForm.setAddMaterialInfoForm(addMaterialInfoForm);
			StoreMaterialInfoMgr.getInstance().update(storeId, storeMaterialInfoUpdateForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
}
