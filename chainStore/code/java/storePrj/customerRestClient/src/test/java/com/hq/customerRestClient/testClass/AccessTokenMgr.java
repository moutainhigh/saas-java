package com.hq.customerRestClient.testClass;

import com.hq.customerRestClient.common.cache.RestCacheMgr;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDaoThreadLocal;

public class AccessTokenMgr {
	
	public static AccessTokenMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AccessTokenMgr.class);
	}
	
	final private String cacheName = "accessTokenCache";
	
	final private ThreadLocal<Long> opIdTL = new ThreadLocal<Long>();

	public String getToken(){
		Long userId = opIdTL.get();
		if(userId!=null){
			return RestCacheMgr.getInstance().get(cacheName, userId.toString());
		}else{
			return null;
		}
	}
	
	public void putToken(Long userId, String token){
		RestCacheMgr.getInstance().put(cacheName, userId.toString(), token);
	}
	
	public void setOpIdTL(Long userId){
		RestDaoThreadLocal.getInstance().setOwnerId(String.valueOf(userId));
		opIdTL.set(userId);
	}
	public void removeOpIdTL(){
		RestDaoThreadLocal.getInstance().remove();
		opIdTL.remove();
	}
	
	
	
}
