package com.hq.thirdPartyServer.service.sms.bs.yunpian.helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.thirdPartyServer.service.sms.data.AreaCodeEnum;

public abstract class YpSmsMgrTemplate {
	
	protected final String symbol = "+";
	
	/**
	 * 发短信
	 * @param map 模板里的变量 key:value
	 * @param phones 手机号码
	 * @param apikey 云片网的apikey
	 * @return
	 */
	public abstract String sendSms(Map<String,String> map, List<String> phones, String apikey) throws Exception;
	
	protected String replaceTemplate(String template, Map<String,String> map){
		String[] keys = StringUtils.substringsBetween(template, "#", "#");
		for (String key : keys) {
			template = StringUtils.replace(template, "#"+key+"#", map.get(key));
		}
		return template;
	}
	
	protected boolean isChinaPhone(String phone) {
		return phone.startsWith(AreaCodeEnum.China.getCode());
	}
	
	protected boolean isGatPhone(String phone) {
		return phone.startsWith(AreaCodeEnum.Hongkong.getCode()) || phone.startsWith(AreaCodeEnum.Macao.getCode()) || phone.startsWith(AreaCodeEnum.Taiwan.getCode());
	}
}
