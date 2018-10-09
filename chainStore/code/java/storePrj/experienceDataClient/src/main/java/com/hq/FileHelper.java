package com.hq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileHelper {

	public static void replace(File targetFile, Map<String, String> replaceMap) {
		try {
			Set<String> tokenSet = replaceMap.keySet();

			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile)));

			StringBuffer strBuf = new StringBuffer();

			String lineTmp = null;
			while ((lineTmp = bufReader.readLine()) != null) {
				for (String token : tokenSet) {
					lineTmp = lineTmp.replace(token, replaceMap.get(token));
				}
				strBuf.append(lineTmp);
				strBuf.append(System.getProperty("line.separator"));
			}

			bufReader.close();

			BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(targetFile)));
			bufWriter.write(strBuf.toString());
			
			bufWriter.flush();
			bufWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyDir(String sourceDirPath, String destDirPath) throws IOException {

		if (!(new File(destDirPath)).exists()) {
			(new File(destDirPath)).mkdir();
		}
		File sourceDir = new File(sourceDirPath);

		String[] fileArray = sourceDir.list();

		for (int i = 0; i < fileArray.length; i++) {
			String sourceFilePathTmp = sourceDirPath + File.separator + fileArray[i];
			String destFilePathTmp = destDirPath + File.separator + fileArray[i];
			if ((new File(sourceFilePathTmp)).isDirectory()) {
				copyDir(sourceFilePathTmp, destFilePathTmp);
			}

			if (new File(sourceFilePathTmp).isFile()) {
				copyFile(sourceFilePathTmp, destFilePathTmp);
			}

		}
	}

	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;

		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	public static void deleteAll(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile() || file.list().length == 0) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteAll(files[i]);
				files[i].delete();
			}

			if (file.exists()) // 如果文件本身就是目录 ，就要删除目录
				file.delete();
		}
	}

	public static void main(String[] args) throws IOException {
//		String oldDir = "D:\\allen\\prjtools\\prj\\zmDevHelper\\doc\\bak\\devTemplate";
//		String newDir = "D:\\allen\\prjtools\\prj\\zmDevHelper\\doc\\devTemplate";
//		FileHelper.deleteAll(new File(newDir));
//		FileHelper.copyDir(oldDir, newDir);
		
		String pathname = "D:\\allen\\prjhq\\prjsaas\\chainStore\\code\\java\\zmDevHelper\\doc\\test\\StoreAPI.java";
		File targetFile = new File(pathname );
		
		final Map<String, String> replaceMap = new HashMap<String, String>();
		replaceMap.put("store", "{pojo}");
		replaceMap.put("Store","{Pojo}");
		
	
		FileHelper.replace(targetFile, replaceMap);

	}
}
