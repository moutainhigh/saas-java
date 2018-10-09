package com.hq.storeClient.common.exception;

import com.zenmind.common.StringFormatUtil;

public class StoreClientException extends RuntimeException {
	
	private static final long serialVersionUID = -34371801147010839L;
	
	private static final String msgFormat = "tips:{}";

	private String tips;

	private StoreClientException(String msg, Exception e) {
		super(msg, e);
	}

	private StoreClientException(String tips) {
		this.tips = tips;
	}

	public static StoreClientException newInstance(String tips) {
		StoreClientException instance = new StoreClientException(tips);
		return instance;
	}

	public static StoreClientException newInstance(String tips, Exception e) {
		String msg = StringFormatUtil.format(msgFormat, tips);
		StoreClientException instance = new StoreClientException(msg, e);
		return instance;
	}

	public String getTips() {
		return tips;
	}

}
