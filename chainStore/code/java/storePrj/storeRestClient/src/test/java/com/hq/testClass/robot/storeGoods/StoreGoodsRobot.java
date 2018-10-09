package com.hq.testClass.robot.storeGoods;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeGoods.apiData.GoodsAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsAddToTopForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsCancelTopForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsRemoveForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsUpdateForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainStore.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class StoreGoodsRobot {
	
	private StoreGoodsRobotData data;
	
	public static StoreGoodsRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static StoreGoodsRobot newInstance(int mark){
		StoreGoodsRobot robot = new StoreGoodsRobot();
		robot.data = StoreGoodsRobotData.newInstance(mark);
		return robot;
	}
	
	public StoreGoodsRobotData getData() {
		return data;
	}
	
	public long getId() {
		return this.data.getStoreId();
	}
	
	public StoreGoods findSimpleStoreInfo(long storeId){
		return StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
	}
	
	public void addGoods(long storeId){
		GoodsAddForm dataForm = GoodsAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this.data, dataForm);
		StoreGoodsMgr.getInstance().addGoods(storeId, dataForm);
	}
	
	public void updateGoods(long storeId, GoodsUpdateForm dataForm){
		StoreGoodsMgr.getInstance().updateGoods(storeId, dataForm);
	}
	
	public void removeGoods(long storeId, GoodsRemoveForm dataForm){
		StoreGoodsMgr.getInstance().removeGoods(storeId, dataForm);
	}
	
	public void updateGoodsState(long storeId, GoodsUpdateStateForm dataForm){
		StoreGoodsMgr.getInstance().updateGoodsState(storeId, dataForm);
	}
	
	public void batchUpdateGoodsState(long storeId, GoodsBatchUpdateStateForm dataForm){
		StoreGoodsMgr.getInstance().batchUpdateGoodsState(storeId, dataForm);
	}
	
	public void addGoodsType(long storeId){
		GoodsTypeAddForm dataForm = GoodsTypeAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this.data, dataForm);
		StoreGoodsMgr.getInstance().addGoodsType(storeId, dataForm);
	}
	
	public void removeGoodsType(long storeId, GoodsTypeRemoveForm dataForm){
		StoreGoodsMgr.getInstance().removeGoodsType(storeId, dataForm);
	}
	
	public void updateGoodsType(long storeId, GoodsTypeUpdateForm dataForm){
		StoreGoodsMgr.getInstance().updateGoodsType(storeId, dataForm);
	}
	
	public void addGoodsToTop(long storeId, GoodsAddToTopForm dataForm){
		StoreGoodsMgr.getInstance().addGoodsToTop(storeId, dataForm);
	}
	
	public void cancelGoodsFromTop(long storeId, GoodsCancelTopForm dataForm){
		StoreGoodsMgr.getInstance().cancelGoodsFromTop(storeId, dataForm);
	}
	
	
}
