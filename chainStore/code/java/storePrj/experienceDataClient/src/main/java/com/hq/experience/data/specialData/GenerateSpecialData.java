package com.hq.experience.data.specialData;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.order.apiData.OrderQueryForm;
import com.hq.chainStore.service.order.bs.OrderMgr;
import com.hq.chainStore.service.order.data.Order;
import com.hq.chainStore.service.order.data.OrderStatusEnum;
import com.hq.chainStore.service.report.apiData.BeauticianSpecialData;
import com.hq.chainStore.service.report.apiData.CUserSpecialData;
import com.hq.chainStore.service.report.apiData.ProductSpecialData;
import com.hq.chainStore.service.report.bs.SpecialDataMgr;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

@Deprecated
public class GenerateSpecialData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623958L;
		String service = "http://192.168.10.170:9114/ws/v1";
		String reportService = "http://192.168.10.170:9117/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
		new GenerateSpecialData().genData(phone);
	}
	
	public GenerateSpecialData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genSpecialData();
			}
			System.out.println("Generate SpecialData success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genSpecialData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setPageItemCount(10);
		params.setPageNo(1);
		params.setStoreId(storeId);
		params.setStatus(OrderStatusEnum.HAS_PAY.ordinal());
		List<Order> orders = OrderMgr.getInstance().findOrderList(params);
		Order order = orders.get(RandomUtils.nextInt(0, orders.size()));
		
		BeauticianSpecialData beauticianSpecialData = BeauticianSpecialData.newInstance();
		beauticianSpecialData.setBeauticianId(order.getBeauticianId());
		SpecialDataMgr.getInstance().addBeauticianSpecialData(storeId+"_" + boss.getId(), beauticianSpecialData);
		
		CUserSpecialData cUserSpecialData = CUserSpecialData.newInstance();
		cUserSpecialData.setCuserId(order.getCuserId());
		SpecialDataMgr.getInstance().addCUserSpecialData(storeId+"_" + boss.getId(), cUserSpecialData);
		
		ProductSpecialData productSpecialData = ProductSpecialData.newInstance();
		productSpecialData.setProductId(order.getProductId());
		SpecialDataMgr.getInstance().addProductSpecialData(storeId+"_" + boss.getId(), productSpecialData);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
