package com.hq.storeMS.zenmind.dao.interceptor;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.mongodb.InterceptorAftParams;
import com.zenmind.dao.mongodb.InterceptorPreParams;
import com.zenmind.dao.mongodb.interceptor.IntfMongoInterceptor;

public class MongoLogInterceptorImpl implements IntfMongoInterceptor{

	public static MongoLogInterceptorImpl newInstance() {
		MongoLogInterceptorImpl tmp = new MongoLogInterceptorImpl();
		return tmp;
	}
	
	final static private String idFormat = "tableName:{}, method:{}";
	final static private String infoFormat = "expenditure {} millisecond";
	
	@Override
	public void preHandle(InterceptorPreParams interceptorPreParams) {
	}

	@Override
	public void aftHandle(InterceptorAftParams interceptorAftParams) {
		if(LogInterceptorMgr.getInstance().isMongoOpen()){
			ClassInfo classInfoPojo = interceptorAftParams.getClassInfoPojo();
			String methodName = interceptorAftParams.getMethodName();
			long startTimeMillis = interceptorAftParams.getStartTimeMillis();
			long endTimeMillis = System.currentTimeMillis();
			//超过500毫秒
			long val = endTimeMillis - startTimeMillis;
			if(val > LogInterceptorMgr.getInstance().getMongoTimeMillis()){
				MainLog.warn(
					LogModule.MongoDao, 
					StringFormatUtil.format(idFormat, classInfoPojo.getTableName(), methodName), 
					StringFormatUtil.format(infoFormat, val));
			}
		}
	}

}
