package com.hq.storeMS.service.qrcode.bs;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.redis.RedisTemplateMgr;
import com.zenmind.wx.AcodeRequestParams;
import com.zenmind.wx.AcodeResponse;
import com.zenmind.wx.TokenResponse;
import com.zenmind.wx.WxProxy;

public class QrcodeDataHolder {
	public static QrcodeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(QrcodeDataHolder.class);
	}
	
	final private String groupName="qrcode";
	final private String groupId="wxAcode";
	
	public TokenResponse getTokenResponse(){
		TokenResponse tokenResponse = this.getFromRedis();
		if(tokenResponse == null){
			tokenResponse = WxProxy.getInstance().getAccessToken();
			if(tokenResponse == null || StringUtils.isBlank(tokenResponse.getAccess_token())){
				return null;
			}
			this.save2Redis(tokenResponse);
		}
		return tokenResponse;
	}
	
	public AcodeResponse getAcodeResponse(String accessToken, long storeId){
		AcodeRequestParams requestParams = AcodeRequestParams.newInstance();
		requestParams.setAuto_color(true);
		requestParams.setPage(StoreMSCfgMgr.getProp().getWxAcodePage());
		requestParams.setScene(String.valueOf(storeId));
		return WxProxy.getInstance().getWxAcodeunlimit(accessToken, requestParams);
	}
	
	//==========================RedisDao==========================
	private void save2Redis(TokenResponse target) {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return;
		}
		long time = (long)target.getExpires_in()/2;
		RedisTemplateMgr.getInstance().getTemplate().opsForValue().set(getKey(groupId), JsonUtil.getInstance().toJson(target), time, TimeUnit.SECONDS);
	}

	private TokenResponse getFromRedis() {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return null;
		}
		String result = RedisTemplateMgr.getInstance().getTemplate().opsForValue().get(getKey(groupId));
		TokenResponse target = null;
		if(StringUtils.isNoneBlank(result)){
			target = JsonUtil.getInstance().fromJson(result, TokenResponse.class);
		}
		return target;
	}
	
	private String getKey(String phone){
		return StringFormatUtil.format("{}_{}", groupName, phone);
	}
}
