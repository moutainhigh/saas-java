package com.hq.customerRestClient.service.img.apiData;

import java.util.ArrayList;
import java.util.List;

public class ImgResp {

	public static ImgResp newInstance() {
		return new ImgResp();
	}

	//图片相对路径列表
	private List<String> imgPathList = new ArrayList<String>();

	public ImgResp addPath(String path) {
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
