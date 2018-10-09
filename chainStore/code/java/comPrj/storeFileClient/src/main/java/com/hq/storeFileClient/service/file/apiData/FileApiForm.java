package com.hq.storeFileClient.service.file.apiData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hq.storeFileClient.service.file.data.BaseUploadForm;

public class FileApiForm {
	// 文件类型：img/video/file等 UploadFileType
	private String fileType;
	// 模块类型：store/buser/product等
	private String moduleType;
	// 具体的ID：3/45/78/等等
	private String moduleId;
	// 附件
	private List<File> files = new ArrayList<File>();

	public static FileApiForm newInstance() {
		return new FileApiForm();
	}
	
	public BaseUploadForm toBaseUploadForm() {
		BaseUploadForm form = BaseUploadForm.newInstance();
		form.setFileType(this.fileType);
		form.setModuleId(this.moduleId);
		form.setModuleType(this.moduleType);
		return form;
	}
	
	public void addFile(File file) {
		files.add(file);
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

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

}
