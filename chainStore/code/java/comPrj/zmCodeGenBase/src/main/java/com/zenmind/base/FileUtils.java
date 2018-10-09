/*
 * $HeadURL: http://150.236.80.220/dev/dsc/dmm/branches/dmm-2.1.1-13-AOP321-14-maint/src/com/drutt/dmm/util/FileUtils.java $
 * $Id: FileUtils.java 858830 2010-09-15 15:12:04Z eligchn $
 * Copyright (c) 2009 by Ericsson, all rights reserved.
 */

package com.zenmind.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;


public class FileUtils {
	
	
	public static String upFirstChar(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}
	
	public static String lowFirstChar(String input) {
		return input.substring(0, 1).toLowerCase() + input.substring(1);
	}
	
	public static void openFolder(String filePath){
		try {  
            String[] cmd = new String[5];  
            cmd[0] = "cmd";  
            cmd[1] = "/c";  
            cmd[2] = "start";  
            cmd[3] = " ";  
            cmd[4] = filePath;  
            Runtime.getRuntime().exec(cmd);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}

	public static void replace(File targetFile, Map<String, String> replaceMap) {
		try {
			Set<String> tokenSet = replaceMap.keySet();

			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8"));

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

			BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
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
		List<File> allFile = getAllFile(sourceDir);
		sourceDirPath = PathHelper.convertSeparator(sourceDirPath);
		destDirPath = PathHelper.convertSeparator(destDirPath);
		for (File file : allFile) {
			String absolutePath = PathHelper.convertSeparator(file.getAbsolutePath());
			String targetPath = StringUtils.replace(absolutePath, sourceDirPath, destDirPath);
			copyFile(absolutePath, targetPath);
		}
	}
	
	public static List<File> getAllFile(File dir) {
		List<File> list = new ArrayList<File>();
		if (!dir.exists()) {
			return list;
		}
		
		if (dir.isFile()) {
			list.add(dir);
		} else {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				list.addAll(getAllFile(files[i]));
			}
		}
		return list;
	}

	public static void deleteAll(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteAll(files[i]);
				files[i].delete();
			}
		}
		//删除完后检查自己是否可删除
		if(file.isDirectory()&& file.list().length == 0){
			file.delete();
		}
	}
	
	public static void copyFile(String oldPath, String newPath) throws IOException {
		copyFile(new File(oldPath), new File(newPath));
	}
	
	private static void fileCreateIfNotExists(File file) throws IOException {
		File fileParent = file.getParentFile();
		if(!fileParent.exists()){
			fileParent.mkdirs();
		}
		if(!file.exists()) {
			file.createNewFile();
		}
	}
	
	
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: 858830 $";

	/**
	 * Whether the specified folder is empty.
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean isEmptyFolder(String folder) {
		boolean isEmpty = true;
		File f = new File(folder);
		if (f.exists()) {
			String[] fileNames = f.list();
			if (fileNames != null && fileNames.length > 0) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	/**
	 * Will create all folders for folder.
	 * 
	 * @param folder
	 *            the folder (and subfolders) to be created
	 */
	public static void createFolder(File folder) {
		if (!folder.exists()) {
			if (!folder.mkdirs()) {
				throw new RuntimeException("Failed to create folder for upload servlet: " + folder.getPath());
			}
		}
	}

	/**
	 * Will copy a file from source to destination.
	 * 
	 * @param source
	 *            Source file
	 * @param dest
	 *            Destination file
	 * @return True if nothing goes wrong
	 */
	public static boolean copyFile(File source, File dest) {
		try {
			fileCreateIfNotExists(dest);
			FileOutputStream os = new FileOutputStream(dest);
			FileInputStream is = new FileInputStream(source);
			byte[] buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
			os.flush();
			os.close();
			is.close();
		} catch (Exception x) {
			return false;
		}
		return true;
	}

	/**
	 * Will try and delete file .
	 * 
	 * @param f
	 *            The file to be deleted
	 * @return True if the file was successfully deleted
	 */
	public static boolean deleteFile(File f) {
		if (f.exists() && f.canWrite()) {
			try {
				return (f.delete());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void reNameFiles(String oriFileDirPath, String namePrefix) {

		File oriFileDir = new File(oriFileDirPath);

		int index = 1;
		for (File fileTmp : oriFileDir.listFiles()) {
			File dest = new File(oriFileDirPath + "/" + namePrefix + "_" + index);
			fileTmp.renameTo(dest);
			index++;
		}

	}

	public static String readTxt(File file) throws Exception {
		final String encoding = "UTF-8";
		return readTxt(file, encoding);
	}
	
	public static String readTxt(File file, String encoding) throws Exception {
		StringBuilder content = new StringBuilder();
		if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					content.append(lineTxt);
				}
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		}
		return content.toString();
	}

	public static void writeTxt(File file, String fileContent) throws Exception {
		final String encoding = "UTF-8";
		writeTxt(file, fileContent, encoding);
	}
	
	public static void writeTxt(File file, String fileContent, String encoding) throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), encoding);
		BufferedWriter writer = new BufferedWriter(write);
		try {
			writer.write(fileContent);
			writer.flush();
		} finally {
			writer.close();
		}
	}

}
