package com.hq.chainStore.pressTest.robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.appointment.apiData.AppointmentAddApiForm;
import com.hq.chainStore.service.appointment.bs.AppointmentMgr;
import com.hq.chainStore.service.appointment.data.AppointProduct;
import com.hq.chainStore.service.appointment.data.Appointment;
import com.hq.chainStore.service.appointment.data.AppointmentQueryParams;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class AppointActor {

	public static AppointActor getInstance(){
		return HotSwap.getInstance().getSingleton(AppointActor.class);
	}
	
	/**
	 * 获取预约
	 * @param storeId
	 * @param bossId
	 * @return
	 */
	public Appointment getRandomAppoint(long storeId,long bossId){
//		long now = System.currentTimeMillis();
//		long minTime = now - 100L * 24 * 3600 * 1000;
//		long maxTime = now + 3600*1000;
		
		AppointmentQueryParams params = AppointmentQueryParams.newInstance();
//		params.setMaxTime(maxTime);
//		params.setMinTime(minTime);
		params.setStoreId(storeId);
		params.setPageItemCount(10);
//		params.setLeaguerName("555");
		
		PageResp<Appointment> pageResp = AppointmentMgr.getInstance().getAppointmentPageInfo(params);
//		System.out.println(JsonUtil.getInstance().toJson(pageResp));
		List<Appointment> list = pageResp.getList();
		if(list.size() > 0){
			return list.get(RandomUtils.nextInt(0, list.size()));
		}else{
			return null;
		}
	}
	
	public void addAppoint(long storeId,long bossId){
		//员工
//		List<ClerkInfo> ClerkList = StoreClerkInfoActor.getInstance().getClerkList(storeId);
//		if(ClerkList.size() > 0){
//			ClerkInfo clerkInfo = ClerkList.get(RandomUtils.nextInt(0, ClerkList.size()));
//		}
		
		//项目
		List<ProductInfo> productList = StoreProductInfoActor.getInstance().getProductList(storeId);
		if(productList.size() > 0){
			ProductInfo product = productList.get(RandomUtils.nextInt(0, productList.size()));
			
			List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();
			AppointProduct apd = AppointProduct.newInstance();
			Set<Long> buserIds = new HashSet<Long>();
			buserIds.add(bossId);
			apd.setProductId(product.getId());
			apd.setBuserIds(buserIds);
			appointProducts.add(apd);
			
			AppointmentAddApiForm addForm = AppointmentAddApiForm.newInstance();
			addForm.setStoreId(storeId);
			//客户
			List<Leaguer> leaguerList = StoreLeaguerInfoActor.getInstance().getLeaguerList(storeId);
			if(leaguerList.size() > 0){
				Leaguer leaguer = leaguerList.get(RandomUtils.nextInt(0, leaguerList.size()));
				addForm.setLeaguerId(leaguer.getId());
			}
			
			addForm.setAppointTime(System.currentTimeMillis());
			addForm.setCreatorId(bossId);
			addForm.setAppointProducts(appointProducts);
			AppointmentMgr.getInstance().addAppointment(addForm);
		}
	}
	
}
