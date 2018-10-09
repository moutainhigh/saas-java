package com.hq.chainStore.service.img.bs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.img.apiData.ImgResp;
import com.hq.chainStore.service.order.data.PayTypeEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class ImgMgrTest {
	private static Boss boss;
	private static long storeId = 21L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId=boss.getRobotStoreId();
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
			imgResp = ImgMgr.getInstance().saveImgs(apiForm, files);
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
			apiForm.setModuleType("store");
			apiForm.setModuleId(storeId+"");
			imgResp = ImgMgr.getInstance().saveImg(apiForm, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}

	@Test
	public void saveBUserImg() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理1.jpg");
			File file2 = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理2.jpg");
			List<File> files = new ArrayList<File>();
			files.add(file);
			files.add(file2);
			imgResp = ImgMgr.getInstance().saveBUserImg(boss.getId(), files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}
	
	@Test
	public void saveStoreImg() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/人物头像2.jpg");
			File file2 = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理2.jpg");
			List<File> files = new ArrayList<File>();
			files.add(file);
			files.add(file2);
			imgResp = ImgMgr.getInstance().saveStoreImg(boss.getId(), files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}
	
	@Test
	public void saveStorePayQrCode() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/aliPay.jpg");
			imgResp = ImgMgr.getInstance().saveStorePayQrCode(storeId, PayTypeEnum.ALIPAY.ordinal(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}
	
	@Test
	public void saveProductImg() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ImgResp imgResp = null;
		try {
			File file = new File("F:/honkon/doc/体验店数据/体验数据图片/人物头像2.jpg");
			File file2 = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理2.jpg");
			List<File> files = new ArrayList<File>();
			files.add(file);
			files.add(file2);
			imgResp = ImgMgr.getInstance().saveProductImg(storeId, "78", files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("应该非空", imgResp.getImgPathList().size() > 0);
		System.out.println(imgResp.getImgPathList());
	}

}
