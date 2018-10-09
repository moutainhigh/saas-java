package com.hq.storeMS.service.excel.data;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: ExcelHeadMap 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年4月16日 上午9:36:00 
 *  
 */
public class ExcelHeadMap {
	
	@SuppressWarnings("serial")
	public final static Map<String,Integer> leaguerHeadMap = new HashMap<String, Integer>(){

		{
			put("*姓名",0);
			put("*手机号",1);
			put("*性别",2);
			put("跟进人员",3);
			put("介绍人",4);
			put("身份证",5);
			put("生日",6);
			put("阳历(阴历)",7);
			put("微信号",8);
			put("昵称",9);
			put("联系地址",10);
			put("工作单位",11);
			put("职位",12);
			
		}
	};
	
	@SuppressWarnings("serial")
	public final static Map<String,Integer> productHeadMap = new HashMap<String,Integer>(){

		{
			put("*名称",0);
			put("*分类",1);
			put("*售价(元)",2);
			put("编号",3);
			put("成本(元)",4);
			put("项目介绍",5);
		}
	};
	
	@SuppressWarnings("serial")
	public final static Map<String,Integer> goodsHeadMap = new HashMap<String,Integer>(){
		{
			put("*名称",0);
			put("*分类",1);
			put("*售价(元)",2);
			put("编号",3);
			put("成本(元)",4);
			put("商品介绍",5);
		}
	};
}
