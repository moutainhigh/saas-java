package com.hq.payms.common.log;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.zenmind.common.StringFormatUtil;


public class ConsoleLog {
	
	private static PrintStream console = System.out;

	public static String format(String format, Object... arguments) {
		return StringFormatUtil.format(format, arguments);
	}

	public static String join(String... arguments) {
		StringBuilder sb = new StringBuilder();
		for (String tmp : arguments) {
			sb.append(tmp);
		}
		return sb.toString();
	}
	
	public static String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return df.format(new Date());
	}
	
	private static String getPrefix(String logLevel) {
		return format("{}  {}		: ", getCurrentTime(), logLevel);
	}
	
	public static void info(LogModule logModule, String id, String info) {
		String content = format("{}|{}|{}|{}", 
				getPrefix("CONSOLE-INFO"),logModule.getName(), id, info);
		console.println(content);
	}
	
	public static void warn(LogModule logModule, String id, String info) {
		String content = format("{}|{}|{}|{}", 
				getPrefix("CONSOLE-WARN"), logModule.getName(), id, info);
		console.println(content);
	}

	public static void error(LogModule logModule, String id, String reason) {
		String content = format("{}|{}|{}|{}", 
				getPrefix("CONSOLE-ERROR"), logModule.getName(), id, reason);
		console.println(content);
	}

	public static void error(LogModule logModule, String id, String reason, Throwable throwable) {
		String content = format("{}|{}|{}|{}\n{}", 
				getPrefix("CONSOLE-ERROR"), logModule.getName(), id, reason, getStackTrace(throwable));
		console.println(content);
	}

	public static String getStackTrace(Throwable t) {
		Writer wr = new StringWriter();
		PrintWriter pWriter = new PrintWriter(wr);
		t.printStackTrace(pWriter);
		return wr.toString();
	}

	public static void main(String[] args) {
		ConsoleLog.info(LogModule.Tmp, "AlipayRespHandle[handleNotify]", "支付宝支付通知结果：" + "aaa");
		ConsoleLog.error(LogModule.Tmp, "AlipayRespHandle[handleNotify]", "支付宝支付通知结果" , new RuntimeException("error"));
	}
}
