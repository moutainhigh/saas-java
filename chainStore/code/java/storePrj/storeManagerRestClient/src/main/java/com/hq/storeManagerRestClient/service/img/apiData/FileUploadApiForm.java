package com.hq.storeManagerRestClient.service.img.apiData;

import java.util.HashMap;
import java.util.Map;

public class FileUploadApiForm {
	// 文件类型：img/video/file等
	private String fileType;
	// 模块类型：store/buser/product等
	private String moduleType;
	// 具体的ID：3/45/78/等等
	private String moduleId;

	public static FileUploadApiForm newInstance() {
		return new FileUploadApiForm();
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileType", this.fileType);
		map.put("moduleType", this.moduleType);
		map.put("moduleId", this.moduleId);
		return map;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}
