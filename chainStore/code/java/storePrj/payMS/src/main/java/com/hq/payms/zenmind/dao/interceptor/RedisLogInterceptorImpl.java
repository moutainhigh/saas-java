package com.hq.payms.zenmind.dao.interceptor;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.redis.InterceptorAftParams;
import com.zenmind.dao.redis.InterceptorPreParams;
import com.zenmind.dao.redis.interceptor.IntfRedisInterceptor;


public class RedisLogInterceptorImpl implements IntfRedisInterceptor{

	public static RedisLogInterceptorImpl newInstance() {
		RedisLogInterceptorImpl tmp = new RedisLogInterceptorImpl();
		return tmp;
	}
	
	final static private String idFormat = "tableName:{}, method:{}";
	final static private String infoFormat = "expenditure {} millisecond";
	
	@Override
	public void preHandle(InterceptorPreParams interceptorPreParams) {
	}

	@Override
	public void aftHandle(InterceptorAftParams interceptorAftParams) {
		if(LogInterceptorMgr.getInstance().isRedisOpen()){
			ClassInfo classInfoPojo = interceptorAftParams.getClassInfoPojo();
			String methodName = interceptorAftParams.getMethodName();
			long startTimeMillis = interceptorAftParams.getStartTimeMillis();
			long endTimeMillis = System.currentTimeMillis();
			//超过500毫秒
			long val = endTimeMillis - startTimeMillis;
			if(val > LogInterceptorMgr.getInstance().getRedisTimeMillis()){
				MainLog.warn(
					LogModule.RedisDao, 
					StringFormatUtil.format(idFormat, classInfoPojo.getTableName(), methodName), 
					StringFormatUtil.format(infoFormat, val));
			}
		}
	}

}
