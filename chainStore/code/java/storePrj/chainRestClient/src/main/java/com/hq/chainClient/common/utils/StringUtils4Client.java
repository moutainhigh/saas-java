package com.hq.chainClient.common.utils;

import java.util.Collection;

public class StringUtils4Client {

	public static boolean isBlank(String input){
		return !isNotBlank(input);
	}
	
	public static boolean isNotBlank(String input){
		return input!=null && input.trim()!="";
	}
	
	public static String join(final Collection<?> collection, final String separator){
		if (collection == null || collection.isEmpty()) {
            return null;
        }
		StringBuilder sb = new StringBuilder();
		for (Object obj : collection) {
			sb.append(String.valueOf(obj)).append(separator);
		}
		String s = sb.toString();
		return s.substring(0, s.length() - separator.length());
	}

	public static String format(String format, Object... args){
		//example "this is an {} for use." example,{} 是占位符
		String target = format;
		for (Object replaceTmp : args) {
			target = target.replaceFirst("\\{\\}",String.valueOf(replaceTmp));
		}
		return target;
	}
	
	
}
