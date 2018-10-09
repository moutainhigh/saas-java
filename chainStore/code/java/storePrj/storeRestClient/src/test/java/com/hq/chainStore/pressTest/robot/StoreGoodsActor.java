package com.hq.chainStore.pressTest.robot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.storeGoods.data.GoodsType;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.testClass.robot.storeGoods.StoreGoodsRobot;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsActor {

	public static StoreGoodsActor getInstance(){
		return HotSwap.getInstance().getSingleton(StoreGoodsActor.class);
	}
	
	/**
	 * 查询
	 * @param storeId
	 * @return
	 */
	public StoreGoods get(long storeId){
		return StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
	}
	
	/**
	 * 获取店铺商品列表
	 * @param storeId
	 * @return
	 */
	public List<Goods> getGoodsList(long storeId){
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
		List<Goods> goodsList = new ArrayList<Goods>(storeGoods.getGoodsMap().values());
		return goodsList;
	}
	
	/**
	 * 随机获取店铺商品
	 * @param storeId
	 * @return
	 */
	public Goods getRandomGoods(long storeId){
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
		List<Goods> goodsList = new ArrayList<Goods>(storeGoods.getGoodsMap().values());
		if(goodsList.size() > 0){
			return goodsList.get(RandomUtils.nextInt(0, goodsList.size()));
		}else{
			return null;
		}
	}
	
	/**
	 * 添加商品
	 * @param storeId
	 */
	public void addGoods(long storeId){
		StoreGoods storeGoods = get(storeId);
		StoreGoodsRobot robot = StoreGoodsRobot.newRandom();
		robot.getData().setId(storeGoods.getGoodsIdIndex()+1);
		List<GoodsType> goodsTypeList = new ArrayList<GoodsType>(storeGoods.getGoodsTypeMap().values());
		if(goodsTypeList.size() > 0){
			GoodsType goodsType = goodsTypeList.get(RandomUtils.nextInt(0, goodsTypeList.size()));
			robot.getData().setTypeId(goodsType!=null?goodsType.getId():"0");
			robot.getData().setNumber("bumber-"+robot.getData().getId());
			robot.addGoods(storeId);
		}
	}
	
	/**
	 * 添加商品类型
	 * @param storeId
	 */
	public void addGoodsType(long storeId){
		StoreGoods storeGoods = get(storeId);
		StoreGoodsRobot robot = StoreGoodsRobot.newRandom();
		robot.getData().setId(storeGoods.getGoodsTypeIdIndex()+1);
		robot.getData().setName("商品分类-"+robot.getData().getId());
		robot.addGoodsType(storeId);
	}
	
}
