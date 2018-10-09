package com.hq.storeClient.testClass;

import com.hq.storeClient.common.cache.RestCacheMgr;
import com.hq.storeClient.common.validate.info.ValidateInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDaoThreadLocal;

public class AccessTokenMgr {
	
	public static AccessTokenMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AccessTokenMgr.class);
	}
	
	final private String cacheName = "accessTokenCache";
	
	final private String validateCacheName = "validateInfoCache";
	
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
	
	
	public ValidateInfo getValidateInfo(){
		Long buserId = opIdTL.get();
		if(buserId!=null){
			return RestCacheMgr.getInstance().get(validateCacheName, buserId.toString());
		}else{
			return null;
		}
	}
	
	public void putValidateInfo(Long buserId, ValidateInfo info){
		RestCacheMgr.getInstance().put(validateCacheName, buserId.toString(), info);
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
