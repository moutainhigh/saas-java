package com.hq.chainStore.service.orderComment.data;

import java.util.List;

public class BeauticianComment {
	private String content;
	private List<String> imgPaths;

	public static BeauticianComment newInstance() {
		BeauticianComment orderComment = new BeauticianComment();
		return orderComment;
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
