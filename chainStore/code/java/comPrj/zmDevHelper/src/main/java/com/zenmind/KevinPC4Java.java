package com.zenmind;

import java.io.File;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;

/**
 * <pre>
 * 拷贝Java的实体到git本地仓库
 * </pre>
 * @author kevin
 *
 */
public class KevinPC4Java {
	private String javaFolder;
	private String inputFolder;
	
	//需要在外部设置
	private String sourcePath;
	private String javaTargetPath;
	private String module;

	public static void main(String[] args) throws Exception {
		String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\storeRestClient\\src\\main\\java\\com\\hq\\chainStore\\service\\order";
		String module = PathHelper.getFileName(sourcePath);
		
		String javaTargetPath = "F:\\honkon\\zhimeitimes\\saas-doc\\chainStore\\storeMS\\Java实体";
		new KevinPC4Java(sourcePath, javaTargetPath, module).doGen();
	}
	
	public KevinPC4Java(String sourcePath, String javaTargetPath, String module){
		String basePath = System.getProperty("user.dir");
		this.javaFolder = PathHelper.joinPaths(basePath, "codeGen\\java");
		this.inputFolder = PathHelper.joinPaths(javaFolder, "input");
		
		this.sourcePath = PathHelper.convertSeparator(sourcePath);
		this.javaTargetPath = PathHelper.convertSeparator(javaTargetPath);
		this.module = module;
	}

	public void doGen() throws Exception {
		FileUtils.deleteAll(new File(inputFolder));
		FileUtils.copyDir(sourcePath, PathHelper.joinPaths(inputFolder, module));
		copyJavaCode2Doc();
	}
	
	private void copyJavaCode2Doc() throws Exception {
		String targetPath = PathHelper.joinPaths(javaTargetPath, "["+module+"]");
		FileUtils.deleteAll(new File(targetPath));
		FileUtils.copyDir(PathHelper.joinPaths(inputFolder, module), targetPath);
	}
}
