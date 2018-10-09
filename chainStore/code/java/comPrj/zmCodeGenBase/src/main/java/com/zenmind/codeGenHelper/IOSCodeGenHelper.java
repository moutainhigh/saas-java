package com.zenmind.codeGenHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;
import com.zenmind.codeGen.ios.GenerHelper;

public class IOSCodeGenHelper {
	private String tempPath;
	private String inputPath;
	private String outputPath;

	// input tmp output module
	public static IOSCodeGenHelper newInstance(File inputDir, File tempDir, File outputDir, String module) {
		IOSCodeGenHelper target = new IOSCodeGenHelper();
		target.inputPath = PathHelper.joinPaths(inputDir.getAbsolutePath(), module);
		target.outputPath = PathHelper.joinPaths(outputDir.getAbsolutePath(), module);
		target.tempPath = PathHelper.joinPaths(tempDir.getAbsolutePath(), module);
		return target;
	}

	public void doGen() throws Exception {
		doGenWithNoOpen();
		FileUtils.openFolder(outputPath);
	}
	
	public void doGenWithNoOpen() throws Exception {
		removeDirFiles();
		copySourceFile();
		
		exchangePath();
		
		new GenerHelper(PathHelper.joinPaths(inputPath, "mgr")).genCodes();
		new GenerHelper(PathHelper.joinPaths(inputPath, "bean")).genCodes();
		new GenerHelper(PathHelper.joinPaths(inputPath, "enum")).genCodes();
	}

	public void removeDirFiles() throws IOException {
		FileUtils.deleteAll(new File(tempPath));
		FileUtils.deleteAll(new File(outputPath));

		FileUtils.createFolder(new File(PathHelper.joinPaths(tempPath, "mgr")));
		FileUtils.createFolder(new File(PathHelper.joinPaths(tempPath, "bean")));
		FileUtils.createFolder(new File(PathHelper.joinPaths(tempPath, "enum")));

		FileUtils.createFolder(new File(PathHelper.joinPaths(outputPath, "mgr")));
		FileUtils.createFolder(new File(PathHelper.joinPaths(outputPath, "bean")));
		FileUtils.createFolder(new File(PathHelper.joinPaths(outputPath, "enum")));
	}

	public void copySourceFile() throws Exception {
		copyBsCode();

		copyApiDataCode();

		copyDataCode();
	}

	private void copyBsCode() throws Exception {
		List<File> allFile = FileUtils.getAllFile(new File(PathHelper.joinPaths(inputPath, "bs")));
		for (File file : allFile) {
			String name = file.getName();
			FileUtils.copyFile(file, new File(PathHelper.joinPaths(tempPath, "mgr", name)));
		}
	}

	private void copyApiDataCode() throws Exception {
		File apiDataDir = new File(PathHelper.joinPaths(inputPath, "apiData"));
		if (!apiDataDir.exists()) {
			return;
		}
		List<File> allFile = FileUtils.getAllFile(apiDataDir);
		for (File file : allFile) {
			String name = file.getName();
			String destFilePathTmp = "";
			if (isEnumFile(file)) {
				destFilePathTmp = PathHelper.joinPaths(tempPath, "enum", name);
			} else {
				destFilePathTmp = PathHelper.joinPaths(tempPath, "bean", name);
			}
			FileUtils.copyFile(file, new File(destFilePathTmp));
		}
	}

	private void copyDataCode() throws Exception {
		File dataDir = new File(PathHelper.joinPaths(inputPath, "data"));
		if (!dataDir.exists()) {
			return;
		}

		List<File> allFiles = FileUtils.getAllFile(dataDir);
		for (File file : allFiles) {
			String fileName = file.getName();
			String destFilePathTmp = "";
			if (fileName.endsWith("DataHolder.java") || fileName.endsWith("DAO.java")) {
				continue;
			} else if (isEnumFile(file)) {
				destFilePathTmp = PathHelper.joinPaths(tempPath, "enum", fileName);
			} else {
				destFilePathTmp = PathHelper.joinPaths(tempPath, "bean", fileName);
			}
			FileUtils.copyFile(file, new File(destFilePathTmp));
		}
	}
	
	private boolean isEnumFile(File targetFile) {
		boolean isEnum = false;
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile)));
			String lineTmp = null;
			while ((lineTmp = bufReader.readLine()) != null) {
				if(StringUtils.contains(lineTmp, "public enum")){
					isEnum = true;
				}
			}
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isEnum;
	}
	
	private void exchangePath() throws Exception {
		FileUtils.deleteAll(new File(inputPath));
		FileUtils.copyDir(tempPath, inputPath);
	}

}
