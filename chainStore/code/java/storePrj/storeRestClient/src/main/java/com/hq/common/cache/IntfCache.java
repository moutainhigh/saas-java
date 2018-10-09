package com.hq.common.cache;

public interface IntfCache {

	
	public void put(String cacheName, String key, Object value);
	
	public Object get(String cacheName, String key);
}
