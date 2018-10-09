package com.zenmind;

import java.io.File;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;
import com.zenmind.codeGenHelper.H5ApiDataGenHelper;

/**
 * <pre>
 * 用于生成angular的实体
 * </pre>
 * @author kevin
 *
 */
public class KevinPC4Angular {
	private String angularFolder;
	private String inputFolder;
	private String outputFolder;
	
	//需要在外部设置
	private String sourcePath;
	private String angularTargetPath;
	private String module;

	public static void main(String[] args) throws Exception {
		String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\storeRestClient\\src\\main\\java\\com\\hq\\chainStore\\service\\order";
		String module = PathHelper.getFileName(sourcePath);
		
		String angularTargetPath = "F:\\honkon\\zhimeitimes\\saas-doc\\chainStore\\storeMS\\angular";
		new KevinPC4Angular(sourcePath, angularTargetPath, module).doGen();
	}
	
	public KevinPC4Angular(String sourcePath, String angularTargetPath, String module){
		String basePath = System.getProperty("user.dir");
		this.angularFolder = PathHelper.joinPaths(basePath, "codeGen\\angular\\apiData");
		this.inputFolder = PathHelper.joinPaths(angularFolder, "input");
		this.outputFolder = PathHelper.joinPaths(angularFolder, "output");
		
		this.sourcePath = PathHelper.convertSeparator(sourcePath);
		this.angularTargetPath = PathHelper.convertSeparator(angularTargetPath);
		this.module = module;
	}

	public void doGen() throws Exception {
		FileUtils.deleteAll(new File(inputFolder));
		FileUtils.copyDir(sourcePath, PathHelper.joinPaths(inputFolder, module));
		
		H5ApiDataGenHelper.newGenHelper(new File(PathHelper.joinPaths(inputFolder, module)), new File(PathHelper.joinPaths(outputFolder, module))).doGenWithNoOpen();
		copyAgCode2Doc();
	}
	
	private void copyAgCode2Doc() throws Exception {
		String targetPath = PathHelper.joinPaths(angularTargetPath, "["+module+"]");
		FileUtils.deleteAll(new File(targetPath));
		FileUtils.copyDir(PathHelper.joinPaths(outputFolder, module), targetPath);
	}
}
