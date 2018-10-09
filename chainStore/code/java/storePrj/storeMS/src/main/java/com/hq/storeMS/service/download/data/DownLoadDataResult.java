package com.hq.storeMS.service.download.data;

import java.io.InputStream;

public class DownLoadDataResult {
	private String fileName;
	private InputStream inputStream;
	
	public static DownLoadDataResult newInstance(){
		return new DownLoadDataResult();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
