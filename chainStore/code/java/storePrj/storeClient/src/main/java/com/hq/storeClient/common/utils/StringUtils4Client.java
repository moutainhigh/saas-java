package com.hq.storeClient.common.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class StringUtils4Client {

	private static final String UnderLine = "_";
	private static final SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy/MM/dd");

	public static boolean isBlank(String input){
		return !isNotBlank(input);
	}
	
	public static boolean isNotBlank(String input){
		return input!=null && input.trim()!="";
	}
	
	public static String join(final Collection<?> cols, final String separator){
		if (cols == null || cols.isEmpty()) {
            return null;
        }
		StringBuilder sb = new StringBuilder();
		for (Object obj : cols) {
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

	public static String joinByUnderline(Object... args) {
		StringBuffer target = new StringBuffer();
		boolean flag = true;
		for (Object tmp : args) {
			if (null == tmp || isBlank(String.valueOf(tmp))) {
				continue;
			}
			if (flag) {
				target.append(tmp);
				flag = false;
			} else {
				target.append(UnderLine).append(tmp);
			}
		}
		return target.toString();
	}

	/**
	 * 时间戳转日期字符串
	 *
	 * @param time
	 * @return
	 */
	public static String timeStamp2Str(long time) {
		try {
			return SDF_DAY.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


}
