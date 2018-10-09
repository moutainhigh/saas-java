package com.hq.payms.service.qrcode.apiData;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class QrCodeResp {

	public static QrCodeResp newInstance() {
		return new QrCodeResp();
	}

	private List<String> imgUrlList = new ArrayList<String>();// 批量生成二维码图片
	private String imgUrl;// 生成单个二维码图片

	public QrCodeResp addPath(String path) {
		imgUrlList.add(path);
		return this;
	}

	public List<String> getImgUrlList() {
		return imgUrlList;
	}

	public void setImgUrlList(List<String> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
