package com.zenmind.applets.apiData;

import com.zenmind.base.StringFormatUtil;

public class ApiDataGen4Applets {

	
	private String pathTemplate="{}\\codeGen\\applets\\apiData\\";
	

	public void doGen() throws Exception {
		
		//获取用户的当前工作目录 
		//F:\zmPrj\saas-java\chainStore\code\java\comPrj\zmDevHelper
		String basePath = System.getProperty("user.dir");
		
		String opFolder = StringFormatUtil.format(pathTemplate, basePath);

		ApiDataCodeGener.newInstance(opFolder).doGen();
	}
	
	public static void main(String[] args) throws Exception {
		new ApiDataGen4Applets().doGen();
	}
	
	
}
