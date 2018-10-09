package com.hq.thirdPartyServer.service.sms.bs.yunpian.helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfgMgr;
import com.hq.thirdPartyServer.service.sms.data.YunPianSendSms;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 智美通 发短信验证码
 * @author kevin
 *
 */
public class ZmtSendSmsCode extends YpSmsMgrTemplate{
	public static ZmtSendSmsCode getInstance() {
		return HotSwap.getInstance().getSingleton(ZmtSendSmsCode.class);
	}

	@Override
	public String sendSms(Map<String, String> map, List<String> phones, String apikey) throws Exception {
		if(StringUtils.isBlank(map.get("time"))){//兼容以前的短信模板
			map.put("time", "10");
		}
		String phone = phones.get(0);
		String text = "";
		if(super.isChinaPhone(phone)) {
			text = replaceTemplate(ThirdPartyServerCfgMgr.getProp().getZmtCodeTemplate(), map);
		}else if(super.isGatPhone(phone)){//港澳台短信
			text = replaceTemplate(ThirdPartyServerCfgMgr.getProp().getZmtGatCodeTemplate(), map);
		}else if(phone.startsWith(symbol)){//国际短信
			text = replaceTemplate(ThirdPartyServerCfgMgr.getProp().getZmtIntlCodeTemplate(), map);
		}else{//国内短信
			text = replaceTemplate(ThirdPartyServerCfgMgr.getProp().getZmtCodeTemplate(), map);
		}
		return YunPianSendSms.getInstance().sendSms(apikey, text, phone);
	}

}
