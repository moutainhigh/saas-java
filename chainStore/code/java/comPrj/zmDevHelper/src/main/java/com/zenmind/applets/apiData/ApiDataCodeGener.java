package com.zenmind.applets.apiData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.base.FileUtils;

public class ApiDataCodeGener {
	
	//指定要迭代的目录名
	private String inputFolder;
	private String outputFolder;
	
	//指定生成的文件名
	private String outPutFileName = "BUserDeviceApiData";
	
	//行分隔符
	private String separator = System.getProperty("line.separator");
	
	public static  ApiDataCodeGener newInstance(String opFolder){
		ApiDataCodeGener instance = new ApiDataCodeGener();
		
		instance.inputFolder = opFolder+"\\input";;
		instance.outputFolder = opFolder+"\\output";
	
		return instance;
	}
	
	public void doGen() throws Exception{
		
		FileUtils.deleteAll(new File(outputFolder));
		FileUtils.createFolder(new File(outputFolder));
		
		File inputDir = new File(inputFolder);
		List<File> folderList = new ArrayList<File>();
		folderList.add(inputDir);
		collectFolders(inputDir,folderList);
		
		List<File> fileList = collectFiles(folderList);
		
		List<ApiDataInfo> dataInfoList = new ArrayList<ApiDataInfo>();
		for (File file : fileList) {
			dataInfoList.add(getDataInfo(file));
		}
		
		genAppletsFile(dataInfoList);
		
	}


	private void genAppletsFile(List<ApiDataInfo> dataInfoList) throws Exception {
		
		File outputFile = new File(outputFolder, outPutFileName+".js");
		StringBuilder sb = new StringBuilder();

		sb.append("var ").append(outPutFileName).append(" = new function () {").append(separator);
		sb.append(" ").append("var service = this;").append(separator);

		for (int index = 0; index < dataInfoList.size(); index++) {
			ApiDataInfo apiDataInfo = dataInfoList.get(index);
				
			if(apiDataInfo.isEnum()){
				appendEnumInfo(sb, apiDataInfo);
			}else{
				appendClassInfo(sb, apiDataInfo);
			}
			
			if(index == dataInfoList.size()-1){
				sb.append("}").append(separator);
				sb.append("module.exports =  ").append(outPutFileName).append(";");
			}
			
			try {
				FileUtils.writeTxt(outputFile, sb.toString());
			} catch (Exception e) {
				System.out.println("lll "+outputFile.getPath());
			}
			
		}
		
	}
	
	private void appendEnumInfo(StringBuilder sb, ApiDataInfo apiDataInfo) {

		  // service.CUserUpdateType = AppUtils.enum({
		  //     updateInfo: 0,
		  //     changePassword: 1,
		  // });
		
		String className = apiDataInfo.getClassName();
		
		sb.append("service."+className+" = AppUtils.enum({").append(separator);
			
		LinkedList<ApiDataFieldInfo> linkedList = apiDataInfo.getFieldList();
		
		ApiDataFieldInfo fieldTmp = linkedList.pop();
		int index = 0;
		while(fieldTmp!=null){
			String fieldName = fieldTmp.getFieldName();
			if(StringUtils.contains(fieldName, "(")){
				fieldName = StringUtils.substringBeforeLast(fieldName, "(").trim();
			}
			sb.append("  ").append(fieldName).append(":").append(index).append(",");
			sb.append(separator);
			index++;
			fieldTmp = linkedList.pollFirst();
		}
		
		sb.append(")}").append(separator);
		
	}

	private void appendClassInfo(StringBuilder sb, ApiDataInfo apiDataInfo) {

//		service.newCUserAddApiForm = function(){
//				 return new CUserAddApiForm();
//		}
//				  
//		 function CUserAddApiForm() {
//		      this.name = null;
//		      this.phone = null;
//		      this.password = null;
//		      this.headImg = null;
//		      this.gender = null;
//		      this.age = null;
//		      this.verifyCode = null;
//		  };
		
		
		String className = apiDataInfo.getClassName();
		sb.append("service."+"new"+className+" = function(){").append(separator);
		sb.append("  ").append("return "+"new "+className+"();").append(separator).append("}").append(separator);
		sb.append("function "+className+"() {").append(separator);
		
		for (ApiDataFieldInfo fieldTmp : apiDataInfo.getFieldList()) {
			sb.append("  ").append("this."+fieldTmp.getFieldName()).append(" = ").append("null").append(";").append(separator);
		}		
		
		sb.append("}").append(separator);
		
	}

