package com.hq.payms.common.utils;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * Json样式转换工具类<br>
 * 支持驼峰式和下划线式
 *  
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class JsonStyleUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String write2SnakeCase(Object obj) throws Exception {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return mapper.writeValueAsString(obj);
	}
	
	public static String write2UpperCamelCase(Object obj) throws Exception {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
		return mapper.writeValueAsString(obj);
	}
	
	public static <T> T read4SnakeCase(String json, Class<T> clazz) throws Exception {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return mapper.readValue(json, clazz);
	}
	
	public static <T> T read4UpperCamelCase(String json, Class<T> clazz) throws Exception {
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
		return mapper.readValue(json, clazz);
	}
	
	public static void main(String[] args) throws Exception {
        String json = "{\"user_name\":\"bflee\",\"id_number\":\"123456\"}";
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        
        Foo foo = read4SnakeCase(json, Foo.class);
        System.out.println(foo.getIdNumber());
        
        String str = write2SnakeCase(foo);
        System.out.println(str);
   }

    static class Foo implements Serializable{
        private static final long serialVersionUID = -3004824622398622080L;
        private String userName ;
        private String idNumber ;
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getIdNumber() {
            return idNumber;
        }
        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
 
}