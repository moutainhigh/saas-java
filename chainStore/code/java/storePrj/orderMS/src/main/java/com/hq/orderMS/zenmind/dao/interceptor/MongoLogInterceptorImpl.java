package com.hq.orderMS.zenmind.dao.interceptor;

import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.mongodb.InterceptorAftParams;
import com.zenmind.dao.mongodb.InterceptorPreParams;
import com.zenmind.dao.mongodb.interceptor.IntfMongoInterceptor;

public class MongoLogInterceptorImpl implements IntfMongoInterceptor{

	public static MongoLogInterceptorImpl newInstance() {
		MongoLogInterceptorImpl tmp = new MongoLogInterceptorImpl();
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
