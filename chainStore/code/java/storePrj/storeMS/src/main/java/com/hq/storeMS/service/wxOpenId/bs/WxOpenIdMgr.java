package com.hq.storeMS.service.wxOpenId.bs;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.service.wxOpenId.data.WxOpenId;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.GetOpenIdParams;
import com.zenmind.wx.WxProxy;

public class WxOpenIdMgr {
	
	public static WxOpenIdMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxOpenIdMgr.class);
	}
	
	public WxOpenId genOpenId(String appId, String jsCode) {
		WxOpenId target = WxOpenIdDataHolder.getInstance().findByKey(appId, jsCode);
		if(target == null) {
			target = getNewOpenIdFromWx(appId, jsCode);
		}
		return target;
	}
	
	/**
	 * 从微信获取新的WxOpenId
	 * @param appId
	 * @param jsCode
	 * @return
	 */
	private WxOpenId getNewOpenIdFromWx(String appId, String jsCode) {
		WxOpenId target = WxOpenId.newInstance();
		GetOpenIdParams goiParams = buildGetOpenIdParams(appId, jsCode);
		String openId = WxProxy.getInstance().getOpenId(goiParams);
		target.setAppId(appId);
		target.setJsCode(jsCode);
		target.setOpenId(openId);
		WxOpenIdDataHolder.getInstance().add(target);
		return target;
	}
	
	private GetOpenIdParams buildGetOpenIdParams(String appId, String jsCode) {
		final StoreMSCfg cfg = StoreMSCfgMgr.getProp();
		String secret = "";
		//小程序的appId和主体公众号的appId分别对应不同的openid，目前只用到了小程序的appId
		if (cfg.getWxAppId().equals(appId)) {
		    secret = cfg.getWxSecret();
		} else if (cfg.getWxMainAppId().equals(appId)) {
			secret = cfg.getWxMainSecret();
		} else {
			//后续如果有定制化小程序的需求，秘钥要从数据库动态查询
		}
	    return GetOpenIdParams.newInstance(appId, secret, jsCode);
	}
}
