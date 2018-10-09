package com.hq.experience.data.storeBeautician;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.AddBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateForm;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateType;
import com.hq.chainStore.service.storeBeauticianInfo.bs.StoreBeauticianInfoMgr;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianInfo;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

@Deprecated
public class GenerateStoreBeauticianData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623958L;
		String service = "http://192.168.10.169:9110/storems/ws/v1";
		String reportService = "http://192.168.10.169:9110/storereportms/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateStoreBeauticianData().genData(phone);
	}
	
	public GenerateStoreBeauticianData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genStoreBeauticianData();
			}
			System.out.println("Generate Beautician Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreBeauticianData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get("_sci"+this.storeId);
		List<ClerkInfo> list = new ArrayList<ClerkInfo>(storeClerkInfo.getClerkInfoMap().values());
		Collections.shuffle(list);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Map<Long, BeauticianInfo> beauticianInfoMap = StoreBeauticianInfoMgr.getInstance().get(storeId).getBeauticianInfoMap();
		Set<Long> ids = beauticianInfoMap.keySet();
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		List<ClerkInfo> targets = list.subList(0, list.size()/2);
		for (ClerkInfo clerkInfo : targets) {
			if(ids.contains(clerkInfo.getBuserId())){
				continue;
			}
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			AddBeauticianInfoData addBeauticianInfoData = AddBeauticianInfoData.newInstance();
			addBeauticianInfoData.setBuserId(clerkInfo.getBuserId());
			StoreBeauticianInfoUpdateForm updateForm = StoreBeauticianInfoUpdateForm.newInstance();
			updateForm.setUpdateTypeEnum(StoreBeauticianInfoUpdateType.AddBeauticianInfo);
			updateForm.setStoreId(storeId);
			updateForm.setAddBeauticianInfoData(addBeauticianInfoData);
			StoreBeauticianInfoMgr.getInstance().update(storeId, updateForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
}
