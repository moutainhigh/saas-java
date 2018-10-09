package com.hq.testClass.robot.storeCardInfo;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.chainStore.service.storeCardInfo.data.DiscountCard;
import com.hq.chainStore.service.storeCardInfo.data.MembershipCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfo;

public class StoreCardInfoRobot {
	
	private StoreCardInfoRobotData data;
	
	public static StoreCardInfoRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static StoreCardInfoRobot newInstance(int mark){
		StoreCardInfoRobot robot = new StoreCardInfoRobot();
		robot.data = StoreCardInfoRobotData.newInstance(mark);
		return robot;
	}
	
	public StoreCardInfoRobotData getData() {
		return data;
	}

	public void setData(StoreCardInfoRobotData data) {
		this.data = data;
	}
	
	public StoreCardInfo getStoreCardInfo(long storeId) {
		return  StoreCardInfoRobotHelper.getInstance().getStoreCardInfo(this, storeId);
	}
	
	public void addMembershipCard(long storeId, int index){
		StoreCardInfoRobotHelper.getInstance().addMembershipCard(this, storeId, index);
	}
	
	public void delMembershipCard(long storeId, String membershipCardId){
		StoreCardInfoRobotHelper.getInstance().delMembershipCard(this, storeId, membershipCardId);
	}
	
	public void updMembershipCard(long storeId, MembershipCard source){
		StoreCardInfoRobotHelper.getInstance().updMembershipCard(this, storeId, source);
	}
	
	public void addProductCard(long storeId, int index,int cardType){
		StoreCardInfoRobotHelper.getInstance().addProductCard(this, storeId, index,cardType);
	}
	
	public void delProductCard(long storeId, String productCardId){
		StoreCardInfoRobotHelper.getInstance().delProductCard(this, storeId, productCardId);
	}
	
	public void updProductCard(long storeId, ProductCard source){
		StoreCardInfoRobotHelper.getInstance().updProductCard(this, storeId, source);
	}
	
	public void addDiscountCard(long storeId, int index){
		StoreCardInfoRobotHelper.getInstance().addDiscountCard(this, storeId, index);
	}
	
	public void delDiscountCard(long storeId, String discountCardId){
		StoreCardInfoRobotHelper.getInstance().delDiscountCard(this, storeId, discountCardId);
	}
	
	public void updDiscountCard(long storeId, DiscountCard source){
		StoreCardInfoRobotHelper.getInstance().updDiscountCard(this, storeId, source);
	}
	
	public void updMemberCardState(long storeId, UpdMemberCardStateData updateMemberCardStateData){
		StoreCardInfoRobotHelper.getInstance().updMemberCardState(this, storeId, updateMemberCardStateData);
	}
	
	public void batchUpdMemberCardState(long storeId, BatchUpdMemberCardStateData batchUpdateMemberCardStateData){
		StoreCardInfoRobotHelper.getInstance().batchUpdMemberCardState(this, storeId, batchUpdateMemberCardStateData);
	}
	
	public void updProductCardState(long storeId, UpdProductCardStateData updateProductCardStateData){
		StoreCardInfoRobotHelper.getInstance().updProductCardState(this, storeId, updateProductCardStateData);
	}
	
	public void batchUpdProductCardState(long storeId, BatchUpdProductCardStateData batchUpdateProductCardStateData){
		StoreCardInfoRobotHelper.getInstance().batchUpdProductCardState(this, storeId, batchUpdateProductCardStateData);
	}
	
}
