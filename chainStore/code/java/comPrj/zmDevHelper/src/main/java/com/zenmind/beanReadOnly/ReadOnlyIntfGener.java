package com.zenmind.beanReadOnly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.base.StringFormatUtil;

public class ReadOnlyIntfGener {
	
	public void genInterfaceCodes(){
		InputStream is = this.getClass().getResourceAsStream("fieldInput");
//		InputStream is = new FileInputStream(inputPath);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		try {
			String str = null;  
            while((str = in.readLine()) != null){
                if(lineFilter(str.trim())){
                	getInterface(getFieldClassName(str.trim()),getFieldName(str.trim()));
                }else{
                	continue;
                }
            }
            
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				in.close();
				is.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
	}
	
	//过滤无效行
	public boolean lineFilter(String str){
		if(StringUtils.isBlank(str) || str.startsWith("//")){
			return false;
		}else{
			return true;
		}
		
	}
	
	//读取FieldClassName //eg : String
	public String getFieldClassName(String str){
		String fieldClassName;
		String[] strArr = str.split("\\s+");

		fieldClassName = strArr[1];
		return fieldClassName;
	}
	
	//读取FieldName //eg : name
	public String getFieldName(String str){
		String fieldName;
		str = str.substring(0, str.length()-1);
		String[] strArr = str.split("\\s+");
		
    	fieldName = (strArr[2]).substring(0,1).toUpperCase() + (strArr[2]).substring(1);
		return fieldName;
	}

	private void getInterface(String fieldClassName, String fieldName) {
		String temlate = "public {} get{}();";
		String interfaceStr = StringFormatUtil.format(temlate, fieldClassName,fieldName);
		System.out.println(interfaceStr);
	}
	

	
	public static void main(String[] args) {
		
		new ReadOnlyIntfGener().genInterfaceCodes();
	}

	
}
