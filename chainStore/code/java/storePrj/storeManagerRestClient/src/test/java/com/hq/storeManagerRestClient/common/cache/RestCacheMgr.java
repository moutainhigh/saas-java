package com.hq.storeManagerRestClient.common.cache;

public class RestCacheMgr {
	private static RestCacheMgr cacheMgr = new RestCacheMgr();

	public static RestCacheMgr getInstance() {
		return cacheMgr;
	}

	private IntfCache cacher;

	public void init(IntfCache cacherP) {
		this.cacher = cacherP;
	}

	public void put(String cacheName, String key, Object value) {
		this.cacher.put(cacheName, key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, String key) {
		Object object = this.cacher.get(cacheName, key);
		if (object != null) {
			return (T) object;
		} else {
			return null;
		}
	}

}
