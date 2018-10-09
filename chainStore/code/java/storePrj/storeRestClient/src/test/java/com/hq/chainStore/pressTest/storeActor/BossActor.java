package com.hq.chainStore.pressTest.storeActor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.common.IntfPressAction;
import com.hq.chainStore.pressTest.common.PressRecorder;
import com.hq.chainStore.pressTest.robot.AppointActor;
import com.hq.chainStore.pressTest.robot.StoreOrderActor;
import com.hq.chainStore.pressTest.robot.workFlow.DeProdCardRecordActor;
import com.hq.chainStore.pressTest.robot.workFlow.GoodsRecordActor;
import com.hq.chainStore.pressTest.robot.workFlow.LeaguerActor;
import com.hq.chainStore.pressTest.robot.workFlow.MemberCardActor;
import com.hq.chainStore.pressTest.robot.workFlow.PrdCardRecordActor;
import com.hq.chainStore.pressTest.robot.workFlow.ProdRecordActor;
import com.hq.chainStore.pressTest.robot.workFlow.WorkFlowActor;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.robot.buser.Boss;

public class BossActor {

	
	private Boss boss;
	
	private Map<Integer,IntfPressAction> actionMap = new HashMap<Integer,IntfPressAction>();
	
	public static BossActor newInstance(Boss bossP){
		BossActor ba = new BossActor();
		ba.boss = bossP;
		ba.init();
		return ba;
	}
	
	public void doAction() {
//		int size = actionMap.size();
		Integer[] keys = actionMap.keySet().toArray(new Integer[0]);
		int nextAct = keys[RandomUtils.nextInt(0,keys.length)];
		try {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
//			actionMap.get(23).doAction();
			actionMap.get(nextAct).doAction();
//			PressRecorder.logReq("BossActor");
			AccessTokenMgr.getInstance().removeOpIdTL();
		} catch (Exception e) {
			PressRecorder.logError("BossActor", e);
		}
	}
	
