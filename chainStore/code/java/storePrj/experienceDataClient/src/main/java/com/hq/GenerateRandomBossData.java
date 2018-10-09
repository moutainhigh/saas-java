package com.hq;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.BUserUpdateInfoApiData;
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
import com.hq.experience.data.storeClerk.GenerateStoreClerkData;
import com.hq.experience.data.storeLeaguerInfo.GenerateRandomStoreLeaguerInfoData;
import com.hq.experience.data.storeProductInfo.GenerateRandomStoreProductInfoData;
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
 * 随机生成用户，新注册，生成体验数据。
 * @author Administrator
 *
 */
public class GenerateRandomBossData{
	private String basePath = "F:/honkon/doc/体验店数据/体验数据图片/";
	private BUser newBoss;
	
	public static void main(String[] args) throws Exception {
		String storeService = "http://192.168.40.221:9110/storems/ws/v1";
		String orderService = "http://192.168.40.221:9110/orderms/ws/v1";
		
//		String storeService = "http://192.168.10.155:9110/storems/ws/v1";
//		String reportService = "http://192.168.10.155:9110/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		//启动10条线程  每条线程生成1000个店铺的信息  总计1W个店铺
		for (int i = 0; i < 2; i++) {
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						new GenerateRandomBossData().genData();
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Thread.sleep(1000);
		}
		
		fixedThreadPool.shutdown();
		
//		new GenerateNewBossData().genData();
	}
	
	public GenerateRandomBossData(){
		super();
	}
	
	public void genData() throws Exception{
		//10W家店   项目50   员工15 客户100
		long startTime = System.currentTimeMillis();
		long endTime = startTime;
		for (int i = 0; i < 2; i++) {
			genRandomBoss();
			openRandomStore();
			long phone = newBoss.getPhone();
			new GenerateStoreClerkData().genData(phone);
//			new GenerateStoreBeauticianData().genData(phone);
//			new GenerateStoreMaterialData().genData(phone);
			
			new GenerateRandomStoreProductInfoData().genData(phone);
			new GenerateRandomStoreLeaguerInfoData().genData(phone);
			endTime = System.currentTimeMillis();
			System.out.println("第"+i+"次耗时："+(endTime-startTime));
			startTime = endTime;
		}
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
	}
	
	public void openRandomStore() {
		//开店，每个店上传支付宝、微信二维码
		TStoreData tStoreData=TStoreData.newInstance(RandomUtils.nextLong(100, 999));
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
	
	public BUser regBuser(BUserAddApiForm formInfo, String imgName, Set<Integer> roleSet){
//		RestResp restResp = BUserMgr.getInstance().reg(formInfo);
		long phone = 12000000000L + RandomUtils.nextLong(100000000L, 999999999L);
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
	@SuppressWarnings("deprecation")
	public String uploadImg(FileUploadApiForm apiForm, String imgName) {
		InputStream is = null;
		ImgResp imgResp = null;
		String imgUrl = "";
		try {
			is = new FileInputStream(new File(basePath+imgName));
			imgResp = ImgMgr.getInstance().saveImg(getSaveImgParams(is, imgName, apiForm));
			if(imgResp!=null){
				imgUrl = imgResp.getImgPathList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return imgUrl;
	}
	
	public Object getSaveImgParams(InputStream is, String fileName,
			FileUploadApiForm apiForm) {
		ByteArrayResource resource = exchangeStream(is, fileName);
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.add("img", resource);
		param.add("fileType", apiForm.getFileType());
		param.add("moduleType", apiForm.getModuleType());
		param.add("moduleId", apiForm.getModuleId());
		return param;
	}

	public ByteArrayResource exchangeStream(final InputStream is,
			final String fileName) {
		ByteArrayResource resource = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while (is.read(buffer) > 0) {
				bos.write(buffer);
			}
			is.close();
			bos.close();
			resource = new ByteArrayResource(bos.toByteArray()) {
				@Override
				public String getFilename() throws IllegalStateException {
					return fileName;
				}

			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}
}
	
