package com.zenmind.beanReadOnly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.base.StringFormatUtil;

public class ReadOnlyIntfGenerS {
	
	public void genInterfaceCodes(){
		InputStream is = this.getClass().getResourceAsStream("fieldInput");
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
//            System.out.println("public void incrVer();");
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
		if(StringUtils.isBlank(str) || str.startsWith("//") || str.startsWith("@")){
			return false;
		}else{
			return true;
		}
		
	}
	
	//读取FieldClassName //eg : String
	public String getFieldClassName(String str){
		String fieldClassName="";
		if(str.indexOf("=") != -1){
			str = str.split("=")[0].trim()+";";
		}
		str = str.substring(0, str.lastIndexOf(";"));
		String[] strArr = str.split("\\s+");
		if(strArr.length == 3){
			fieldClassName = strArr[1];
		}else{
			for (int i = 0; i < strArr.length; i++) {
				if(i != 0 && i != strArr.length-1){
					fieldClassName+=strArr[i];
				}
			}
		}
		return fieldClassName;
	}
	
	//读取FieldName //eg : name
	public String getFieldName(String str){
		String fieldName="";
		if(str.indexOf("=") != -1){
			str = str.split("=")[0].trim()+";";
		}
		str = str.substring(0, str.lastIndexOf(";"));
		String[] strArr = str.split("\\s+");
		fieldName = strArr[strArr.length-1];
		
    	fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		return fieldName;
	}

	private void getInterface(String fieldClassName, String fieldName) {
		String temlate = "public {} get{}();";
		String interfaceStr = StringFormatUtil.format(temlate, fieldClassName,fieldName);
		System.out.println(interfaceStr);
	}
	

	
	public static void main(String[] args) {
		
		new ReadOnlyIntfGenerS().genInterfaceCodes();
	}

	
}
