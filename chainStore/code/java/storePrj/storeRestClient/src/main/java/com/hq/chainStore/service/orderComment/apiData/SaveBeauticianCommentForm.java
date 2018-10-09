package com.hq.chainStore.service.orderComment.apiData;

import java.util.List;

public class SaveBeauticianCommentForm{
	private String content;
	private List<String> imgPaths;

	public static SaveBeauticianCommentForm newInstance() {
		SaveBeauticianCommentForm saveBeauticianCommentForm = new SaveBeauticianCommentForm();
		return saveBeauticianCommentForm;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

}
