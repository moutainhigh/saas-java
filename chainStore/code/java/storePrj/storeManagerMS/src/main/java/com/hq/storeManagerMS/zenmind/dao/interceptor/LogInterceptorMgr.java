package com.hq.storeManagerMS.zenmind.dao.interceptor;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.interceptor.MongoInterceptor;
import com.zenmind.dao.redis.interceptor.RedisInterceptor;

public class LogInterceptorMgr {

	public static LogInterceptorMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LogInterceptorMgr.class);
	}
	
	//完成标识  保证只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);
		
	public void init(){
		if(isInit.compareAndSet(false, true)){
			MongoInterceptor.getInstance().add(MongoLogInterceptorImpl.newInstance());
			RedisInterceptor.getInstance().add(RedisLogInterceptorImpl.newInstance());
		}
	}
	
	private boolean redisOpen = false;
	private long redisTimeMillis = 0;

	private boolean mongoOpen = false;
	private long mongoTimeMillis = 0;
	
	public void reset(LogInterceptorConfig config){
		try {
			this.redisOpen = config.isRedisOpen();
			this.redisTimeMillis = config.getRedisTimeMillis();
			
			this.mongoOpen = config.isMongoOpen();
			this.mongoTimeMillis = config.getMongoTimeMillis();
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "LogInterceptorMgr[reset]", "", e);
		}
	}
	
	public boolean isRedisOpen() {
		return redisOpen;
	}

	public void setRedisOpen(boolean redisOpen) {
		this.redisOpen = redisOpen;
	}

	public long getRedisTimeMillis() {
		return redisTimeMillis;
	}

	public void setRedisTimeMillis(long redisTimeMillis) {
		this.redisTimeMillis = redisTimeMillis;
	}

	public boolean isMongoOpen() {
		return mongoOpen;
	}

	public void setMongoOpen(boolean mongoOpen) {
		this.mongoOpen = mongoOpen;
	}

	public long getMongoTimeMillis() {
		return mongoTimeMillis;
	}

	public void setMongoTimeMillis(long mongoTimeMillis) {
		this.mongoTimeMillis = mongoTimeMillis;
	}

}
