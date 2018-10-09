package com.hq.thirdPartyServer.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AppUtils {
	/**
	 * 大陆号码或香港号码均可
	 */
	public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
		return isChinaPhoneLegal(str) || isHKPhoneLegal(str) || isTWPhoneLegal(str) || isMOPhoneLegal(str);
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 此方法中前三位格式有：
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+任意数
	 * 17+除9的任意数
	 * 147
	 */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^[1][1-9]\\d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 台湾手机号码10位数，09开头+8位任意数
	 */
	public static boolean isTWPhoneLegal(String str)throws PatternSyntaxException {
		String regExp = "^(09)\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 澳门手机号码7位数，66或者68开头+5位任意数
	 */
	public static boolean isMOPhoneLegal(String str)throws PatternSyntaxException {
		String regExp = "^(68|66)\\d{5}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 澳大利亚手机号码9位数，4开头+8位任意数
	 */
	public static boolean isAUPhoneLegal(String str)throws PatternSyntaxException {
		String regExp = "^(4)\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 北美手机号码10位数
	 */
	public static boolean isNorthAmericaPhoneLegal(String str)throws PatternSyntaxException {
		String regExp = "\\d{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	
	public static void main(String[] args) throws Exception {
//		DateFormat df = new SimpleDateFormat("yyyy-MM");
//		Date now = df.parse("2017-11");
//		Date before = df.parse("2017-09");
//		System.out.println(diffTimeMonth(now, before));
		System.out.println(isNorthAmericaPhoneLegal("0485325968"));
	}
	
}
