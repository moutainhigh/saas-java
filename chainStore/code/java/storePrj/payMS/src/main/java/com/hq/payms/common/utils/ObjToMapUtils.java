package com.hq.payms.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.github.wxpay.sdk.WXPayUtil;

import org.apache.commons.beanutils.BeanMap;

/**
 * Object和Map互转工具类 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@SuppressWarnings("unchecked")
public class ObjToMapUtils {
	
	/** 
	 * 使用org.apache.commons.beanutils进行转换 
	 */  
	public static <T> T mapToObjectByApache(Map<String, String> map, Class<T> beanClass) throws Exception {    
        if (map == null)  
            return null;  
  
        Object obj = beanClass.newInstance();  
  
        BeanUtils.populate(obj, map);  
  
        return (T)obj;  
    }    
      
    public static Map<String, String> objectToMapByApache(Object obj) {  
        if(obj == null)  
            return null;   
        Map<String, String> map = new HashMap<String, String>();
        BeanMap beanMap = new BeanMap(obj);
        for (Entry<Object, Object> entry : beanMap.entrySet()) {
        	String key = String.valueOf(entry.getKey());
        	if (key.compareToIgnoreCase("class") == 0) {   
        		continue;
            }  
        	String value = String.valueOf(entry.getValue());
        	if( StringUtils.isBlank(value) || "null".equals(value) ) { //忽略空值
        		continue; 
        	}
        	map.put(key, value);
		}
        return map;
    }   


    /** 
     * 使用Introspector进行转换 
     */  
    public static <T> T mapToObjectByIntrospector(Map<String, String> map, Class<T> beanClass) throws Exception {    
        if (map == null)   
            return null;    
  
        Object obj = beanClass.newInstance();  
  
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {  
            Method setter = property.getWriteMethod();    
            if (setter != null) {  
                setter.invoke(obj, map.get(property.getName()));   
            }  
        }  
  
        return (T)obj;  
    }    
      
    public static Map<String, Object> objectToMapByIntrospector(Object obj) throws Exception {    
        if(obj == null)  
            return null;      
  
        Map<String, Object> map = new HashMap<String, Object>();   
  
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {    
            String key = property.getName();    
            if (key.compareToIgnoreCase("class") == 0) {   
                continue;  
            }  
            Method getter = property.getReadMethod();  
            Object value = getter!=null ? getter.invoke(obj) : null;  
            map.put(key, value);  
        }    
  
        return map;  
    }    
    
    /** 
     * 使用reflect进行转换 
     */  
    public static Object mapToObjectByReflect(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;    
  
        Object obj = beanClass.newInstance();  
  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }    
  
            field.setAccessible(true);    
            field.set(obj, map.get(field.getName()));   
        }   
  
        return obj;    
    }    
  
    public static Map<String, Object> objectToMapByReflect(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
  
        return map;  
    }   
    
    public static class Person{
    	private String name;
    	private String age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
		
    }
    
    public static void main(String[] args) throws Exception {
    	Person p = new Person();
    	p.setName("张三");
//    	p.setAge("18");
    	Map<String, String> map = objectToMapByApache(p);
    	String mapToXml = WXPayUtil.mapToXml(map);
    	System.out.println(mapToXml);
    	
    	Map<String,String> map2 = new HashMap<String, String>();
    	map2.put("name", "李四");
    	map2.put("age", "19");
    	Person p2 = mapToObjectByApache(map2, Person.class);
    	System.out.println(p2.toString());
    	
	}

}
