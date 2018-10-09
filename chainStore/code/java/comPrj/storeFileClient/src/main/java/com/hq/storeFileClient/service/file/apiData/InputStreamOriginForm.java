package com.hq.storeFileClient.service.file.apiData;

import java.io.InputStream;

public class InputStreamOriginForm {
	private String path;
	private InputStream file;
	private String fileName;

	public static InputStreamOriginForm newInstance() {
		return new InputStreamOriginForm();
	}
	
	public void addFile(InputStream fileP, String fileNameP) {
		this.file = fileP;
		this.fileName = fileNameP;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public InputStream getFile() {
		return file;
	}

	public String getFileName() {
		return fileName;
	}
}
