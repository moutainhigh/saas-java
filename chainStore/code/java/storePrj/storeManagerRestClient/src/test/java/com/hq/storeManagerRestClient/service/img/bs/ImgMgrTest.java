package com.hq.storeManagerRestClient.service.img.bs;

import java.io.File;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeManagerRestClient.service.img.apiData.FileUploadApiForm;
import com.hq.storeManagerRestClient.service.img.apiData.ImgResp;
import com.hq.storeManagerRestClient.testClass.AccessTokenMgr;
import com.hq.storeManagerRestClient.testClass.TestEnv;
import com.hq.storeManagerRestClient.testClass.robot.muser.Admin;
import com.hq.storeManagerRestClient.testClass.robot.muser.MUserRobot;

public class ImgMgrTest {
	private static Admin admin;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		admin = Admin.newAdmin(MUserRobot.newRandom());
		admin.loginWithParam("admin","123456");
	}
	
	@Test
	public void testImg() {
		AccessTokenMgr.getInstance().setOpIdTL(admin.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/IMG_20171231_193444.jpg");
			FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
			apiForm.setFileType("img");
			apiForm.setModuleType("product");
			apiForm.setModuleId("4");
			imgResp = ImgMgr.getInstance().saveImg(apiForm, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}


}
