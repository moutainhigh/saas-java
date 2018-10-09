package com.hq.storeMS.service.storeVip.bs;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buserRole.bs.BuserRoleMgr;
import com.hq.storeMS.service.buserRole.data.BuserRole;
import com.hq.storeMS.service.buserRole.data.VipContent;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreVipMgr {

	public static StoreVipMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipMgr.class);
	}
	
	public boolean isExpired(long storeId){
		boolean success = false;
		StoreRO storeRO = StoreMgr.getInstance().getSimple(storeId);
		BUser buserRO = BUserQueryMgr.getInstance().getSimple(storeRO.getBossId());
		if(System.currentTimeMillis() >= buserRO.getExpiredTime()){
			success = true;
		}
		return success;
	}
	
	/**
	 * 
	 * @param boosId
	 * @return true：已满  false：未满
	 */
	public boolean isStoreLimited(long bossId){
		boolean success = false;
		VipContent vipContent = getVipContentByBossId(bossId);
		if(vipContent!=null) {
			BUser buser = BUserQueryMgr.getInstance().getSimple(bossId);
			List<StoreRO> list = StoreMgr.getInstance().findByIdList(buser.getStoreIdSet(), buser.getStoreIdSet().size(), 1);
			if(list.size() >= vipContent.getStoreLimit()){
				success = true;
			}
		}
		return success;
	}

	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isBuserLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent!=null) {
			StoreClerkInfo storeData = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
			int sum = 0;
			Collection<ClerkInfo> values = storeData.getClerkInfoMap().values();
			for (ClerkInfo data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getBuserLimit()){
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isLeaguerLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent != null){
			StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);
			int sum = 0;
			Collection<Leaguer> values = storeData.getLeaguerInfoMap().values();
			for (Leaguer data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getLeaguerLimit()){
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isGoodsLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent != null){
			StoreGoods storeData = StoreGoodsMgr.getInstance().getByStoreId(storeId);
			int sum = 0;
			Collection<Goods> values = storeData.getGoodsMap().values();
			for (Goods data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getGoodsLimit()){
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isProductLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent != null){
			StoreProductInfo storeData = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
			int sum = 0;
			Collection<ProductInfo> values = storeData.getProductInfoMap().values();
			for (ProductInfo data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getProductLimit()){
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isPackageLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent != null){
			StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
			int sum = 0;
			Collection<PackageProject> values = storeData.getPackageProjectMap().values();
			for (PackageProject data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getPackageLimit()){
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isProductCardLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent != null){
			StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
			int sum = 0;
			Collection<ProductCard> values = storeData.getProductCardMap().values();
			for (ProductCard data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getPrdCardLimit()){
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * @param storeId
	 * @return true：已满  false：未满
	 */
	public boolean isMemberCardLimited(long storeId){
		boolean success = false;
		VipContent vipContent = getVipContentByStoreId(storeId);
		if(vipContent != null){
			StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
			int sum = 0;
			Collection<MembershipCard> values = storeData.getMembershipCardMap().values();
			for (MembershipCard data : values) {
				if(data.getEntityState()!=EntityState.Deleted.ordinal()) {
					sum++;
				}
			}
			if(sum >= vipContent.getMemberCardLimit()){
				success = true;
			}
		}
		return success;
	}
	
	private VipContent getVipContentByStoreId(long storeId) {
		StoreRO storeRO = StoreMgr.getInstance().getSimple(storeId);
		if(storeRO == null || storeRO.getBossId() == 0L) {
			return null;
		}
		return getVipContentByBossId(storeRO.getBossId());
	}
	
	private VipContent getVipContentByBossId(long bossId) {
		BuserRole buserRole = BuserRoleMgr.getInstance().getSimple(bossId);
		return buserRole.getVipContent();
	}

}
