package com.hq.thirdPartyServer.service.businessCard.bs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.hq.thirdPartyServer.service.businessCard.apiData.BusinessCardForm;
import com.hq.thirdPartyServer.service.businessCard.data.BusinessCardResp;
import com.hq.thirdPartyServer.service.businessCard.data.SyncApiClientBusinessCard;
import com.hq.thirdPartyServer.service.common.Image;
import com.hq.thirdPartyServer.service.common.Input;
import com.hq.thirdPartyServer.service.common.ReqResult;
import com.hq.thirdPartyServer.service.common.RequestEntity;
import com.hq.thirdPartyServer.service.common.RespStatus;
import com.hq.thirdPartyServer.service.common.ResponesEntity;
import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfgMgr;
import com.hq.thirdPartyServer.common.log.LogModule;
import com.hq.thirdPartyServer.common.log.MainLog;
import com.hq.thirdPartyServer.common.util.Base64ImageUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class BusinessCardMgr {

	public static BusinessCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BusinessCardMgr.class);
	}
	
	public ReqResult<BusinessCardResp> readIdCardInfo(MultipartFile img){
		ReqResult<BusinessCardResp> result = ReqResult.newInstance(false, BusinessCardResp.class);
		try {
			BusinessCardForm apiForm = BusinessCardForm.newInstance();
			apiForm.setImageBinary(Base64ImageUtil.getInstance().getImageStrByInputStream(img.getInputStream()));
			BusinessCardResp businessCardResp=read(apiForm);
			if(businessCardResp != null){
				result.setTarget(businessCardResp);
				result.setSuccess(true);
			}else{
				result.setTips("识别名片信息失败");
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.BusinessCard, "BusinessCardMgr[readIdCardInfo]", "", e);
		}
		return result;
	}
	
	
	public ReqResult<BusinessCardResp> readCardInfoByBase64(BusinessCardForm apiForm){
		ReqResult<BusinessCardResp> result = ReqResult.newInstance(false, BusinessCardResp.class);
		try {
			BusinessCardResp businessCardResp=read(apiForm);
			if(businessCardResp != null){
				result.setTarget(businessCardResp);
				result.setSuccess(true);
			}else{
				result.setTips("识别名片信息失败");
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.BusinessCard, "BusinessCardMgr[readCardInfoByBase64]", "", e);
		}
        return result;
	}
	
	private BusinessCardResp read(BusinessCardForm apiForm){
		BusinessCardResp businessCardResp = BusinessCardResp.newInstance();
		try {
			SyncApiClientBusinessCard clientIdCard = SyncApiClientBusinessCard.newBuilder()
					.appKey(ThirdPartyServerCfgMgr.getProp().getAppKey())
					.appSecret(ThirdPartyServerCfgMgr.getProp().getAppSecret())
					.build();
		
			Image image = Image.newInstance(apiForm.getImageBinary());
			Input input = Input.newInstance(image, null);
			RequestEntity body= RequestEntity.newInstance(input);
		
			ApiResponse apiResponse = clientIdCard.analyze(JsonUtil.getInstance().toJson(body).getBytes());
			businessCardResp.setSerialNumber(apiResponse.getHeaders().get("X-Ca-Request-Id"));
			System.out.println(apiResponse);
			if(apiResponse.getStatusCode() == 200){
				String responseBody=new String(apiResponse.getBody(), "utf-8");
			    ResponesEntity entity = JsonUtil.getInstance().fromJson(responseBody, ResponesEntity.class);
			    if(entity != null){
					String resultStr = entity.getOutputs().get(0).getOutputValue().getDataValue();
					if(StringUtils.isNoneBlank(resultStr)){
						businessCardResp = JsonUtil.getInstance().fromJson(resultStr, BusinessCardResp.class);
					}
				}else{
					MainLog.error(LogModule.BusinessCard, "BusinessCardMgr[read]", apiResponse.toString(), null);
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.BusinessCard, "BusinessCardMgr[read]", "", e);
		}
		return businessCardResp;
	}

}
