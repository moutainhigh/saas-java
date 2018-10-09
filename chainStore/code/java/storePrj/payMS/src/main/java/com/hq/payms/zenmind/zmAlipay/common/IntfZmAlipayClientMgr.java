package com.hq.payms.zenmind.zmAlipay.common;

import com.alipay.api.AlipayClient;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;

public interface IntfZmAlipayClientMgr {
	
	public AlipayClient getClient(ZmAlipayAppParam appParam);
	
}
