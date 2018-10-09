package com.hq.experience.data.storeLeaguerInfo;

import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.experienceData.TCustomer;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateStoreLeaguerInfoData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623953L;
		
		String storeService = "https://www.zhimeitimes.com:9129/storems/ws/v1";
		String orderService = "https://www.zhimeitimes.com:9129/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateStoreLeaguerInfoData().genData(phone);
	}
	
	public GenerateStoreLeaguerInfoData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genStoreLeaguerInfoData();
			}
			System.out.println("Generate LeaguerInfo Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreLeaguerInfoData() {
		List<TCustomer> list = TCustomer.buildTCustomerList();
		for (TCustomer tCustomer : list) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(tCustomer, addForm);
			FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
			apiForm.setFileType("img");
			apiForm.setModuleType("storeLeaguerInfo");
			apiForm.setModuleId(boss.getId()+"");
			String uploadImg = uploadImg(apiForm, tCustomer.getName()+".jpg");
			addForm.setHeadImg(uploadImg);
			StoreLeaguerInfoMgr.getInstance().addLeaguerInfo(storeId, addForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
}
