package com.hq;

import com.hq.experience.data.appointment.GenerateAppointmentData;
import com.hq.experience.data.clerkSalary.GenerateClerkSalaryData;
import com.hq.experience.data.leaguerAffair.GenerateLeaguerAffairData;
import com.hq.experience.data.materialRecord.GenerateMaterialRecordData;
import com.hq.experience.data.order.GenerateOrderData;
import com.hq.experience.data.orderComment.GenerateOrderCommentData;
import com.hq.experience.data.specialData.GenerateSpecialData;
import com.hq.experience.data.store.GenerateStoreData;
import com.hq.experience.data.storeBeautician.GenerateStoreBeauticianData;
import com.hq.experience.data.storeCardInfo.GenerateStoreCardInfoData;
import com.hq.experience.data.storeClerk.GenerateStoreClerkData;
import com.hq.experience.data.storeLeaguerInfo.GenerateStoreLeaguerInfoData;
import com.hq.experience.data.storeMaterial.GenerateStoreMaterialData;
import com.hq.experience.data.storeProductInfo.GenerateStoreProductInfoData;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;


/**
 * 为指定的老板生成测试数据
 * @author Administrator
 *
 */
public class GenerateUserData{
	
	public static void main(String[] args) throws Exception {
//		String storeService = "http://192.168.40.220:9110/storems/ws/v1";
//		String orderService = "http://192.168.40.220:9110/orderms/ws/v1";
		
		String storeService = "http://39.106.13.113:9110/storems/ws/v1";
		String orderService = "http://39.106.13.113:9110/orderms/ws/v1";
		
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateUserData().genData(12100000000L);
	}
	
	public GenerateUserData(){
		super();
	}
	
	public void genData(long phone) throws Exception{
		new GenerateStoreData().genData(phone);
		new GenerateStoreClerkData().genData(phone);
		new GenerateStoreBeauticianData().genData(phone);
		new GenerateStoreMaterialData().genData(phone);
		new GenerateStoreProductInfoData().genData(phone);
		new GenerateStoreLeaguerInfoData().genData(phone);
		new GenerateStoreCardInfoData().genData(phone);
		new GenerateLeaguerAffairData().genData(phone);
		new GenerateClerkSalaryData().genData(phone);
		new GenerateAppointmentData().genData(phone);
		new GenerateOrderData().genData(phone);
		new GenerateOrderCommentData().genData(phone);
		new GenerateMaterialRecordData().genData(phone);
		new GenerateSpecialData().genData(phone);
	}
}
	
