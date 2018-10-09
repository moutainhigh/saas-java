package com.hq.storeMS.service.download.apiData;

public class DownLoadDataForm {
	//下载的类型  对应DownLoadTypeEnum
	private int downLoadType;
	
	public static DownLoadDataForm newInstance(){
		return new DownLoadDataForm();
	}

	public int getDownLoadType() {
		return downLoadType;
	}

	public void setDownLoadType(int downLoadType) {
		this.downLoadType = downLoadType;
	}
}
