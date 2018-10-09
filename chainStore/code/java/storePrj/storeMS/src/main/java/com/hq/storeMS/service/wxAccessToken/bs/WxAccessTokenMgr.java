package com.hq.storeMS.service.wxAccessToken.bs;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.service.wxAccessToken.data.WxAccessToken;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.TokenRequestParams;
import com.zenmind.wx.TokenResponse;
import com.zenmind.wx.WxProxy;

public class WxAccessTokenMgr {
	
	public static WxAccessTokenMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxAccessTokenMgr.class);
	}
	
	public WxAccessToken add(WxAccessToken target) {
		WxAccessTokenDataHolder.getInstance().add(target);
		return target;
	}
	
	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(WxAccessToken target) {
		WxAccessTokenDataHolder.getInstance().update(target);
	}
	
	public WxAccessToken getWxAccessToken(String appId) {
		WxAccessToken target = WxAccessTokenDataHolder.getInstance().findByAppId(appId);
		if(target == null) {
			target = getNewWxAccessTokenFromWx(appId);
		}
		return target;
	}
	
	/**
	 * 从微信获取新的WxAccessToken
	 * @param appId
	 * @return
	 */
	private WxAccessToken getNewWxAccessTokenFromWx(String appId) {
		WxAccessToken target = null;
		TokenRequestParams tokenRequestParams = buildTokenRequestParams(appId);
		TokenResponse tokenResponse = WxProxy.getInstance().getAccessToken(tokenRequestParams);
		if(tokenResponse != null && StringUtils.isNotBlank(tokenResponse.getAccess_token())){
			target = WxAccessToken.newInstance();
			FastBeanCopyer.getInstance().copy(tokenResponse, target);
			target.setAppId(appId);
			WxAccessTokenDataHolder.getInstance().add(target);
		}
		
		return target;
	}
	
	private TokenRequestParams buildTokenRequestParams(String appId) {
		final StoreMSCfg cfg = StoreMSCfgMgr.getProp();
		String secret = "";
		//小程序的appId和主体公众号的appId分别对应不同的access_token，目前两种都用到了
		if (cfg.getWxAppId().equals(appId)) {
		    secret = cfg.getWxSecret();
		} else if (cfg.getWxMainAppId().equals(appId)) {
			secret = cfg.getWxMainSecret();
		} else {
			//后续如果有定制化小程序的需求，秘钥要从数据库动态查询
		}
	    return TokenRequestParams.newInstance(appId, secret);
	}

}
