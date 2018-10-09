package com.zenmind.codeGenHelper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;

import java2typescript.SourceTranslator;

public class J2TSGenHelper {

	public static J2TSGenHelper newGenHelper(File inputDir, File outputDir, String module) {
		J2TSGenHelper target = new J2TSGenHelper();
		target.inputDir = inputDir;
		target.outputDir = outputDir;
		target.module = module;
		return target;
	}

	private File inputDir;
	private File outputDir;
	private String module;

	public void doGen() throws Exception {
		doGenWithNoOpen();
		FileUtils.openFolder(outputDir.getAbsolutePath());
	}
	
	public void doGenWithNoOpen() throws Exception {
		String inputPath = inputDir.getAbsolutePath();
		String outputPath = outputDir.getAbsolutePath();
//		FileUtils.deleteAll(new File(PathHelper.joinPaths(inputPath, module, "bs")));
//		List<File> allFile = FileUtils.getAllFile(new File(PathHelper.joinPaths(inputPath, module, "data")));
//		for (File file : allFile) {
//			String filePath = file.getAbsoluteFile().toString();
//			if (filePath.endsWith("DAO.java") || filePath.endsWith("Holder.java")) {
//				FileUtils.deleteFile(file);
//			}
//		}
		
		replaceContent();

		String[] folders = new String[] { "apiData", "data", "bs" };
		for (String folder : folders) {
			String source = PathHelper.joinPaths(inputPath, module, folder);
			String target = PathHelper.joinPaths(outputPath, module);
			SourceTranslator translator = new SourceTranslator(source, target, module + FileUtils.upFirstChar(folder));
			translator.process();
			translator.generate();
		}
	}
	
	private void replaceContent() throws Exception {
		List<File> allFile = FileUtils.getAllFile(inputDir);
		Map<String, String> replaceMap = new HashMap<String, String>();
		for (File file : allFile) {
			String fileName = PathHelper.getFileNameWithNoSuffix(file.getAbsolutePath());
			System.out.println(fileName);
			replaceMap.put("return HotSwap.getInstance().getSingleton("+fileName+".class);", "return new "+fileName+"();");
		}
		System.out.println(replaceMap);
		for (File file : allFile) {
			FileUtils.replace(file, replaceMap);
		}
	}

}
