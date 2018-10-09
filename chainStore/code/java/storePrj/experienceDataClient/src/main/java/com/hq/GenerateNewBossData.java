package com.hq;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.chainStore.service.buser.apiData.BUserUpdateVipTypeData;
import com.hq.chainStore.service.buser.apiData.LoginResp;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.img.apiData.ImgResp;
import com.hq.chainStore.service.img.bs.ImgMgr;
import com.hq.chainStore.service.store.apiData.AlipayQrCodeApiData;
import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.apiData.WechatQrCodeApiData;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeVipType.data.StoreVipLevelEnum;
import com.hq.experience.data.store.GenerateStoreData;
import com.hq.experience.data.storeCardInfo.GenerateStoreCardInfoData;
import com.hq.experience.data.storeClerk.GenerateStoreClerkData;
import com.hq.experience.data.storeGoods.GenerateStoreGoodsData;
import com.hq.experience.data.storeLeaguerInfo.GenerateStoreLeaguerInfoData;
import com.hq.experience.data.storeProductInfo.GenerateStoreProductInfoData;
import com.hq.experienceData.TBoss;
import com.hq.experienceData.TStoreData;
import com.hq.experienceData.service.DataConstants;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

/**
 * 为指定的号码，注册、开店、生成体验数据。
 * @author Administrator
 *
 */
public class GenerateNewBossData{
	private String basePath = "F:/honkon/doc/体验店数据/体验数据图片/";
	private long phone = 13660623953L;
	private BUser newBoss;
	
	public static void main(String[] args) throws Exception {
//		String storeService = "http://192.168.40.221/storems/ws/v1";
//		String orderService = "http://192.168.40.221/orderms/ws/v1";
		
		String storeService = "http://192.168.40.220/storems/ws/v1";
		String orderService = "http://192.168.40.220/orderms/ws/v1";
		
//		String storeService = "http://192.168.40.52/storems/ws/v1";
//		String orderService = "http://192.168.40.52/orderms/ws/v1";
		
//		String storeService = "https://www.zhimeitimes.com:9129/storems/ws/v1";
//		String orderService = "https://www.zhimeitimes.com:9129/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		
		new GenerateNewBossData().genData();
	}
	
	public GenerateNewBossData(){
		super();
	}
	
	public void genData() throws Exception{
		//每家店   项目50   员工15 客户100
		genRandomBoss();
//		open3Store();//老板开三家店铺
		new GenerateStoreData().genData(phone);//开一家店铺
		new GenerateStoreClerkData().genData(phone);
		new GenerateStoreProductInfoData().genData(phone);
		new GenerateStoreLeaguerInfoData().genData(phone);
		new GenerateStoreCardInfoData().genData(phone);
		new GenerateStoreGoodsData().genData(phone);
	}
	
	public void genRandomBoss(){
		//老板注册 更新权限、头像信息
//		TBoss tBoss = TBoss.buildTBoss();
		TBoss tBoss = TBoss.newInstance(RandomUtils.nextLong(10, 100));
		BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
		formInfo.setName(tBoss.getName());
		formInfo.setGender(tBoss.getGender());
		formInfo.setAge(tBoss.getAge());
		formInfo.setPhone(tBoss.getPhone());
		formInfo.setPassword(DataConstants.PASSWORD);
		newBoss = regBuser(formInfo, tBoss.getImgPath(), tBoss.getRoleSet());
		
		updateVipType();
	}
	
	private void updateVipType(){
		AccessTokenMgr.getInstance().setOpIdTL(newBoss.getId());
		BUserUpdateVipTypeData updateVipTypeData = BUserUpdateVipTypeData.newInstance();
		updateVipTypeData.setId(newBoss.getId());
		updateVipTypeData.setVipType(StoreVipLevelEnum.InnerBetaUser.ordinal());
		BUserMgr.getInstance().updateVipType(newBoss.getId(), updateVipTypeData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void open3Store() {
		//开店，每个店上传支付宝、微信二维码
		List<TStoreData> tStoreDatas=TStoreData.buildTStoreData();
		for (TStoreData tStoreData : tStoreDatas) {
			AccessTokenMgr.getInstance().setOpIdTL(newBoss.getId());
			StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(tStoreData, formInfo);
			formInfo.setBossId(newBoss.getId());
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
	
	public BUser regBuser(BUserAddApiForm formInfo, String imgName, Set<Integer> roleSet){
		formInfo.setPhone(phone);
		formInfo.setRoleSet(roleSet);
		BUser buser = doRegBuser(formInfo, imgName, roleSet);
		while (buser==null) {
			formInfo.setPhone(formInfo.getPhone()+1);
			buser = doRegBuser(formInfo, imgName, roleSet);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		BUserLoginApiForm loginInfo = BUserLoginApiForm.newInstance();
		loginInfo.setPhone(buser.getPhone());
		loginInfo.setPassword(DataConstants.PASSWORD);
		RestResp restResp2 = BUserMgr.getInstance().login(loginInfo);
		
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp2.gettJson(), LoginResp.class);
		AccessTokenMgr.getInstance().putToken(buser.getId(), loginResp.getToken());
		
		AccessTokenMgr.getInstance().setOpIdTL(buser.getId());
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType("img");
		apiForm.setModuleType("buser");
		apiForm.setModuleId(buser.getId()+"");
		String uploadImg = uploadImg(apiForm, imgName);
		
		BUserUpdateInfoApiData updateData = BUserUpdateInfoApiData.newInstance();
		updateData.setAge(buser.getAge());
		updateData.setBuserId(buser.getId());
		updateData.setGender(buser.getGender());
		updateData.setName(buser.getName());
		updateData.setHeadImg(uploadImg);
		updateData.setRoleSet(roleSet);
		BUserMgr.getInstance().updateInfo(buser.getId(), updateData);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return buser;
	}
	
	private BUser doRegBuser(BUserAddApiForm formInfo, String imgName, Set<Integer> roleSet){
		BUser buser = null;
		RestResp restResp = null;
		try {
			restResp = BUserMgr.getInstance().reg(formInfo);
			if(restResp!=null && restResp.gettJson() != null){
				buser = JsonUtil.getInstance().fromJson(restResp.gettJson(), BUser.class);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return buser;
	}
	
	//上传图片
	public String uploadImg(FileUploadApiForm apiForm, String imgName) {
		ImgResp imgResp = null;
		String imgUrl = "";
		try {
			imgResp = ImgMgr.getInstance().saveImg(apiForm, new File(basePath+imgName));
			if(imgResp!=null){
				imgUrl = imgResp.getImgPathList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgUrl;
	}
}
	
