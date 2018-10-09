package com.hq.storeFileClient.service.file.apiData;

import java.io.File;

public class FileOriginForm {
	private String path;
	private File file;

	public static FileOriginForm newInstance() {
		return new FileOriginForm();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
