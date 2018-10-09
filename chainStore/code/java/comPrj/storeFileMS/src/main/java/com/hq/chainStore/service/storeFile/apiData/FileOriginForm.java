package com.hq.chainStore.service.storeFile.apiData;

import org.springframework.web.multipart.MultipartFile;

public class FileOriginForm {
	private String path;
	private MultipartFile file;

	public static FileOriginForm newInstance() {
		return new FileOriginForm();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
