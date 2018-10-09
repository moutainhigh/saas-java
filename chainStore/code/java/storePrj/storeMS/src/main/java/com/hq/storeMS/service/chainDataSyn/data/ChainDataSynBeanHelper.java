package com.hq.storeMS.service.chainDataSyn.data;

import java.util.Collection;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.common.DataOriginEnum;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeCardInfo.data.CardStatusEnum;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.GoodsStateEnum;
import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.PackageProjectType;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfoState;
import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 拉取连锁店数据 逻辑类似  放在该类[方便同时改动]
 * @author kevin
 *
 */
public class ChainDataSynBeanHelper {

	public static ChainDataSynBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainDataSynBeanHelper.class);
	}
	
	private final static String chainNumber="C";

	public void synStoreGoods(StoreGoods storeData, ChainGoods chainData, String goodsId) {
		Collection<com.hq.chainClient.service.chainGoods.data.GoodsType> types = chainData.getGoodsTypeMap().values();
		for (com.hq.chainClient.service.chainGoods.data.GoodsType chainType : types) {
			GoodsType storeType = storeData.takeGoodsTypeById(chainType.getId());
			if(storeType == null) {
				storeType = GoodsType.newInstance();
			}
			FastBeanCopyer.getInstance().copy(chainType, storeType);
			storeType.setOrigin(DataOriginEnum.CHAIN.ordinal());
			storeData.getGoodsTypeMap().put(storeType.getId(), storeType);
		}
		
		Goods sgoods = storeData.takeGoodsById(goodsId);
		com.hq.chainClient.service.chainGoods.data.Goods cgoods = chainData.takeGoodsById(goodsId);
		if(sgoods == null) {
			sgoods = Goods.newInstance();
		}
		FastBeanCopyer.getInstance().copy(cgoods, sgoods);
		sgoods.setPrice(cgoods.getSellPrice());
		sgoods.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sgoods.setNumber(chainNumber+cgoods.getNumber());
		if(cgoods.getState() == com.hq.chainClient.service.chainGoods.data.GoodsStateEnum.Open.ordinal()) {
			sgoods.setState(GoodsStateEnum.Open.ordinal());
		}else {
			sgoods.setState(GoodsStateEnum.Close.ordinal());
		}
		storeData.getGoodsMap().put(sgoods.getId(), sgoods);
	}
	
	public void synGoodsDetail(long storeId, GoodsDetail sdetail, com.hq.chainClient.service.chainGoods.data.GoodsDetail cdetail) {
		FastBeanCopyer.getInstance().copy(cdetail, sdetail);
		sdetail.setPrice(cdetail.getSellPrice());
		sdetail.setStoreId(storeId);
		sdetail.setGoodsId(cdetail.getId());
		sdetail.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sdetail.setNumber(chainNumber+cdetail.getNumber());
		sdetail.setId(AppUtils.joinByUnderline(storeId, cdetail.getId()));
		if(cdetail.getState() == com.hq.chainClient.service.chainGoods.data.GoodsStateEnum.Open.ordinal()) {
			sdetail.setState(GoodsStateEnum.Open.ordinal());
		}else {
			sdetail.setState(GoodsStateEnum.Close.ordinal());
		}
	}
	
	public void synStoreProduct(StoreProductInfo storeData, ChainProduct chainData, String productId) {
		Collection<com.hq.chainClient.service.chainProduct.data.ProductType> types = chainData.getProductTypeMap().values();
		for (com.hq.chainClient.service.chainProduct.data.ProductType chainType : types) {
			ProductType storeType = storeData.takeProductTypeById(chainType.getId());
			if(storeType == null) {
				storeType = ProductType.newInstance();
			}
			FastBeanCopyer.getInstance().copy(chainType, storeType);
			storeType.setOrigin(DataOriginEnum.CHAIN.ordinal());
			storeData.getProductTypeMap().put(storeType.getId(), storeType);
		}
		
		ProductInfo sproduct = storeData.takeProductInfoById(productId);
		com.hq.chainClient.service.chainProduct.data.Product cproduct = chainData.takeProductById(productId);
		if(sproduct == null) {
			sproduct = ProductInfo.newInstance();
		}
		FastBeanCopyer.getInstance().copy(cproduct, sproduct);
	
		sproduct.setPrice(cproduct.getSellPrice());
		sproduct.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sproduct.setNumber(chainNumber+cproduct.getNumber());
		if(cproduct.getState() == com.hq.chainClient.service.chainProduct.data.ProductStateEnum.Open.ordinal()) {
			sproduct.setState(ProductInfoState.Open.ordinal());
		}else {
			sproduct.setState(ProductInfoState.Close.ordinal());
		}
		
		storeData.getProductInfoMap().put(sproduct.getId(), sproduct);
	}
	
	public void synProductDetail(long storeId, ProductDetail sdetail, com.hq.chainClient.service.chainProduct.data.ProductDetail cdetail) {
		FastBeanCopyer.getInstance().copy(cdetail, sdetail);
		sdetail.setPrice(cdetail.getSellPrice());
		sdetail.setStoreId(storeId);
		sdetail.setProductId(cdetail.getId());
		sdetail.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sdetail.setNumber(chainNumber+cdetail.getNumber());
		sdetail.setId(AppUtils.joinByUnderline(storeId, cdetail.getId()));
		if(cdetail.getState() == com.hq.chainClient.service.chainProduct.data.ProductStateEnum.Open.ordinal()) {
			sdetail.setState(ProductInfoState.Open.ordinal());
		}else {
			sdetail.setState(ProductInfoState.Close.ordinal());
		}
	}
	
	public void synStorePackageProject(StorePackageProject storeData, ChainPackageProject chainData, String packageId) {
		Collection<com.hq.chainClient.service.chainPackageProject.data.PackageProjectType> types = chainData.getPackageProjectTypeMap().values();
		for (com.hq.chainClient.service.chainPackageProject.data.PackageProjectType chainType : types) {
			PackageProjectType storeType = storeData.takePackageProjectTypeById(chainType.getId());
			if(storeType == null) {
				storeType = PackageProjectType.newInstance();
			}
			FastBeanCopyer.getInstance().copy(chainType, storeType);
			storeType.setOrigin(DataOriginEnum.CHAIN.ordinal());
			storeData.getPackageProjectTypeMap().put(storeType.getId(), storeType);
		}
		
		PackageProject spackage = storeData.takePackageProjectById(packageId);
		com.hq.chainClient.service.chainPackageProject.data.PackageProject cpackage = chainData.takePackageProjectById(packageId);
		if(spackage == null) {
			spackage = PackageProject.newInstance();
		}
		FastBeanCopyer.getInstance().copy(cpackage, spackage);
		spackage.setSellPrice(cpackage.getSellPrice());
		spackage.setOrigin(DataOriginEnum.CHAIN.ordinal());
		spackage.setNumber(chainNumber+cpackage.getNumber());
		storeData.getPackageProjectMap().put(spackage.getId(), spackage);
	}
	
	public void synPackageProjectDetail(long storeId, PackageProjectDetail sdetail, com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail cdetail) {
		FastBeanCopyer.getInstance().copy(cdetail, sdetail);
		sdetail.setSellPrice(cdetail.getSellPrice());
		sdetail.setStoreId(storeId);
		sdetail.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sdetail.setNumber(chainNumber+cdetail.getNumber());
		sdetail.setId(AppUtils.joinByUnderline(storeId, cdetail.getId()));
	}
	
	public void synStoreProductCard(StoreCardInfo storeData, ChainCard chainData, String cardId) {
		Collection<com.hq.chainClient.service.chainCard.data.PrdCardType> types = chainData.getPrdCardTypeMap().values();
		for (com.hq.chainClient.service.chainCard.data.PrdCardType chainType : types) {
			PrdCardType storeType = storeData.takeProductCardTypeById(chainType.getId());
			if(storeType == null) {
				storeType = PrdCardType.newInstance();
			}
			FastBeanCopyer.getInstance().copy(chainType, storeType);
			storeType.setOrigin(DataOriginEnum.CHAIN.ordinal());
			storeData.getPrdCardTypeMap().put(storeType.getId(), storeType);
		}
		
		ProductCard sproductCard = storeData.takeProductCardById(cardId);
		com.hq.chainClient.service.chainCard.data.ProductCard cproductCard = chainData.takeProductCardById(cardId);
		if(sproductCard == null) {
			sproductCard = ProductCard.newInstance();
		}
		FastBeanCopyer.getInstance().copy(cproductCard, sproductCard);
		sproductCard.setSellPrice(cproductCard.getSellPrice());
		sproductCard.setOrigin(DataOriginEnum.CHAIN.ordinal());
		if(cproductCard.getStatus() == com.hq.chainClient.service.chainCard.data.CardStatusEnum.OPEN.ordinal()) {
			sproductCard.setStatus(CardStatusEnum.OPEN.ordinal());
		}else {
			sproductCard.setStatus(CardStatusEnum.CLOSE.ordinal());
		}
		sproductCard.setNumber(chainNumber+cproductCard.getNumber());
		storeData.getProductCardMap().put(sproductCard.getId(), sproductCard);
	}
	
	public void synProductCardDetail(long storeId, ProductCardDetail sdetail, com.hq.chainClient.service.chainCard.data.ProductCardDetail cdetail) {
		FastBeanCopyer.getInstance().copy(cdetail, sdetail);
		sdetail.setSellPrice(cdetail.getSellPrice());
		sdetail.setStoreId(storeId);
		sdetail.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sdetail.setId(AppUtils.joinByUnderline(storeId, cdetail.getId()));
		if(cdetail.getStatus() == com.hq.chainClient.service.chainCard.data.CardStatusEnum.OPEN.ordinal()) {
			sdetail.setStatus(CardStatusEnum.OPEN.ordinal());
		}else {
			sdetail.setStatus(CardStatusEnum.CLOSE.ordinal());
		}
		sdetail.setNumber(chainNumber+cdetail.getNumber());
	}
	
	public void synStoreMemberCard(StoreCardInfo storeData, ChainCard chainData, String cardId) {
		MembershipCard smemberCard = storeData.takeMembershipCardById(cardId);
		com.hq.chainClient.service.chainCard.data.MembershipCard cmemberCard = chainData.takeMembershipCardById(cardId);
		if(smemberCard == null) {
			smemberCard = MembershipCard.newInstance();
		}
		FastBeanCopyer.getInstance().copy(cmemberCard, smemberCard);
		smemberCard.setOrigin(DataOriginEnum.CHAIN.ordinal());
		if(cmemberCard.getStatus() == com.hq.chainClient.service.chainCard.data.CardStatusEnum.OPEN.ordinal()) {
			smemberCard.setStatus(CardStatusEnum.OPEN.ordinal());
		}else {
			smemberCard.setStatus(CardStatusEnum.CLOSE.ordinal());
		}
		smemberCard.setNumber(chainNumber+cmemberCard.getNumber());
		storeData.getMembershipCardMap().put(smemberCard.getId(), smemberCard);
	}
	
	public void synMemberCardDetail(long storeId, MembershipCardDetail sdetail, com.hq.chainClient.service.chainCard.data.MembershipCardDetail cdetail) {
		FastBeanCopyer.getInstance().copy(cdetail, sdetail);
		sdetail.setStoreId(storeId);
		sdetail.setOrigin(DataOriginEnum.CHAIN.ordinal());
		sdetail.setId(AppUtils.joinByUnderline(storeId, cdetail.getId()));
		if(cdetail.getStatus() == com.hq.chainClient.service.chainCard.data.CardStatusEnum.OPEN.ordinal()) {
			sdetail.setStatus(CardStatusEnum.OPEN.ordinal());
		}else {
			sdetail.setStatus(CardStatusEnum.CLOSE.ordinal());
		}
		sdetail.setNumber(chainNumber+cdetail.getNumber());
	}

}