	private ApiDataInfo getDataInfo(File targetFile) {
		try {
			ApiDataInfo info = new ApiDataInfo();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile)));

			List<String> lineList = new ArrayList<String>();
			boolean isEnum = false;
			String lineTmp = null;
			while ((lineTmp = bufReader.readLine()) != null) {
				lineList.add(lineTmp);
				if(StringUtils.contains(lineTmp, "enum")){
					isEnum = true;
				}
				
			}
			
			//readLine()是一个阻塞函数，当没有数据读取时，就一直会阻塞在那，而不是返回null.所以要手动关闭流
			bufReader.close();
			
			if(isEnum){
				info = handleEnum(lineList);
			}else{
				info = handleClass(lineList);
			}
			
			return info;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ApiDataInfo handleEnum(List<String> lineList) {
		ApiDataInfo info = new ApiDataInfo();
		info.setEnum(true);
		String className;
		
		StringBuilder enumSumSb = new StringBuilder();
		boolean shouldAppend = false;
		for (String lineTmp : lineList) {
			if(shouldAppend){
				enumSumSb.append(lineTmp);
				if(StringUtils.contains(lineTmp, ";")){
					shouldAppend = false;
				}
			}
			if(StringUtils.contains(lineTmp, "enum") && StringUtils.contains(lineTmp, "{")){
				className = StringUtils.substringBetween(lineTmp, "enum", "{").trim();
				info.setClassName(className);
				shouldAppend = true;
			} 
				
		}
		
		
		String enumSum = StringUtils.substringBefore(enumSumSb.toString(), ";") ;
		
		String[] split = enumSum.split(",");
		for (String fieldNameTmp : split) {
			String trim = fieldNameTmp.trim();
			if(StringUtils.isNotBlank(trim)){
				info.addFieldName(trim, null);
			}
		}
		
		return info;
	}

	private ApiDataInfo handleClass(List<String> lineList){
		ApiDataInfo info = new ApiDataInfo();
		for (String lineTmp : lineList) {
			//去掉注释
			lineTmp = handleComment(lineTmp);
			
			//获取类名
			if(StringUtils.contains(lineTmp, "class") && StringUtils.contains(lineTmp, "{")){
				String className = StringUtils.substringBetween(lineTmp, "class", "{").trim();
				if(StringUtils.contains(className, "implements")){
					className = StringUtils.substringBetween(lineTmp, "class", "implements").trim();
				}
				info.setClassName(className);
			}

			//属性名 属性类型
			if(StringUtils.contains(lineTmp, "private") && lineTmp.trim().endsWith(";")){
				String tmp = StringUtils.substringAfter(lineTmp, "private").trim();
				String fieldName = null;
				String filedType = StringUtils.substringBefore(tmp, " ").trim();
				if(StringUtils.contains(tmp, "=")){	
					fieldName = StringUtils.substringBetween(tmp, " ", "=").trim();
				}else{
					fieldName = StringUtils.substringBetween(tmp, " ", ";").trim();
				}
				info.addFieldName(fieldName,filedType);
			}
		}
		return info;
		
	}

	private String handleComment(String lineTmp) {
		
		if(StringUtils.contains(lineTmp, "//")){
			lineTmp = StringUtils.substringBefore(lineTmp, "//");
		}
		
		return lineTmp;
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
	
	public void collectFolders(File dir,List<File> folderList) {
		
		File[] files = dir.listFiles();
		
		for (File file : files) {
			if (file.isDirectory()) {
				folderList.add(file);
				collectFolders(file,folderList);
			} 
		}
	}

	public String getOutPutFileName() {
		return outPutFileName;
	}

	public void setOutPutFileName(String outPutFileName) {
		this.outPutFileName = outPutFileName;
	}

}
