package com.hq.thirdPartyServer.service.sms.bs.yunpian.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfgMgr;
import com.hq.thirdPartyServer.common.util.AppUtils;
import com.hq.thirdPartyServer.service.sms.apiData.SmsAPIForm;
import com.hq.thirdPartyServer.service.sms.apiData.SmsTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class YpSmsMgrHelper {

	public static YpSmsMgrHelper getInstance() {
		return HotSwap.getInstance().getSingleton(YpSmsMgrHelper.class);
	}
	
	private final String symbol = "+";
	
	private Map<SmsTypeEnum, YpSmsMgrTemplate> handleMapper = new HashMap<SmsTypeEnum, YpSmsMgrTemplate>();

	public YpSmsMgrHelper() {
		handleMapper.put(SmsTypeEnum.B_VERIFICATION_CODE, new YpSmsMgrTemplate(){
			@Override
			public String sendSms(Map<String, String> map, List<String> phones, String apikey) throws Exception {
				return ZmtSendSmsCode.getInstance().sendSms(map, phones, apikey);
			}
		});
		
//		handleMapper.put(SmsTypeEnum.C_VERIFICATION_CODE, new YpSmsMgrTemplate(){
//			@Override
//			public String sendSms(Map<String, String> map, List<String> phones, String apikey) throws Exception {
//				return ZmkSendSmsCode.getInstance().sendSms(map, phones, apikey);
//			}
//		});
		
		handleMapper.put(SmsTypeEnum.C_VERIFICATION_CODE, new YpSmsMgrTemplate(){
			@Override
			public String sendSms(Map<String, String> map, List<String> phones, String apikey) throws Exception {
				return ZmyySendSmsCode.getInstance().sendSms(map, phones, apikey);
			}
		});
	}

	public String sendSms(SmsAPIForm apiForm) throws Exception {
		String jsonRes = null;
		String apikey = ThirdPartyServerCfgMgr.getProp().getApikey();
		Map<String,String> map = JsonUtil.getInstance().parseMap(apiForm.getContent());
		SmsTypeEnum smsType = SmsTypeEnum.valueOf(apiForm.getSmsType());
		List<String> phones = getPhoneNumbers(apiForm);
		
		YpSmsMgrTemplate template = handleMapper.get(smsType);
		if (template!=null) {
			jsonRes=template.sendSms(map, phones, apikey);
		}
		return jsonRes;
	}
	
	private List<String> getPhoneNumbers(SmsAPIForm apiForm) throws Exception{
		List<String> phoneList = new ArrayList<String>();
		String phoneStr = apiForm.getPhoneNumbers();
		String[] phones = phoneStr.split(",");
		
		for (String phone : phones) {
			if(phone.startsWith(symbol)) {//如果号码已经是带有区号的 则直接使用
				phoneList.add(phone);
			}else if(AppUtils.isChinaPhoneLegal(phone)){
				phoneList.add(phone);
			}else if(AppUtils.isHKPhoneLegal(phone)){
				phoneList.add(encodePhone("852"+phone));
			}else if(AppUtils.isMOPhoneLegal(phone)){
				phoneList.add(encodePhone("853"+phone));
			}else if(AppUtils.isTWPhoneLegal(phone)){
				phoneList.add(encodePhone("886"+phone));
			}
		}
		return phoneList;
	}
	
	private String encodePhone(String phone) throws Exception{
		return symbol+phone;
	}
}
