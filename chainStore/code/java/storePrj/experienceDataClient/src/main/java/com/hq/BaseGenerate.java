package com.hq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserLoginApiForm;
import com.hq.chainStore.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.chainStore.service.buser.apiData.LoginResp;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.img.apiData.ImgResp;
import com.hq.chainStore.service.img.bs.ImgMgr;
import com.hq.experienceData.service.DataConstants;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

/**
 * 模块基础类
 * @author Administrator
 *
 */
public abstract class BaseGenerate {
	protected BUser boss;
	protected long storeId;
	protected Set<Long> storeIds = new HashSet<>();
	protected String basePath = "F:/honkon/doc/体验店数据/体验数据图片/";
//	protected String basePath = "F:/honkon/doc/体验店资料/体验店图片/客户头像/";
	
	public abstract void genData(long phone);
	
	public BUser regBuser(BUserAddApiForm formInfo, String imgName, Set<Integer> roleSet){
		long phone = 13500000000L + RandomUtils.nextLong(100000000L, 999999999L);
		formInfo.setPhone(phone);
		formInfo.setRoleSet(roleSet);
		BUser buser = doRegBuser(formInfo, imgName, roleSet);
		while (buser==null) {
			formInfo.setPhone(formInfo.getPhone()+1);
			buser = doRegBuser(formInfo, imgName, roleSet);
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
	
	public void initEnv(long phone){
		BUserLoginApiForm loginForm = BUserLoginApiForm.newInstance();
		loginForm.setPhone(phone).setPassword(DataConstants.PASSWORD);
		RestResp restResp = BUserMgr.getInstance().login(loginForm);
		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		boss = loginResp.getBuser();
		storeIds = boss.getStoreIdSet();//默认取用户所有的店铺
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(boss.getId(), token);
	}
	
	//上传图片
	public String uploadImg(FileUploadApiForm apiForm, String imgName) {
		InputStream is = null;
		ImgResp imgResp = null;
		String imgUrl = "";
		try {
			imgResp = ImgMgr.getInstance().saveImg(apiForm, new File(basePath+imgName));
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

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
}
