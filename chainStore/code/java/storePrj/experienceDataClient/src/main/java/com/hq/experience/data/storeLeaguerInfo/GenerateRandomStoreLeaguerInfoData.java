package com.hq.experience.data.storeLeaguerInfo;

import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.chainStore.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.experienceData.TCustomer;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateRandomStoreLeaguerInfoData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623958L;
		String service = "http://192.168.10.169:9110/storems/ws/v1";
		String reportService = "http://192.168.10.169:9110/storereportms/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateRandomStoreLeaguerInfoData().genData(phone);
	}
	
	public GenerateRandomStoreLeaguerInfoData(){
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
		List<TCustomer> list = TCustomer.buildRandomTCustomerList(100);
		for (TCustomer tCustomer : list) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			LeaguerAddApiForm addForm = LeaguerAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(tCustomer, addForm);
			StoreLeaguerInfoUpdateApiForm updateForm = StoreLeaguerInfoUpdateApiForm.newInstance();
			updateForm.setStoreId(storeId);
			updateForm.setUpdateType(StoreLeaguerInfoUpdateType.AddLeaguerInfo.ordinal());
			updateForm.setLeaguerAddInfoData(addForm);
			addLeaguer(storeId, updateForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
	
	private void addLeaguer(long storeId, StoreLeaguerInfoUpdateApiForm updateForm){
		try {
			StoreLeaguerInfoMgr.getInstance().updateStoreLeaguerInfo(storeId, updateForm);
		} catch (Exception e) {
			updateForm.getLeaguerAddInfoData().setPhone((updateForm.getLeaguerAddInfoData().getPhone()+1));
			try {
				StoreLeaguerInfoMgr.getInstance().updateStoreLeaguerInfo(storeId, updateForm);
			} catch (Exception e2) {
				updateForm.getLeaguerAddInfoData().setPhone((updateForm.getLeaguerAddInfoData().getPhone()+1));
				StoreLeaguerInfoMgr.getInstance().updateStoreLeaguerInfo(storeId, updateForm);
			}
		}
	}
}
