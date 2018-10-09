package com.zenmind;

import java.io.File;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;
import com.zenmind.codeGenHelper.IOSCodeGenHelper;



/**
 * 使用方法：
 * 1、执行改类的main方法  
 * 
 * 功能：批量生成IOS代码，并且将IOS的H文件 和 Java源文件 拷贝到git doc的目录下面
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class KevinPC4IosCode {
	private String inputPath;
	private String outputPath;
	private String tempPath;
	
	//需要在外部设置
	private String iosTargetPath;
	private String sourcePath;
	private String module;
	
	public static void main(String[] args) throws Exception {
		String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\storeRestClient\\src\\main\\java\\com\\hq\\chainStore\\service\\workFlowData";
//	    String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\customerRestClient\\src\\main\\java\\com\\hq\\customerRestClient\\service";
		String iosTargetPath = "F:\\honkon\\zhimeitimes\\saas-doc\\chainStore\\storeMS\\IOS代码";
		String module = PathHelper.getFileName(sourcePath);
		
		new KevinPC4IosCode(sourcePath, iosTargetPath, module).doGen();
	}
	
	public KevinPC4IosCode(String sourcePath, String iosTargetPath, String module){
		String basePath = System.getProperty("user.dir");
		this.inputPath = PathHelper.joinPaths(basePath,"codeGen\\mgr2iosh\\input");
		this.outputPath = PathHelper.joinPaths(basePath, "codeGen\\mgr2iosh\\output");
		this.tempPath = PathHelper.joinPaths(basePath, "codeGen\\mgr2iosh\\temp");
		
		this.sourcePath=PathHelper.convertSeparator(sourcePath);
		this.iosTargetPath=PathHelper.convertSeparator(iosTargetPath);
		this.module=module;
	}
	
	public void doGen() throws Exception{
		FileUtils.deleteAll(new File(inputPath));
		FileUtils.copyDir(sourcePath, PathHelper.joinPaths(inputPath, module));
		
		IOSCodeGenHelper.newInstance(new File(this.inputPath), new File(this.tempPath), new File(this.outputPath), module).doGenWithNoOpen();
		copyFile2GitDoc();
	}
	
	private void copyFile2GitDoc() throws Exception{
		String tmpIosTargetPath = PathHelper.joinPaths(iosTargetPath, "["+module+"]");
		FileUtils.deleteAll(new File(tmpIosTargetPath));
		FileUtils.copyDir(PathHelper.joinPaths(outputPath, module), tmpIosTargetPath);
	}
	
}
