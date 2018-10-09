package com.hq.experience.data.store;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.store.apiData.AlipayQrCodeApiData;
import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.apiData.WechatQrCodeApiData;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.experienceData.TStoreData;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateStoreData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623951L;
		String service = "http://192.168.40.220/storems/ws/v1";
		String reportService = "http://192.168.40.220/storems/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateStoreData().genData(phone);
	}
	
	public GenerateStoreData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			genStoreData();
			System.out.println("Generate Store Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStoreData() {
		//开店，每个店上传支付宝、微信二维码
		TStoreData tStoreData=TStoreData.newInstance(RandomUtils.nextLong(100, 999));
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(tStoreData, formInfo);
		formInfo.setBossId(boss.getId());
		Store store = StoreMgr.getInstance().openStore(formInfo);
		
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType("img");
		apiForm.setModuleType("store");
		apiForm.setModuleId(store.getId()+"");
		String uploadImg = uploadImg(apiForm, tStoreData.getAliPath());
		
		AlipayQrCodeApiData info = AlipayQrCodeApiData.newInstance();
		info.setStoreId(store.getId());
		info.setAlipayImg(uploadImg);
		StoreMgr.getInstance().uploadAlipayQrCode(info);
		
		FileUploadApiForm apiForm2 = FileUploadApiForm.newInstance();
		apiForm2.setFileType("img");
		apiForm2.setModuleType("store");
		apiForm2.setModuleId(store.getId()+"");
		String uploadImg2 = uploadImg(apiForm2, tStoreData.getWechatPath());
		
		WechatQrCodeApiData info2 = WechatQrCodeApiData.newInstance();
		info2.setStoreId(store.getId());
		info2.setWechatImg(uploadImg2);
		StoreMgr.getInstance().uploadWechatQrCode(info2);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
