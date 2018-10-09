package com.hq.storeMS.service.wxAccessToken.bs;

import com.hq.storeMS.service.wxAccessToken.data.WxAccessToken;
import com.hq.storeMS.service.wxAccessToken.data.WxAccessTokenRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WxAccessTokenDataHolder {

	public static WxAccessTokenDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxAccessTokenDataHolder.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void add(WxAccessToken target) {
		WxAccessTokenRedisDAO.getInstance().save2Redis(target);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(WxAccessToken target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		WxAccessTokenRedisDAO.getInstance().deleteByAppId(target.getAppId());
		WxAccessTokenRedisDAO.getInstance().save2Redis(target);
	}
	
	public WxAccessToken findByAppId(String appId) {
		WxAccessToken target = WxAccessTokenRedisDAO.getInstance().findByAppId(appId);
		return target;
	}
}
