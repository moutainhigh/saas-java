package com.hq.chainStore.service.storeCardInfo.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreCardInfoSynDataHolder extends AbsDataSynDataHolder<StoreCardInfo> {

	public static StoreCardInfoSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreCardInfo;

	protected Class<StoreCardInfo> getClazz() {
		return StoreCardInfo.class;
	}
	
	public StoreCardInfo getData(Long ownerId,String targetId){
		return super.getData(String.valueOf(ownerId), targetId);
	}


	protected RestDao<StoreCardInfo> getDao() {
		return StoreCardInfoDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	/**
	 * 获取客户会员卡列表
	 * @param ownerId 当前登录的用户ID
	 * @param leaguerId
	 * @return
	 */
	public List<MembershipCard> getMembershipCards(String ownerId, Long storeId){
		StoreCardInfo storeCardInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<MembershipCard>(storeCardInfo.getMembershipCardMap().values());
	}
	
	/**
	 * 获取会员卡详情
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public MembershipCard getMembershipCard(String ownerId, String membershipCardId){
		String[] ids = membershipCardId.split("_");
		StoreCardInfo storeCardInfo = super.getData(ownerId, String.valueOf(ids[2]));
		return storeCardInfo.getMembershipCardMap().get(membershipCardId);
	}
	
	/**
	 * 获取客户优惠卷列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<DiscountCard> getDiscountCards(String ownerId, Long storeId){
		StoreCardInfo storeCardInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<DiscountCard>(storeCardInfo.getDiscountCardMap().values());
	}
	
	/**
	 * 获取优惠卷详情
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public DiscountCard getDiscountCard(String ownerId, String discountCardId){
		String[] ids = discountCardId.split("_");
		StoreCardInfo storeCardInfo = super.getData(ownerId, String.valueOf(ids[2]));
		return storeCardInfo.getDiscountCardMap().get(discountCardId);
	}
	
	/**
	 * 获取耗卡列表
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public List<ProductCard> getProductCards(String ownerId, Long storeId){
		StoreCardInfo storeCardInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<ProductCard>(storeCardInfo.getProductCardMap().values());
	}
	
	/**
	 * 获取耗卡详情
	 * @param ownerId
	 * @param leaguerId
	 * @return
	 */
	public ProductCard getProductCard(String ownerId, String productCardId){
		String[] ids = productCardId.split("_");
		StoreCardInfo storeCardInfo = super.getData(ownerId, String.valueOf(ids[2]));
		return storeCardInfo.getProductCardMap().get(productCardId);
	}

}
