package com.hq;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hq.experience.data.appointment.GenerateAppointmentData;
import com.hq.experience.data.order.GenerateOrderData;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

/**
 * 为指定的老板，周期生成预约、订单的数据
 * @author Administrator
 *
 */
public class GeneratePeriodData {
	public static void main(String[] args) throws Exception {
		new GeneratePeriodData().genData();
	}
	
	public GeneratePeriodData(){
		super();
	}
	
	public void genData() throws Exception{
		Properties prop = getProperties();
		String storeService = prop.getProperty("storeService").trim();
		String orderService = prop.getProperty("orderService").trim();
		String phoneStr = prop.getProperty("phone").trim();
		System.out.println("storeService="+storeService);
		System.out.println("orderService="+orderService);
		System.out.println("phone="+phoneStr);
		long phone = Long.valueOf(phoneStr);
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		
		try {
			new GenerateAppointmentData().genData(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			new GenerateOrderData().genData(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Properties getProperties(){
		Properties prop = new Properties();
		InputStream in = null;
		try{
			String configPath = "/configPath/config.properties";
//			String configPath = System.getProperty("user.dir")+"/src/main/java/com/hq/config.properties";
			in = new BufferedInputStream (new FileInputStream(configPath));
		    prop.load(in);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