	private void init(){
//		//添加岗位
//		IntfPressAction addAdminRole = new IntfPressAction() {			
//			@Override
//			public void doAction() {
//				System.out.println("addAdminRole==========");
//				StoreClerkInfoActor.getInstance().addStoreAdminRole(boss.getStoreId());	
//			}
//		};
//		actionMap.put(0, addAdminRole);	
//		
//		//添加项目
//		IntfPressAction addProduct = new IntfPressAction() {			
//			@Override
//			public void doAction() {
//				System.out.println("addProduct==========");
//				StoreProductInfoActor.getInstance().addProduct(boss.getStoreId());				
//			}
//		};
//		actionMap.put(1, addProduct);	
//		
//		//添加商品
//		IntfPressAction addGoods = new IntfPressAction() {			
//			@Override
//			public void doAction() {
//				System.out.println("addGoods==========");
//				StoreGoodsActor.getInstance().addGoods(boss.getStoreId());				
//			}
//		};
//		actionMap.put(2, addGoods);
//		
//		//添加会员卡
//		IntfPressAction addMemberCard = new IntfPressAction() {			
//			@Override
//			public void doAction() {
//				System.out.println("addMemberCard==========");
//				StoreCardInfoActor.getInstance().addMemberCard(boss.getStoreId());				
//			}
//		};
//		actionMap.put(3, addMemberCard);
//		
//		//添加次卡
//		IntfPressAction addProductCard = new IntfPressAction() {			
//			@Override
//			public void doAction() {
//				System.out.println("addProductCard==========");
//				StoreCardInfoActor.getInstance().addProductCard(boss.getStoreId());
//			}
//		};
//		actionMap.put(4, addProductCard);
		
		//添加预约
		IntfPressAction addAppoint = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("addAppoint==========");
				AppointActor.getInstance().addAppoint(boss.getStoreId(), boss.getId());
			}
		};
		actionMap.put(5, addAppoint);
		
		//添加订单
		IntfPressAction addOrder = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("addOrder==========");
				StoreOrderActor.getInstance().addOrder(boss.getStoreId(),boss.getId());
			}
		};
		actionMap.put(6, addOrder);
		
		/******************************购买消费工作流**************************************/
		//添加工作流
		IntfPressAction addWorkFlow = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("addWorkFlow==========");
				WorkFlowActor.getInstance().addWorkFlow(boss.getStoreId(),boss.getId());
			}
		};
		actionMap.put(7, addWorkFlow);
		
		//预约转工作流
		IntfPressAction addWorkFlowByAppoint = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("addWorkFlowByAppoint==========");
				WorkFlowActor.getInstance().addByAppoint(boss.getStoreId(),boss.getId());
			}
		};
		actionMap.put(8, addWorkFlowByAppoint);
		
		//添加修改项目列表
		IntfPressAction upProdList = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("upProdList==========");
				ProdRecordActor.getInstance().upProdList(boss.getStoreId());
			}
		};
		actionMap.put(9, upProdList);
		
		//修改项目数量、价格
		IntfPressAction updateProdItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("updateProdItem==========");
				ProdRecordActor.getInstance().updateProdItem(boss.getStoreId());
			}
		};
		actionMap.put(10, updateProdItem);
		
		//删除项目
		IntfPressAction deleteProdItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("deleteProdItem==========");
				ProdRecordActor.getInstance().deleteProdItem(boss.getStoreId());
			}
		};
		actionMap.put(11, deleteProdItem);
		
		//添加修改划卡项目列表
		IntfPressAction upDeProdList = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("upDeProdList==========");
				DeProdCardRecordActor.getInstance().upDeProdList(boss.getStoreId());
			}
		};
		actionMap.put(12, upDeProdList);
		
		//修改划卡项目
		IntfPressAction updateDeProdItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("updateDeProdItem==========");
				DeProdCardRecordActor.getInstance().updateDeProdItem(boss.getStoreId());
			}
		};
		actionMap.put(12, updateDeProdItem);
		
		//删除划卡项目
		IntfPressAction deleteDeProdItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("deleteDeProdItem==========");
				DeProdCardRecordActor.getInstance().deleteDeProdItem(boss.getStoreId());
			}
		};
		actionMap.put(13, deleteDeProdItem);
		
		//添加修改商品列表
		IntfPressAction upGoodsRecordList = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("upGoodsRecordList==========");
				GoodsRecordActor.getInstance().upGoodsRecordList(boss.getStoreId());
			}
		};
		actionMap.put(14, upGoodsRecordList);
		
		//修改商品
		IntfPressAction updateGoodsItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("updateGoodsItem==========");
				GoodsRecordActor.getInstance().updateGoodsItem(boss.getStoreId());
			}
		};
		actionMap.put(15, updateGoodsItem);
		
		//删除商品
		IntfPressAction deleteGoodsItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("deleteGoodsItem==========");
				GoodsRecordActor.getInstance().deleteGoodsItem(boss.getStoreId());
			}
		};
		actionMap.put(16, deleteGoodsItem);
		
		//添加修改次卡列表
		IntfPressAction upPrdCardList = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("upPrdCardList==========");
				PrdCardRecordActor.getInstance().upPrdCardList(boss.getStoreId());
			}
		};
		actionMap.put(17, upPrdCardList);
		
		//修改次卡数量价格
		IntfPressAction updatePrdCardItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("updatePrdCardItem==========");
				PrdCardRecordActor.getInstance().updatePrdCardItem(boss.getStoreId());
			}
		};
		actionMap.put(18, updatePrdCardItem);
		
		//删除次卡
		IntfPressAction deletePrdCardItem = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("deletePrdCardItem==========");
				PrdCardRecordActor.getInstance().deletePrdCardItem(boss.getStoreId());
			}
		};
		actionMap.put(19, deletePrdCardItem);
		
		//添加修改客户
		IntfPressAction updateLeaguer = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("updateLeaguer==========");
				LeaguerActor.getInstance().updateLeaguer(boss.getStoreId(), boss.getId());
			}
		};
		actionMap.put(20, updateLeaguer);
		
		//添加修改会员充值信息
		IntfPressAction updateMemberCard = new IntfPressAction() {			
			@Override
			public void doAction() {
//				System.out.println("updateMemberCard==========");
				MemberCardActor.getInstance().updateMemberCard(boss.getStoreId());
			}
		};
		actionMap.put(21, updateMemberCard);
		
		/***********************************工作流购买消费******************************************/
		
//		//添加商品类型
//		IntfPressAction addGoodsType = new IntfPressAction() {			
//			@Override
//			public void doAction() {
//				System.out.println("addGoodsType==========");
//				StoreGoodsActor.getInstance().addGoodsType(boss.getStoreId());				
//			}
//		};
//		actionMap.put(22, addGoodsType);
		
		/***********************************查询工作流******************************************/
		//查询工作流
		IntfPressAction queryWorkFlowDataType = new IntfPressAction() {			
			@Override
			public void doAction() {
				WorkFlowActor.getInstance().getList(boss.getStoreId());		
			}
		};
		actionMap.put(23, queryWorkFlowDataType);
	}	
	
}
