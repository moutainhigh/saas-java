package com.hq.payms.service.qrcode.apiData;

public class QrCodeAPIForm {
	private String content;
	private int size;
	private int logoWidth;
	private int logoHeigth;
	
	private String logoUrl;//logo的网络URL

	public static QrCodeAPIForm newInstance() {
		return new QrCodeAPIForm();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLogoWidth() {
		return logoWidth;
	}

	public void setLogoWidth(int logoWidth) {
		this.logoWidth = logoWidth;
	}

	public int getLogoHeigth() {
		return logoHeigth;
	}

	public void setLogoHeigth(int logoHeigth) {
		this.logoHeigth = logoHeigth;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Override
	public String toString() {
		return "QrCodeAPIForm [content=" + content + ", size=" + size
				+ ", logoWidth=" + logoWidth + ", logoHeigth=" + logoHeigth
				+ "]";
	}
}
