package com.hq.chainStore.service.storeFile.apiData;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class FileResp {

	public static FileResp newInstance(){
		return new FileResp();
	}
	
	private List<String> imgPathList = new ArrayList<String>();
	
	public FileResp addPath(String path){
		imgPathList.add(path);
		return this;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}
}
