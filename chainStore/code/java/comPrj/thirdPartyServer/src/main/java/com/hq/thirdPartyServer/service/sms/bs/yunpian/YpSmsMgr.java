package com.hq.thirdPartyServer.service.sms.bs.yunpian;

import com.hq.thirdPartyServer.service.sms.apiData.SmsAPIForm;
import com.hq.thirdPartyServer.service.sms.bs.yunpian.helper.YpSmsMgrHelper;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 邀请码短信
 * @author Administrator
 *
 */
public class YpSmsMgr {

	public static YpSmsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(YpSmsMgr.class);
	}
	
	public String sendSms(SmsAPIForm apiForm) throws Exception{
		return YpSmsMgrHelper.getInstance().sendSms(apiForm);
	}
}
