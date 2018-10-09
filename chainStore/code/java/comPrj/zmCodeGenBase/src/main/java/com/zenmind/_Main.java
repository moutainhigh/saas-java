package com.zenmind;

import java.io.File;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;
import com.zenmind.codeGenHelper.H5ApiDataGenHelper;

public class _Main {
	public static void main(String[] args) throws Exception {
//		String sourcePath = "E:\\java_code\\saas-java\\chainStore\\code\\java\\storePrj\\storeRestClient\\src\\main\\java\\com\\hq\\chainStore\\service\\incomePay";
		String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\storeClient\\src\\main\\java\\com\\hq\\storeClient\\service\\daySnapshot";
		String module = PathHelper.getFileName(sourcePath);
		
		String inputPath = "F:\\codeGenTmpDir\\input";
		String outputPath = "F:\\codeGenTmpDir\\output";
		String tempPath = "F:\\codeGenTmpDir\\temp";
		
		FileUtils.deleteAll(new File(inputPath));
		FileUtils.copyDir(sourcePath, PathHelper.joinPaths(inputPath, module));
		
		File inputDir = new File(inputPath);
		File outputDir = new File(outputPath);
		H5ApiDataGenHelper.newGenHelper(inputDir, outputDir).doGen();
		
//		String targetModule="leaguerRecord";
//		File inputDir = new File(inputPath);
//		File outputDir = new File(outputPath);
//		File tempDir = new File(tempPath);
//		CodeGenHelper.newInstance(inputDir , tempDir, outputDir)
//		.withTempClass(FileUtils.upFirstChar(module), module)
//		.withBsClass(FileUtils.upFirstChar(targetModule), targetModule).doGenBS();
		
//		File inputDir = new File(inputPath);
//		File tempDir = new File(tempPath);
//		File outputDir = new File(outputPath);
//		IOSCodeGenHelper.newInstance(inputDir, tempDir, outputDir, module).doGen();
	}
}
