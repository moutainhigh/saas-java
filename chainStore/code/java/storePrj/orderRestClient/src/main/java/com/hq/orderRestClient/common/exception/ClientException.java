package com.hq.orderRestClient.common.exception;

import com.zenmind.common.StringFormatUtil;

public class ClientException extends RuntimeException {
	
	private static final long serialVersionUID = -34371801147010839L;
	
	private static final String msgFormat = "tips:{}";

	private String tips;

	private ClientException(String msg, Exception e) {
		super(msg, e);
	}

	private ClientException(String tips) {
		this.tips = tips;
	}

	public static ClientException newInstance(String tips) {
		ClientException instance = new ClientException(tips);
		return instance;
	}

	public static ClientException newInstance(String tips, Exception e) {
		String msg = StringFormatUtil.format(msgFormat, tips);
		ClientException instance = new ClientException(msg, e);
		return instance;
	}

	public String getTips() {
		return tips;
	}

}
