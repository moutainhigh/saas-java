package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeMS.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeMS.service.productDetail.bs.ProductDetailMgr;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SellProductTypeMgr {
	
	public static SellProductTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SellProductTypeMgr.class);
	}
	
	public void splitData(long storeId) {
		try {
			updateProductCard(storeId);
			updatePackagePrj(storeId);
			updateProduct(storeId);
			updateGoods(storeId);
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SellProductTypeMgr[splitData]", "项目商品套餐次卡添加默认分类数据失败"+storeId, e);
		}
	}
	
	private void updateProductCard(long storeId) {
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		Collection<ProductCard> values = storeData.getProductCardMap().values();
		for (ProductCard data : values) {
			if(StringUtils.isBlank(data.getTypeId())) {
				data.setTypeId(SysInitTypeEnum.UnClassify.getId());
			}
		}
		StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<ProductCardDetail> page = ProductCardDetailMgr.getInstance().getProductCardDetailPageInfo(queryForm);
		List<ProductCardDetail> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)) {
			for (ProductCardDetail detail : list) {
				if(StringUtils.isBlank(detail.getTypeId())) {
					detail.setTypeId(SysInitTypeEnum.UnClassify.getId());
					ProductCardDetailMgr.getInstance().update(detail);
				}
			}
		}
	}
	
	private void updatePackagePrj(long storeId) {
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		Collection<PackageProject> values = storeData.getPackageProjectMap().values();
		for (PackageProject data : values) {
			if(StringUtils.isBlank(data.getTypeId())) {
				data.setTypeId(SysInitTypeEnum.UnClassify.getId());
			}
		}
		StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
		
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<PackageProjectDetail> page = PackageProjectDetailMgr.getInstance().getPackageProjectDetailPageInfo(queryForm);
		List<PackageProjectDetail> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)) {
			for (PackageProjectDetail detail : list) {
				if(StringUtils.isBlank(detail.getTypeId())) {
					detail.setTypeId(SysInitTypeEnum.UnClassify.getId());
					PackageProjectDetailMgr.getInstance().update(detail);
				}
			}
		}
	}
	
	private void updateProduct(long storeId) {
		StoreProductInfo storeData = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		Collection<ProductInfo> values = storeData.getProductInfoMap().values();
		for (ProductInfo data : values) {
			if(StringUtils.isBlank(data.getTypeId())) {
				data.setTypeId(SysInitTypeEnum.UnClassify.getId());
			}
		}
		StoreProductInfoMgr.getInstance().update(storeData);
		
		ProductDetailQueryForm queryForm = ProductDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<ProductDetail> page = ProductDetailMgr.getInstance().getProductDetailPageInfo(queryForm);
		List<ProductDetail> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)) {
			for (ProductDetail detail : list) {
				if(StringUtils.isBlank(detail.getTypeId())) {
					detail.setTypeId(SysInitTypeEnum.UnClassify.getId());
					ProductDetailMgr.getInstance().update(detail);
				}
			}
		}
	}
	
	private void updateGoods(long storeId) {
		StoreGoods storeData = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		Collection<Goods> values = storeData.getGoodsMap().values();
		for (Goods data : values) {
			if(StringUtils.isBlank(data.getTypeId())) {
				data.setTypeId(SysInitTypeEnum.UnClassify.getId());
			}
		}
		StoreGoodsMgr.getInstance().update(storeData);
		
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<GoodsDetail> page = GoodsDetailMgr.getInstance().getGoodsDetailPageInfo(queryForm);
		List<GoodsDetail> list = page.getList();
		if(CollectionUtils.isNotEmpty(list)) {
			for (GoodsDetail detail : list) {
				if(StringUtils.isBlank(detail.getTypeId())) {
					detail.setTypeId(SysInitTypeEnum.UnClassify.getId());
					GoodsDetailMgr.getInstance().update(detail);
				}
			}
		}
	}
}
