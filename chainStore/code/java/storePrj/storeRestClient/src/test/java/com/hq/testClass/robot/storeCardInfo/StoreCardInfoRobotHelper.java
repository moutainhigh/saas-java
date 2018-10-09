package com.hq.testClass.robot.storeCardInfo;

import java.util.ArrayList;

import com.hq.chainStore.service.storeCardInfo.apiData.AddDiscountCard;
import com.hq.chainStore.service.storeCardInfo.apiData.AddMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.DelDiscountCard;
import com.hq.chainStore.service.storeCardInfo.apiData.DelMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.DelProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdDiscountCard;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdMembershipCard;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.chainStore.service.storeCardInfo.data.DiscountCard;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.PrdInCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCardTypeEnum;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class StoreCardInfoRobotHelper {

	private static StoreCardInfoRobotHelper instance = new StoreCardInfoRobotHelper();

	public static StoreCardInfoRobotHelper getInstance() {
		return instance;
	}

	public StoreCardInfo getStoreCardInfo(StoreCardInfoRobot robot, long storeId) {
		StoreCardInfo data = StoreCardInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		return data;
	}
	
	public void addMembershipCard(StoreCardInfoRobot robot, long storeId, int index){
		AddMembershipCard param = AddMembershipCard.newInstance();
		FastBeanCopyer.getInstance().copy(robot.getData(), param);
		param.setIndex(index);
		StoreCardInfoMgr.getInstance().addMembershipCard(storeId, param);
	}
	
	public void delMembershipCard(StoreCardInfoRobot robot, long storeId, String membershipCardId){
		DelMembershipCard param = DelMembershipCard.newInstance();
		param.setId(membershipCardId);
		StoreCardInfoMgr.getInstance().delMembershipCard(storeId, param);
	}
	
	public void updMembershipCard(StoreCardInfoRobot robot, long storeId, MembershipCard source){
		UpdMembershipCard param = UpdMembershipCard.newInstance();
		FastBeanCopyer.getInstance().copy(source, param);
		StoreCardInfoMgr.getInstance().updMembershipCard(storeId, param);
	}
	
	
	public void addProductCard(StoreCardInfoRobot robot, long storeId, int index,int cardType){
		AddProductCardForm param = AddProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(robot.getData(), param);
		param.setIndex(index);
		param.setType(cardType);
		ProductCardTypeEnum cardTypeEnum = ProductCardTypeEnum.valueOf(cardType);
		switch (cardTypeEnum) {
		case LIMIT_PRDANDTIME:
			param.setTime(0);
			break;
		case LIMIT_TIMEBUTPRD:
			param.setProductList(new ArrayList<PrdInCard>());
			break;
		case NOLIMIT_PRDANDTIME:
			param.setTime(0);
			param.setProductList(new ArrayList<PrdInCard>());
			break;

		default:
			break;
		}
		StoreCardInfoMgr.getInstance().addProductCard(storeId, param);
	}
	
	public void delProductCard(StoreCardInfoRobot robot, long storeId, String membershipCardId){
		DelProductCardForm param = DelProductCardForm.newInstance();
		param.setId(membershipCardId);
		StoreCardInfoMgr.getInstance().delProductCard(storeId, param);
	}
	
	public void updProductCard(StoreCardInfoRobot robot, long storeId, ProductCard source){
		UpdProductCardForm param = UpdProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(source, param);
		StoreCardInfoMgr.getInstance().updProductCard(storeId, param);
	}
	
	public void addDiscountCard(StoreCardInfoRobot robot, long storeId, int index){
		AddDiscountCard param = AddDiscountCard.newInstance();
		FastBeanCopyer.getInstance().copy(robot.getData(), param);
		param.setIndex(index);
		StoreCardInfoMgr.getInstance().addDiscountCard(storeId, param);
	}
	
	public void delDiscountCard(StoreCardInfoRobot robot, long storeId, String discountCardId){
		DelDiscountCard param = DelDiscountCard.newInstance();
		param.setId(discountCardId);
		StoreCardInfoMgr.getInstance().delDiscountCard(storeId, param);
	}
	
	public void updDiscountCard(StoreCardInfoRobot robot, long storeId, DiscountCard source){
		UpdDiscountCard param = UpdDiscountCard.newInstance();
		FastBeanCopyer.getInstance().copy(source, param);
		StoreCardInfoMgr.getInstance().updDiscountCard(storeId, param);
	}
	
	public void updMemberCardState(StoreCardInfoRobot robot, long storeId, UpdMemberCardStateData updateMemberCardStateData){
		StoreCardInfoMgr.getInstance().updMemberCardState(storeId, updateMemberCardStateData);
	}
	
	public void batchUpdMemberCardState(StoreCardInfoRobot robot, long storeId, BatchUpdMemberCardStateData batchUpdateMemberCardStateData){
		StoreCardInfoMgr.getInstance().batchUpdMemberCardState(storeId, batchUpdateMemberCardStateData);
	}
	
	public void updProductCardState(StoreCardInfoRobot robot, long storeId, UpdProductCardStateData updateProductCardStateData){
		StoreCardInfoMgr.getInstance().updProductCardState(storeId, updateProductCardStateData);
	}
	
	public void batchUpdProductCardState(StoreCardInfoRobot robot, long storeId, BatchUpdProductCardStateData batchUpdateProductCardStateData){
		StoreCardInfoMgr.getInstance().batchUpdProductCardState(storeId, batchUpdateProductCardStateData);
	}
	
}