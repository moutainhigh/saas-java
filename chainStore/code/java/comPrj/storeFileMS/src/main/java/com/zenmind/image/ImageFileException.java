package com.zenmind.image;

import com.zenmind.common.StringFormatUtil;

public class ImageFileException extends RuntimeException {
	private static final long serialVersionUID = -7312352610682207078L;
	
	final static private String msgFormat = "msg:{}, keyOrSql:{}";

	private ImageFileException(String msg, Exception e){
		super(msg, e);		
	}
	
	public static ImageFileException newInstance(String msgP, String keyOrSql, Exception e) {
		String msg = StringFormatUtil.format(msgFormat, msgP, keyOrSql);
		ImageFileException instance = new ImageFileException(msg, e);
		return instance;
	}
	
}
