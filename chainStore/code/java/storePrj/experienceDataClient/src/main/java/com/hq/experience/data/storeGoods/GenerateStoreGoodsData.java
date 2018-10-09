package com.hq.experience.data.storeGoods;

import java.util.ArrayList;
import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.chainStore.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.experienceData.TGoods;
import com.hq.experienceData.service.RandomUtils;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateStoreGoodsData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 121000000L;
//		String storeService = "http://192.168.40.220/storems/ws/v1";
//		String orderService = "http://192.168.40.220/orderms/ws/v1";
		
		String storeService = "http://192.168.40.220/storems/ws/v1";
		String orderService = "http://192.168.40.220/orderms/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
		new GenerateStoreGoodsData().genData(phone);
	}
	
	public GenerateStoreGoodsData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genGoodsTypeData();
				genGoodsData();
			}
//			this.storeId = 275L;
//			genGoodsTypeData();
//			genGoodsData();
			System.out.println("Generate GenerateStoreGoodsData Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genGoodsTypeData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
		long goodsTypeId = storeGoods.getGoodsTypeIdIndex();
		List<TGoods> tGoodsList = TGoods.buildTGoodsTypeList();

		for (TGoods tGoods : tGoodsList) {
			goodsTypeId = goodsTypeId + 1 ; 
			GoodsTypeAddForm dataForm = GoodsTypeAddForm.newInstance();
			dataForm.setName(tGoods.getTypeName());
			dataForm.setIndex(goodsTypeId);
			StoreGoodsMgr.getInstance().addGoodsType(storeId, dataForm);
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	public void genGoodsData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
		long goodsId = storeGoods.getGoodsIdIndex();
		List<String> typeIds = new ArrayList<String>(storeGoods.getGoodsTypeMap().keySet());
		List<TGoods> tGoodsList = TGoods.buildTGoodsList();

		for (TGoods tGoods : tGoodsList) {
			goodsId = goodsId + 1 ; 
			GoodsAddForm dataForm = GoodsAddForm.newInstance();
			FastBeanCopyer.getInstance().copy(tGoods, dataForm);
			dataForm.setIndex(goodsId);
			dataForm.setTypeId(typeIds.get(RandomUtils.nextInt(0, typeIds.size())));
			
			FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
			apiForm.setFileType("img");
			apiForm.setModuleType("storeGoods");
			apiForm.setModuleId(storeId+"");
			String uploadImg = uploadImg(apiForm, tGoods.getImgNames().get(0));
			List<String> imgPaths = new ArrayList<String>();
			imgPaths.add(uploadImg);
			dataForm.setImgPaths(imgPaths);
			
			StoreGoodsMgr.getInstance().addGoods(storeId, dataForm);
		}
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
