package com.hq.storeFileClient.service.file.apiData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hq.storeFileClient.service.file.data.BaseUploadForm;

public class InputStreamApiForm {
	// 文件类型：img/video/file等 UploadFileType
	private String fileType;
	// 模块类型：store/buser/product等
	private String moduleType;
	// 具体的ID：3/45/78/等等
	private String moduleId;
	
	// 附件
	private List<InputStream> files = new ArrayList<InputStream>();
	private List<String> fileNames = new ArrayList<String>();

	public static InputStreamApiForm newInstance() {
		return new InputStreamApiForm();
	}

	public BaseUploadForm toBaseUploadForm() {
		BaseUploadForm form = BaseUploadForm.newInstance();
		form.setFileType(this.fileType);
		form.setModuleId(this.moduleId);
		form.setModuleType(this.moduleType);
		return form;
	}
	
	public void addFile(InputStream file, String fileName) {
		files.add(file);
		fileNames.add(fileName);
	}
	
	/**
	 * 请使用addFile(InputStream file, String fileName) 明确文件名
	 * @param file
	 */
	@Deprecated
	public void addFile(InputStream file) {
		files.add(file);
		fileNames.add(UUID.randomUUID().toString());
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

	public List<InputStream> getFiles() {
		return files;
	}

	public List<String> getFileNames() {
		return fileNames;
	}
}
