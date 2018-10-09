package com.hq.storeManagerMS.service.img.apiData;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadApiForm {
	// 文件类型：img/video/file等
	private String fileType;
	// 模块类型：store/buser/product等
	private String moduleType;
	// 具体的ID：3/45/78/等等
	private String moduleId;
	// 附件
	private MultipartFile img;

	public static FileUploadApiForm newInstance() {
		return new FileUploadApiForm();
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

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}

}
