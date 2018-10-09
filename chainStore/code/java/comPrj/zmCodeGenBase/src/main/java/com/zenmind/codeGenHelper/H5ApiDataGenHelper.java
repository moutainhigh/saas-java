package com.zenmind.codeGenHelper;

import java.io.File;

import com.zenmind.base.FileUtils;
import com.zenmind.codeGen.angular.apiData.ApiDataCodeGener;

public class H5ApiDataGenHelper {

	public static H5ApiDataGenHelper newGenHelper(File inputDir, File outputDir) {
		H5ApiDataGenHelper target = new H5ApiDataGenHelper();

		target.inputDir = inputDir;
		target.outputDir = outputDir;

		return target;
	}

	private File inputDir;
	private File outputDir;

	public void doGen() throws Exception {
		doGenWithNoOpen();
		FileUtils.openFolder(outputDir.getAbsolutePath());
	}
	
	public void doGenWithNoOpen() throws Exception {
		ApiDataCodeGener.newInstance(inputDir.getAbsolutePath(), outputDir.getAbsolutePath()).doGen();
	}

}
