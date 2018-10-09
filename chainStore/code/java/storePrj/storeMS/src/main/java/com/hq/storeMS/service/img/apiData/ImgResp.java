package com.hq.storeMS.service.img.apiData;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ImgResp {

	public static ImgResp newInstance(){
		return new ImgResp();
	}
	
	private List<String> imgPathList = new ArrayList<String>();
	
	public ImgResp addPath(String path){
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
