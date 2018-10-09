package com.hq.storeManagerRestClient.testClass;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeManagerRestClient.common.cache.IntfCache;

public class CacheMgr4Test implements IntfCache{

	private Map<String,Map<String,Object>> cacheMap = new HashMap<String,Map<String,Object>>();

	@Override
	public void put(String cacheName, String key, Object value) {
		Map<String, Object> map = getMap(cacheName);
		map.put(key, value);
		
	}

	private Map<String, Object> getMap(String cacheName) {
		Map<String, Object> map = cacheMap.get(cacheName);
		if(map == null){
			map = new HashMap<String, Object>();
			cacheMap.put(cacheName, map);
		}
		return map;
	}

	@Override
	public Object get(String cacheName, String key) {
		Map<String, Object> map = getMap(cacheName);
		return map.get(key);
	}
	
	
	
	
}
