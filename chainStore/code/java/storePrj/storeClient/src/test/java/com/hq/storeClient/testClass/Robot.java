package com.hq.storeClient.testClass;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.CollectionUtils;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.leaguerDetail.bs.LeaguerDetailClientMgr;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeClient.service.storeCardInfo.bs.StoreCardInfoClientMgr;
import com.hq.storeClient.service.storeCardInfo.data.MembershipCard;
import com.hq.storeClient.service.storeCardInfo.data.PrdCardType;
import com.hq.storeClient.service.storeCardInfo.data.ProductCard;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeClient.service.storeGoods.bs.StoreGoodsClientMgr;
import com.hq.storeClient.service.storeGoods.data.Goods;
import com.hq.storeClient.service.storeGoods.data.GoodsType;
import com.hq.storeClient.service.storeGoods.data.StoreGoods;
import com.hq.storeClient.service.storeLeaguerInfo.bs.StoreLeaguerInfoClientMgr;
import com.hq.storeClient.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeClient.service.storeLeaguerInfo.data.OutsiderEnum;
import com.hq.storeClient.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeClient.service.storePackageProject.bs.StorePackageProjectClientMgr;
import com.hq.storeClient.service.storePackageProject.data.PackageProject;
import com.hq.storeClient.service.storePackageProject.data.PackageProjectType;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.hq.storeClient.service.storeProductInfo.bs.StoreProductInfoClientMgr;
import com.hq.storeClient.service.storeProductInfo.data.ProductInfo;
import com.hq.storeClient.service.storeProductInfo.data.ProductType;
import com.hq.storeClient.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class Robot {
	
	public static Robot getInstance() {
		return HotSwap.getInstance().getSingleton(Robot.class);
	}
	
	public LeaguerDetail getLeaguerDetail(long storeId, String leaguerId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		return LeaguerDetailClientMgr.getInstance().get(storeId, leaguerId);
	}
	
	public Leaguer getRandomLeaguer(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreLeaguerInfo storeData = StoreLeaguerInfoClientMgr.getInstance().get(storeId);
		storeData.getLeaguerInfoMap().remove(OutsiderEnum.Boy.getId(storeId));
		storeData.getLeaguerInfoMap().remove(OutsiderEnum.Girl.getId(storeId));
		List<Leaguer> list = new ArrayList<Leaguer>(storeData.getLeaguerInfoMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public Leaguer getLeaguer(long storeId, String leaguerId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreLeaguerInfo storeData = StoreLeaguerInfoClientMgr.getInstance().get(storeId);
		return storeData.getLeaguerInfoMap().get(leaguerId);
	}
	
	public ProductInfo getRandomProductInfo(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreProductInfo storeData = StoreProductInfoClientMgr.getInstance().get(storeId);
		List<ProductInfo> list = new ArrayList<ProductInfo>(storeData.getProductInfoMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductType getRandomProductType(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreProductInfo storeData = StoreProductInfoClientMgr.getInstance().get(storeId);
		List<ProductType> list = new ArrayList<ProductType>(storeData.getProductTypeMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductInfo getProductInfo(long storeId, String productId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreProductInfo storeData = StoreProductInfoClientMgr.getInstance().get(storeId);
		return storeData.getProductInfoMap().get(productId);
	}
	
	public Goods getRandomGoods(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreGoods storeData = StoreGoodsClientMgr.getInstance().get(storeId);
		List<Goods> list = new ArrayList<Goods>(storeData.getGoodsMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public GoodsType getRandomGoodsType(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreGoods storeData = StoreGoodsClientMgr.getInstance().get(storeId);
		List<GoodsType> list = new ArrayList<GoodsType>(storeData.getGoodsTypeMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public Goods getGoods(long storeId, String goodsId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreGoods storeData = StoreGoodsClientMgr.getInstance().get(storeId);
		return storeData.getGoodsMap().get(goodsId);
	}
	
	public ProductCard getRandomProductCard(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo storeData = StoreCardInfoClientMgr.getInstance().get(storeId);
		List<ProductCard> list = new ArrayList<ProductCard>(storeData.getProductCardMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PrdCardType getRandomPrdCardType(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo storeData = StoreCardInfoClientMgr.getInstance().get(storeId);
		List<PrdCardType> list = new ArrayList<PrdCardType>(storeData.getPrdCardTypeMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public ProductCard getProductCard(long storeId, String prdCardId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo storeData = StoreCardInfoClientMgr.getInstance().get(storeId);
		return storeData.getProductCardMap().get(prdCardId);
	}
	
	public MembershipCard getRandomMembershipCard(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo storeData = StoreCardInfoClientMgr.getInstance().get(storeId);
		List<MembershipCard> list = new ArrayList<MembershipCard>(storeData.getMembershipCardMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public MembershipCard getMembershipCard(long storeId, String memberId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StoreCardInfo storeData = StoreCardInfoClientMgr.getInstance().get(storeId);
		return storeData.getMembershipCardMap().get(memberId);
	}
	
	public PackageProject getRandomPackageProject(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StorePackageProject storeData = StorePackageProjectClientMgr.getInstance().get(storeId);
		List<PackageProject> list = new ArrayList<PackageProject>(storeData.getPackageProjectMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PackageProjectType getRandomPackageProjectType(long storeId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StorePackageProject storeData = StorePackageProjectClientMgr.getInstance().get(storeId);
		List<PackageProjectType> list = new ArrayList<PackageProjectType>(storeData.getPackageProjectTypeMap().values());
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(RandomUtils.nextInt(0, list.size()));
	}
	
	public PackageProject getPackageProject(long storeId, String packageId) {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StorePackageProject storeData = StorePackageProjectClientMgr.getInstance().get(storeId);
		return storeData.getPackageProjectMap().get(packageId);
	}
	
}
