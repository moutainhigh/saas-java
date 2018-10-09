package com.hq.payms.zenmind.zmAlipay.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.alipay.api.AlipayConstants;
import com.alipay.api.AlipayResponse;

public class ZmAlipayLogHelper {

	public static String getLogInfo(Map<String, Object> rt, AlipayResponse tRsp) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(AlipayConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorScene");
        sb.append("^_^");
        sb.append(tRsp.getErrorCode());
        sb.append("^_^");
        sb.append(tRsp.getSubCode());
        sb.append("^_^");
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append("ProtocalMustParams:");
        sb.append(rt.get("protocalMustParams"));
        sb.append("^_^");
        sb.append("ProtocalOptParams:");
        sb.append(rt.get("protocalOptParams"));
        sb.append("^_^");
        sb.append("ApplicationParams:");
        sb.append(rt.get("textParams"));
        sb.append("^_^");
        sb.append("Body:");
        sb.append((String) rt.get("rsp"));
        return sb.toString();
	}
}
