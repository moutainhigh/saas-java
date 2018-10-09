package com.zenmind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zenmind.base.PathHelper;

/**
 * 使用方法：
 * 1、执行改类的main方法  
 * 
 * 功能：一次性生成IOS代码、angular代码，并且将生成的IOS H文件 、angular的ts文件 和 Java源文件 拷贝到git doc的目录下面
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class KevinPC4GenCode {

	private static final String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\storeRestClient\\src\\main\\java\\com\\hq\\chainStore\\service";
	private static final String angularTargetPath = "F:\\honkon\\zhimeitimes\\saas-doc\\chainStore\\storeMS\\angular";
	private static final String iosTargetPath = "F:\\honkon\\zhimeitimes\\saas-doc\\chainStore\\storeMS\\IOS代码";
	private static final String javaTargetPath = "F:\\honkon\\zhimeitimes\\saas-doc\\chainStore\\storeMS\\Java实体";
	
	public static List<String> getFolders(File dir){
		List<String> folders = new ArrayList<String>();
		File[] files = dir.listFiles();
		for (File f : files) {
			if(f.isDirectory()) {
				folders.add(PathHelper.getFileNameWithNoSuffix(f.getAbsolutePath()));
			}
		}
		folders.remove("common");
		return folders;
	}
	
	public static void main(String[] args) throws Exception {
//		List<String> modules = getFolders(new File(sourcePath));
		String[] modules = {"buserRole"};
		for (String module : modules) {
			new KevinPC4IosCode(PathHelper.joinPaths(sourcePath, module), iosTargetPath, module).doGen();
			new KevinPC4Angular(PathHelper.joinPaths(sourcePath, module), angularTargetPath, module).doGen();
			new KevinPC4Java(PathHelper.joinPaths(sourcePath, module), javaTargetPath, module).doGen();
		}
	}

}
