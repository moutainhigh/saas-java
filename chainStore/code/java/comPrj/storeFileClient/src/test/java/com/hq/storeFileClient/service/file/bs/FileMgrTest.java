package com.hq.storeFileClient.service.file.bs;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeFileClient.service.file.apiData.FileApiForm;
import com.hq.storeFileClient.service.file.apiData.FileOriginForm;
import com.hq.storeFileClient.service.file.apiData.InputStreamApiForm;
import com.hq.storeFileClient.service.file.apiData.InputStreamOriginForm;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeFileClient.testClass.TestEnv;

public class FileMgrTest {
	
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testDeleteFile() {
		String filePath = "img/storeGoods/22/2018_05_10_15_23caab0c-3c62-4f57-84b0-4fb091909dda-reduce.jpg";
		FileMgr.getInstance().deleteFile(filePath);
	}
	
	@Test
	public void testSaveFile() {
		try {
			FileApiForm apiForm = FileApiForm.newInstance();
//			apiForm.addFile(new File("F:/test.jpg"));
			apiForm.addFile(new File("F:/honkon/doc/体验店资料/体验店图片/客户头像/客户1/陈佩雯.jpg"));
			apiForm.setFileType("img");
			apiForm.setModuleType("storeGoods");
			apiForm.setModuleId("22");
			FileResp fileResp = FileMgr.getInstance().saveFile(apiForm);
			if(fileResp != null) {
				System.out.println(fileResp.getImgPathList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveFileByInputStream() {
		try {
			File file = new File("F:/honkon/doc/体验店资料/体验店图片/客户头像/客户1/陈佩雯.jpg");
			FileInputStream is = new FileInputStream(file);
			InputStreamApiForm apiForm = InputStreamApiForm.newInstance();
			apiForm.addFile(is, UUID.randomUUID().toString());
			apiForm.setFileType("img");
			apiForm.setModuleType("storeGoods");
			apiForm.setModuleId("22");
			FileResp fileResp = FileMgr.getInstance().saveFile(apiForm);
			if(fileResp != null) {
				System.out.println(fileResp.getImgPathList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveFileWithOriginInfo() {
		try {
			FileOriginForm apiForm = FileOriginForm.newInstance();
			apiForm.setFile(new File("F:/honkon/doc/体验店资料/体验店图片/客户头像/客户1/陈佩雯.jpg"));
			apiForm.setPath("file/test/name.jpg");
			FileResp fileResp = FileMgr.getInstance().saveFileWithOriginInfo(apiForm);
			if(fileResp != null) {
				System.out.println(fileResp.getImgPathList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveFileWithOriginInfoByStream() {
		try {
			File file = new File("F:/honkon/doc/体验店资料/体验店图片/客户头像/客户1/陈佩雯.jpg");
			FileInputStream is = new FileInputStream(file);
			InputStreamOriginForm apiForm = InputStreamOriginForm.newInstance();
			apiForm.addFile(is, file.getName());
			apiForm.setPath("file/test/name.jpg");
			FileResp fileResp = FileMgr.getInstance().saveFileWithOriginInfo(apiForm);
			if(fileResp != null) {
				System.out.println(fileResp.getImgPathList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
