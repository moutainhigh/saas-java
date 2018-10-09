package com.hq.taskMS.storeTask;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.lang3.StringUtils;

public class ExtClasspathLoader {

	public static ExtClasspathLoader newInstance() throws Exception {

		ExtClasspathLoader target = new ExtClasspathLoader();
		target.init();
		return target;

	}

	private Method addPathMethod;

	private URLClassLoader classloader;

	private void init() throws Exception {

			URLClassLoader systemLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

			URL[] urls = systemLoader.getURLs();

			classloader = new URLClassLoader(urls);

			addPathMethod = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
			addPathMethod.setAccessible(true);

	}

	public ExtClasspathLoader addJar(File jarFile) throws Exception{
		addPathMethod.invoke(classloader, new Object[] { jarFile.toURI().toURL() });
		return this;
	}
	public ExtClasspathLoader addJar(String jarFilePath) throws Exception{
		File jarFile = new File(jarFilePath);
		return addJar(jarFile);
	}

	public Class<?> loadClass(String className) throws ClassNotFoundException {
		Class<?> target = classloader.loadClass(className);
		return target;

	}

	public static void main(String[] args) throws ClassNotFoundException, Exception {
		String jarPath = "D:/allen/prjhq/prjsaas/chainStore/code/java/experienceDataClient/target/experienceDataClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar";
		String className = "com.hq.TestAllen";
		String method = "test";
		ExtClasspathLoader newInstance = ExtClasspathLoader.newInstance().addJar(jarPath);
		Class<?> targetClass = newInstance.loadClass(className);

		Object target = targetClass.newInstance();

		Class<?> clazz = target.getClass();
		if (StringUtils.isNotBlank(method)) {
			Method targetMethod = clazz.getDeclaredMethod(method);
			targetMethod.setAccessible(true);
			targetMethod.invoke(target);
		}

	}

}