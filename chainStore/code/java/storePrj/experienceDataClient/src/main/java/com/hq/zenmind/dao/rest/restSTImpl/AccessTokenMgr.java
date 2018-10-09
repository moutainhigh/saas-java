package com.hq.zenmind.dao.rest.restSTImpl;

import com.hq.common.cache.RestCacheMgr;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDaoThreadLocal;

public class AccessTokenMgr {
	
	public static AccessTokenMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AccessTokenMgr.class);
	}
	
	final private String cacheName = "accessTokenCache";
	
	final private ThreadLocal<Long> opIdTL = new ThreadLocal<Long>();

	public String getToken(){
		Long buserId = opIdTL.get();
		if(buserId!=null){
			return RestCacheMgr.getInstance().get(cacheName, buserId.toString());
		}else{
			return null;
		}
	}
	
	public void putToken(Long buserId, String token){
		RestCacheMgr.getInstance().put(cacheName, buserId.toString(), token);
	}
	
	public void setOpIdTL(Long buserId){
		RestDaoThreadLocal.getInstance().setOwnerId(String.valueOf(buserId));
		opIdTL.set(buserId);
	}
	public void removeOpIdTL(){
		RestDaoThreadLocal.getInstance().remove();
		opIdTL.remove();
	}
	
	
	
}
