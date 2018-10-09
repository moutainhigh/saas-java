package com.hq.storeClient.service.wxMedia.apiData;

public class WxMediaSaveApiForm {
	
	private String mediaId; //微信端图片、语音、视频等多媒体文件的ID
	
	private String appId; //主体公众号appId
	
	public static WxMediaSaveApiForm newInstance() {
		return new WxMediaSaveApiForm();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	

}
