package com.hq.chainStore.pressTest.storeCreate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.StoreClientMgr;
import com.hq.chainStore.pressTest.robot.StoreCardInfoActor;
import com.hq.chainStore.pressTest.robot.StoreGoodsActor;
import com.hq.chainStore.pressTest.robot.StoreLeaguerInfoActor;
import com.hq.chainStore.pressTest.robot.StoreProductInfoActor;
import com.hq.chainStore.pressTest.storeActor.BossActor;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.CacheMgr4Test;
import com.hq.testClass.RestLogerImpl;
import com.hq.testClass.RestProxySTImpl;
import com.hq.testClass.RestTemplateMgr;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class StoreCreator {

	
	public static StoreData create(int clerkCount, int cuserCount,int productCount, long interval) throws InterruptedException{
		Boss boss = Boss.newBoss(BRobot.newRandom());
		//手机号已存在 则变更注册的手机号  直到满足条件
		while(getBuserByPhone(boss.getRobot().getData().getPhone()) != null){
			boss.getRobot().getData().setPhone(boss.getRobot().getData().getPhone()+100);
		}
		boss.reg();
		boss.login();
		boss.changeVipLevel();
		BossActor bossActor = BossActor.newInstance(boss);
		Store store = openStore(boss);
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		List<Leaguer> cuserList = new ArrayList<Leaguer>();
		while(cuserList.size() < cuserCount){
			Leaguer leaguer = addCuser(store.getId());
			cuserList.add(leaguer);
			Thread.sleep(interval);
		}
		buildData(store.getId(),productCount);
		StoreData data = StoreData.newInstance(bossActor);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return data;
	}
	
	private static BUser getBuserByPhone(long phone){
		try {
			BUser b = BUserMgr.getInstance().findByPhone(phone);
			return b;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 添加项目、商品、次卡基础数据
	 * @param storeId
	 * @param productCount
	 */
	private static void buildData(long storeId,int productCount){
		for(int i=0;i<productCount;i++){
			//项目
			StoreProductInfoActor.getInstance().addProductType(storeId);
			StoreProductInfoActor.getInstance().addProduct(storeId);
			//商品
			StoreGoodsActor.getInstance().addGoodsType(storeId);
			StoreGoodsActor.getInstance().addGoods(storeId);
			//次卡
			StoreCardInfoActor.getInstance().addProductCard(storeId);
		}
	}
	
	private static Store openStore(Boss boss) {
		int mark = RandomUtils.nextInt(0, 1000);
		Store store = boss.openSimpleStore(mark);
//		System.out.println("新建店铺======="+store.getId());
		return store;
	}

	private static Leaguer addCuser(long storeId) {
		Leaguer leaguer = StoreLeaguerInfoActor.getInstance().addLeaguer(storeId);
		return leaguer;
	}

	/**
	 * 初始化环境
	 */
	public static void initEnv(){
		RestTemplateMgr.getInstance().init();
		//nginx + gatewayServer + storeMS
//		String storeService = "http://192.168.40.52/storems/ws/v1";
//		String orderService = "http://192.168.40.52/orderms/ws/v1";
		
		//gatewayServer + storeMS
//		String storeService = "http://192.168.40.52:9110/storems/ws/v1";
//		String orderService = "http://192.168.40.52:9110/orderms/ws/v1";
		
		//storeMS
		String storeService = "http://192.168.40.52:9114/storems/ws/v1";
		String orderService = "http://192.168.40.52:9117/orderms/ws/v1";
		
		//localhost
//		String storeService = "http://127.0.0.1:9114/storems/ws/v1";
//		String orderService = "http://127.0.0.1:9117/orderms/ws/v1";
		
		//nginx + storeMS
//		String storeService = "http://192.168.40.52/dev/storems/ws/v1";
//		String orderService = "http://192.168.40.52/dev/orderms/ws/v1";
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService, orderService);
	}

	
}
