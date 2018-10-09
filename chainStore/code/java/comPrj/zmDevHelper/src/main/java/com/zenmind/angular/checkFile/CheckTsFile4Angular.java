package com.zenmind.angular.checkFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于检查ts文件名 与 导入的文件名是否一致【区分大小写】
 * @author Administrator
 *
 */
public class CheckTsFile4Angular {

	private final String suffix = ".ts";
	private final String checkPath="F:/honkon/Saas/chainStore/code/h5/zmtWeb/src/zmWeb";
	
	public static void main(String[] args) throws Exception {
		new CheckTsFile4Angular().doCheck();
	}

	public void doCheck() throws Exception {
		File sourceFile = new File(checkPath);
		//所有文件夹
		List<File> folderList = new ArrayList<File>();
		folderList.add(sourceFile);
		collectFolders(sourceFile, folderList);
		//所有文件
		List<File> allFiles = collectFiles(folderList);
		//过滤suffix类型的文件
		List<File> filterFiles = filterFilesByType(allFiles, suffix);
		//提取文件名 Appointment:appointment
		List<String> fileNames = getFileNameList(filterFiles, suffix);
		//检查不符合文件名称的导入语句
		checkFileName(filterFiles, fileNames);
		
		System.out.println("all file has finish!!");
	}
	
	public void checkFileName(List<File> filterFiles, List<String> fileNames) throws Exception{
		for (File file : filterFiles) {
			checkFile(file, fileNames);
		}
	}
	
	private void checkFile(File file, List<String> fileNames) throws Exception{
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String lineTmp = null;
		while ((lineTmp = bufReader.readLine()) != null) {
			if(isGetLine(lineTmp) && isMatchName(fileNames, getLineContent(lineTmp))){
				System.out.println(file.getName()+"=========="+lineTmp);
			}
		}
		bufReader.close();
	}
	
	private boolean isMatchName(List<String> fileNames, String fileName){
		boolean b = false;
		if(StringUtils.isBlank(fileName)){//存在文件名与import的名称不一致
			return b;
		}
		
		for (String name : fileNames) {
			if(fileName.equals(name)){
				b = true;
				break;
			}
		}
		return b;
	}
	
	private boolean isGetLine(String line){
		if(line.trim().indexOf(" from ") > -1 ){
			return true;
		}
		return false;
	}
	
	private String getLineContent(String line){
		line = StringUtils.substringBetween(line, "from", ";");
		if(line!=null && line.indexOf("/") > -1){
			line = line != null ? StringUtils.substringAfterLast(line, "/") : null;
		}
		line = line != null ? line.replace("\"", "").replace("'", ""):"";
		return line.trim();
	}
	
	
	public List<String> getFileNameList(List<File> filterFiles, String suffix){
		List<String> fileNames = new ArrayList<String>();
		for (File file : filterFiles) {
			String fileName = file.getName().replaceAll(suffix, "");
			if(StringUtils.isNotBlank(fileName)){
				fileNames.add(convertName(fileName));
			}
		}
		return fileNames;
	}
	
	private String convertName(String fileName){
		StringBuilder sb = new StringBuilder();
		char firstChar = fileName.charAt(0);
        if (Character.isLowerCase(firstChar)) {
        	sb.append(Character.toUpperCase(firstChar));
        }else{
        	sb.append(Character.toLowerCase(firstChar));
        }
        sb.append(fileName.substring(1));
        return sb.toString();
	}
	
	public List<File> filterFilesByType(List<File> fileList, String suffix) {
		List<File> list = new ArrayList<File>();
		for (File file : fileList) {
			if(file.getAbsolutePath().endsWith(suffix)){
				list.add(file);
			}
		}
		return list;
	}
	
	public List<File> collectFiles(List<File> folderList) {
		List<File> fileList = new ArrayList<File>();
		for (File folder : folderList) {
			File[] files = folder.listFiles();
			for (File filetmp : files) {
				if(!filetmp.isDirectory()){
					fileList.add(filetmp);
				}
			}
		}
		return fileList;
	}
	
	public void collectFolders(File dir, List<File> folderList) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				folderList.add(file);
				collectFolders(file,folderList);
			} 
		}
	}
	
}
