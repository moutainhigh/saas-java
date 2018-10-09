package com.hq.orderMS.common.cache;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.zenmind.common.SpringContextUtil;
import com.zenmind.common.hotSwap.HotSwap;


public class CacheMgr {

	public static CacheMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CacheMgr.class);
	}
	
	public void put(String cacheName, String key, Object value){
		getCacheManager().getCache(cacheName).put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, String key){
		ValueWrapper valueWrapper = getCacheManager().getCache(cacheName).get(key);
		if(valueWrapper!=null){
			return (T)valueWrapper.get();
		}else{
			return null;
		}
	}
	
	private CacheManager getCacheManager(){
		return SpringContextUtil.getBean(CacheManager.class);
	}
	
}
