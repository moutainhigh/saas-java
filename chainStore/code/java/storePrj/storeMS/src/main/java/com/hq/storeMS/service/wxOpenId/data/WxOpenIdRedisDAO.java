package com.hq.storeMS.service.wxOpenId.data;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.redis.RedisTemplateMgr;

public class WxOpenIdRedisDAO{
	public static WxOpenIdRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WxOpenIdRedisDAO.class);
	}
	
	final private String groupName="wxOpenIdJsCode";
	
	public void save2Redis(WxOpenId target) {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return;
		}
		//过期时间为: 1天; 每次重新进入小程序时都会产生一个新jsCode，一个jsCode对应一个openid，所以存1天比较合适
		long time = 24 * 60 * 60L; 
		RedisTemplateMgr.getInstance().getTemplate().opsForValue().set(getKey(target.getAppId(), target.getJsCode()), 
				JsonUtil.getInstance().toJson(target), time, TimeUnit.SECONDS);
	}

	public WxOpenId findByKey(String appId,String jsCode) {
		if(!RedisTemplateMgr.getInstance().isOpen()){
			return null;
		}
		String result = RedisTemplateMgr.getInstance().getTemplate().opsForValue().get(getKey(appId, jsCode));
		WxOpenId target = null;
		if(StringUtils.isNoneBlank(result)){
			target = JsonUtil.getInstance().fromJson(result, WxOpenId.class);
		}
		return target;
	}
	
	public void deleteByKey(String appId,String jsCode){
		RedisTemplateMgr.getInstance().getTemplate().delete(getKey(appId, jsCode));
	}
	
	private String getKey(String appId, String jsCode){
		return StringFormatUtil.format("{}_{}_{}", groupName, appId, jsCode);
	}
}
