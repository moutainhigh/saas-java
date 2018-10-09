package com.hq.storeMS.service.bossPayInfo.apiData;

import org.springframework.web.multipart.MultipartFile;


public class CertFileUpLoadForm {

	private String moduleId;
	
	private String moduleType;//UploadModuleType
		
	private MultipartFile file;
	
	public static CertFileUpLoadForm newInstance() {
		return new CertFileUpLoadForm();
	}

	

	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}



	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
}
