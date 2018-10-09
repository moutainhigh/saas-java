package com.hq.storeMS.service.wxOpenId.bs;

import com.hq.storeMS.service.wxOpenId.data.WxOpenId;
import com.hq.storeMS.service.wxOpenId.data.WxOpenIdRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WxOpenIdDataHolder {
	
	public static WxOpenIdDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WxOpenIdDataHolder.class);
	}
	
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void add(WxOpenId target) {
		WxOpenIdRedisDAO.getInstance().save2Redis(target);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(WxOpenId target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		WxOpenIdRedisDAO.getInstance().deleteByKey(target.getAppId(),target.getJsCode());
		WxOpenIdRedisDAO.getInstance().save2Redis(target);
	}
	
	
	public WxOpenId findByKey(String appId, String jsCode) {
		return WxOpenIdRedisDAO.getInstance().findByKey(appId, jsCode);
	}
	
	
	
}
