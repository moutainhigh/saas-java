package com.hq.customerRestClient.service.img.bs;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.img.apiData.FileUploadApiForm;
import com.hq.customerRestClient.service.img.apiData.ImgResp;
import com.hq.customerRestClient.testClass.TestEnv;

public class ImgClientMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void testSaveImg() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		File file = new File("F:/honkon/doc/体验店数据/体验数据图片/面部护理1.jpg");
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType("img");
		apiForm.setModuleId("140");
		apiForm.setModuleType("product");
		ImgResp imgResp = ImgClientMgr.getInstance().saveImg(apiForm, file);
		System.out.println(imgResp.getImgPathList());
	}
}
