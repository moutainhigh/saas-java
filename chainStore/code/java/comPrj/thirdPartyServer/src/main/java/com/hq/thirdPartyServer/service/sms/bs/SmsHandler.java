package com.hq.thirdPartyServer.service.sms.bs;

import java.util.Map;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfgMgr;
import com.hq.thirdPartyServer.common.log.LogModule;
import com.hq.thirdPartyServer.common.log.MainLog;
import com.hq.thirdPartyServer.service.common.ReqResult;
import com.hq.thirdPartyServer.service.common.RespStatus;
import com.hq.thirdPartyServer.service.sms.apiData.SmsAPIForm;
import com.hq.thirdPartyServer.service.sms.bs.aliyun.AliSmsMgr;
import com.hq.thirdPartyServer.service.sms.bs.yunpian.YpSmsMgr;
import com.hq.thirdPartyServer.service.sms.data.SmsResp;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class SmsHandler {

	public static SmsHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SmsHandler.class);
	}
	
	public ReqResult<SmsResp> sendSms(SmsAPIForm apiForm){
		if(ThirdPartyServerCfgMgr.getProp().getSmsSwitch() == 1){
			return sendSmsByAliYun(apiForm);
		}else if(ThirdPartyServerCfgMgr.getProp().getSmsSwitch() == 2){
			return sendSmsByYunPian(apiForm);
		}else{
			return sendSmsByAliYun(apiForm);
		}
	}
	
	public ReqResult<SmsResp> sendSmsByAliYun(SmsAPIForm apiForm){
		ReqResult<SmsResp> result = ReqResult.newInstance(false, SmsResp.class);
		try {
			ApiResponse response = AliSmsMgr.getInstance().sendSms(apiForm);
			SmsResp smsResp = SmsResp.newInstance();
			smsResp.setCode(response.getStatusCode());
			smsResp.setMessage(response.getMessage());
			smsResp.setSerialNumber(response.getHeaders().get("X-Ca-Request-Id"));
			result.setTarget(smsResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.SmsServer, "SmsMgr[sendSms]", "", e);
		}
        return result;
	}
	
	public ReqResult<SmsResp> sendSmsByYunPian(SmsAPIForm apiForm){
		ReqResult<SmsResp> result = ReqResult.newInstance(false, SmsResp.class);
		try {
			String jsonRes = YpSmsMgr.getInstance().sendSms(apiForm);
			Map<String,String> resMap = JsonUtil.getInstance().parseMap(jsonRes);
			SmsResp smsResp = SmsResp.newInstance();
			smsResp.setCode(Integer.valueOf(resMap.get("code")));
			smsResp.setMessage(resMap.get("msg"));
			smsResp.setSerialNumber(resMap.get("sid"));
			result.setTarget(smsResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.SmsServer, "SmsMgr[sendSmsByYunPian]", "", e);
		}
		return result;
	}
}
