package com.hq.storeManagerMS.zenmind.dao.interceptor;

import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.redis.InterceptorAftParams;
import com.zenmind.dao.redis.InterceptorPreParams;
import com.zenmind.dao.redis.interceptor.IntfRedisInterceptor;


public class RedisLogInterceptorImpl implements IntfRedisInterceptor{

	public static RedisLogInterceptorImpl newInstance() {
		RedisLogInterceptorImpl tmp = new RedisLogInterceptorImpl();
		return tmp;
	}
	
	@Override
	public void preHandle(InterceptorPreParams interceptorPreParams) {
	}

	@Override
	public void aftHandle(InterceptorAftParams interceptorAftParams) {
		ClassInfo classInfoPojo = interceptorAftParams.getClassInfoPojo();
		String methodName = interceptorAftParams.getMethodName();
		long startTimeMillis = interceptorAftParams.getStartTimeMillis();
		long endTimeMillis = System.currentTimeMillis();
		//超过500毫秒
		long val = endTimeMillis - startTimeMillis;
		if(val > 500){
			MainLog.warn(LogModule.MongoDao, classInfoPojo.getTableName()+"["+methodName+"]", "耗时："+val+"毫秒");
		}
	}

}
