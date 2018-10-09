package com.hq.orderMS.zenmind.dao.interceptor;

public class LogInterceptorConfig {
	private boolean redisOpen = false;
	private long redisTimeMillis = 0;

	private boolean mongoOpen = false;
	private long mongoTimeMillis = 0;

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
