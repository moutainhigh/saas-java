package com.hq.storeMS.service.wxJsApiTicket.bs;

import com.hq.storeMS.service.wxJsApiTicket.data.WxJsApiTicket;
import com.hq.storeMS.service.wxJsApiTicket.data.WxJsApiTicketRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WxJsApiTicketDataHolder{
	
	public static WxJsApiTicketDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxJsApiTicketDataHolder.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void add(WxJsApiTicket target) {
		WxJsApiTicketRedisDAO.getInstance().save2Redis(target);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(WxJsApiTicket target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		WxJsApiTicketRedisDAO.getInstance().deleteByAppId(target.getAppId());
		WxJsApiTicketRedisDAO.getInstance().save2Redis(target);
	}
	
	public WxJsApiTicket findByAppId(String appId) {
		return WxJsApiTicketRedisDAO.getInstance().findByAppId(appId);
	}
}