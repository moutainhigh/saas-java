package com.hq.chainClient.service.img.bs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.img.apiData.FileUploadApiForm;
import com.hq.chainClient.service.img.apiData.ImgResp;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;

public class ImgClientMgrTest {
	private static Boss boss;
	private static long chainId = 0L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
		chainId = boss.getChainId();
	}

	@Test
	public void testSaveImgs() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理7.jpg");
			File file2 = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理2.jpg");
			List<File> files = new ArrayList<File>();
			files.add(file);
			files.add(file2);
			FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
			apiForm.setFileType("img");
			apiForm.setModuleType("storeGoods");
			apiForm.setModuleId("4");
			imgResp = ImgClientMgr.getInstance().saveImgs(apiForm, files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}

	@Test
	public void testImg() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/图层 1.png");
			FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
			apiForm.setFileType("img");
			apiForm.setModuleType("chain");
			apiForm.setModuleId(chainId + "");
			imgResp = ImgClientMgr.getInstance().saveImg(apiForm, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}

}
