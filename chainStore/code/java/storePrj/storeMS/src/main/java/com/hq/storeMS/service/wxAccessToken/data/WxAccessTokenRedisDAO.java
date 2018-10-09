package com.hq.storeMS.service.wxAccessToken.data;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.redis.RedisTemplateMgr;

public class WxAccessTokenRedisDAO{

	public static WxAccessTokenRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WxAccessTokenRedisDAO.class);
	}

	final private String groupName="wxAccessToken";
	
	public void save2Redis(WxAccessToken target) {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return;
		}
		long time = (2 * 60 * 60L) - (30 * 60L); //过期时间为: 2小时减去30分钟
		RedisTemplateMgr.getInstance().getTemplate()
			.opsForValue().set(getKey(target.getAppId()), JsonUtil.getInstance().toJson(target), time, TimeUnit.SECONDS);
	}

	public WxAccessToken findByAppId(String appId) {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return null;
		}
		String result = RedisTemplateMgr.getInstance().getTemplate().opsForValue().get(getKey(appId));
		WxAccessToken target = null;
		if(StringUtils.isNoneBlank(result)){
			target = JsonUtil.getInstance().fromJson(result, WxAccessToken.class);
		}
		return target;
	}
	
	public void deleteByAppId(String appId){
		RedisTemplateMgr.getInstance().getTemplate().delete(getKey(appId));
	}
	
	private String getKey(String appId){
		return StringFormatUtil.format("{}_{}", groupName, appId);
	}
	
}
