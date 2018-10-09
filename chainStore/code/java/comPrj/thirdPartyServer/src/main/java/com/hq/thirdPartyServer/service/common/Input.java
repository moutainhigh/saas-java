package com.hq.thirdPartyServer.service.common;

public class Input {
	private Image image;
	private Configure configure;

	public static Input newInstance(Image image, Configure configure) {
		Input data = new Input();
		data.image = image;
		data.configure = configure;
		return data;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Configure getConfigure() {
		return configure;
	}

	public void setConfigure(Configure configure) {
		this.configure = configure;
	}

	
}
