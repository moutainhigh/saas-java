package com.hq.taskMS.storeTask;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.common.hotSwap.HotSwap;

public class TaskHelper {

	public static TaskHelper getInstance(){
		return HotSwap.getInstance().getSingleton(TaskHelper.class);
	}
	
	public void awake(String jarPath, String className, String method) throws Exception {


		Class<?> targetClass = ExtClasspathLoader.newInstance().addJar(jarPath).loadClass(className);

		Object target = targetClass.newInstance();

		if(StringUtils.isNotBlank(method)){
			Method targetMethod = targetClass.getDeclaredMethod(method);
			targetMethod.setAccessible(true);
			targetMethod.invoke(target);
		}
	}
	
	public static void main(String[] args) throws Exception {
		TaskHelper.getInstance().awake("D:/allen/prjhq/prjsaas/chainStore/code/java/experienceDataClient/target/experienceDataClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar", "com.hq.GenerateTData", "genData");
	}
}
