package com.hq.storeMS.service.wxJsApiTicket.bs;

import com.hq.storeMS.service.wxAccessToken.bs.WxAccessTokenMgr;
import com.hq.storeMS.service.wxAccessToken.data.WxAccessToken;
import com.hq.storeMS.service.wxJsApiTicket.data.WxJsApiTicket;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.GetJsApiTicketRequestParams;
import com.zenmind.wx.GetJsApiTicketResponse;
import com.zenmind.wx.WxProxy;

public class WxJsApiTicketMgr {
	
	public static WxJsApiTicketMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WxJsApiTicketMgr.class);
	}
	
	public WxJsApiTicket add(WxJsApiTicket target) {
		WxJsApiTicketDataHolder.getInstance().add(target);
		return target;
	}
	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(WxJsApiTicket target) {
		WxJsApiTicketDataHolder.getInstance().update(target);
	}
	
	public WxJsApiTicket findByAppId(String appId) {
		WxJsApiTicket target = WxJsApiTicketDataHolder.getInstance().findByAppId(appId);
		if(target == null) {
			target = getNewWxJsApiTicketFromWx(appId);
		}
		return target;
	}
	
	/**
	 * 从微信获取新的WxJsApiTicket
	 * @param appId
	 */
	private WxJsApiTicket getNewWxJsApiTicketFromWx(String appId) {
		WxJsApiTicket target = null;
		WxAccessToken wxAccessToken = WxAccessTokenMgr.getInstance().getWxAccessToken(appId);
		if(wxAccessToken != null) {
			GetJsApiTicketRequestParams params = GetJsApiTicketRequestParams.newInstance(wxAccessToken.getAccess_token());
			GetJsApiTicketResponse jsApiTicketResponse = WxProxy.getInstance().getJsApiTicket(params);
			if(jsApiTicketResponse != null) {
				target = WxJsApiTicket.newInstance();
				target.setJsapiTicket(jsApiTicketResponse.getTicket());
				target.setAppId(appId);
				WxJsApiTicketDataHolder.getInstance().add(target);
			}
		}
		return target;
	}
	
}
