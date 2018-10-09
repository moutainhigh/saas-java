//package com.hq.experience.data.beauticianProduct;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.RandomUtils;
//
//import com.hq.BaseGenerate;
//import com.hq.StoreClientMgr;
//import com.hq.chainStore.service.beauticianProduct.apiData.AddForemost;
//import com.hq.chainStore.service.beauticianProduct.bs.BeauticianProductMgr;
//import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
//import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
//import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
//import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
//import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
//import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
//import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
//
///**
// * 已经取消医美师模块 
// * @author Administrator
// *
// */
//@Deprecated
//public class GenerateBeauticianProductData extends BaseGenerate{
//	
//	public static void main(String[] args) throws Exception {
//		long phone = 13660623958L;
//		String service = "http://192.168.10.170:9114/ws/v1";
//		String reportService = "http://192.168.10.170:9117/ws/v1";
//		RestTemplateMgr.getInstance().init();
//		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service, reportService);
//		new GenerateBeauticianProductData().genData(phone);
//	}
//	
//	public GenerateBeauticianProductData(){
//		super();
//	}
//	
//	@Override
//	public void genData(long phone){
//		try {
//			initEnv(phone);
//			for (Long id : storeIds) {
//				this.storeId = id;
//				genBeauticianProductData();
//			}
//			System.out.println("Generate BeauticianProduct Data success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void genBeauticianProductData() {
//		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//		List<ProductInfo> productInfos = new ArrayList<ProductInfo>(StoreProductInfoMgr.getInstance().get(storeId).getProductInfoMap().values());
//		ProductInfo info = productInfos.get(RandomUtils.nextInt(0, productInfos.size()));
//		List<Long> bIds = new ArrayList<Long>(info.getBeauticianSet());
//		String beauticianProductId = storeId+"_"+bIds.get(RandomUtils.nextInt(0, bIds.size()));
//		AddForemost addForemost = AddForemost.newInstance();
//		addForemost.setProductId(info.getId());
//		BeauticianProductMgr.getInstance().addForemost(beauticianProductId, addForemost);
//		AccessTokenMgr.getInstance().removeOpIdTL();
//	}
//}
//	
