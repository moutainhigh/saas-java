package com.zenmind.base;

public class StringFormatUtil {
	public static String format(String format, Object... args){
		//example "this is an {} for use." example,{} 是占位符
		String target = format;
		for (Object replaceTmp : args) {
			target = target.replaceFirst("\\{\\}",String.valueOf(replaceTmp));
		}
		return target;
	}
	
	
}
